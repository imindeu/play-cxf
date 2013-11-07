package org.apache.cxf.transport.play

import java.io.{ByteArrayInputStream, InputStream, OutputStream}

import org.apache.cxf.message.{MessageImpl, Message}

import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._

import scala.concurrent.Promise
import scala.collection.JavaConverters._

object CxfController extends Controller {

  /**
   * Factory method for Spring.
   */
  def getInstance() = this

  /**
   * Apache CXF transport factory, set by Spring.
   */
  var transportFactory: PlayTransportFactory = null

  val maxRequestSize = 1024 * 1024

  def handle(path: String) = Action.async(parse.raw(maxRequestSize)) { implicit request =>
    val delayedOutput = new DelayedOutputStream
    val replyPromise: Promise[Message] = Promise.apply()
    dispatchMessage(extractMessage, delayedOutput, replyPromise)

    val resultEnumerator = Enumerator.outputStream { os =>
      delayedOutput.setTarget(os)
    }
    replyPromise.future.map { outMessage =>
      Ok.chunked(resultEnumerator >>> Enumerator.eof) withHeaders(
        Message.CONTENT_TYPE -> outMessage.get(Message.CONTENT_TYPE).asInstanceOf[String]
      )
    }
  }

  private def extractMessage()(implicit request: Request[RawBuffer]) = {
    val msg: Message = new MessageImpl
    msg.put(Message.ENDPOINT_ADDRESS, endpointAddress)
    msg.put(Message.HTTP_REQUEST_METHOD, request.method)
    msg.put(Message.REQUEST_URL, request.path)
    msg.put(Message.QUERY_STRING, request.rawQueryString)
    msg.put(Message.PROTOCOL_HEADERS, headersAsJava)
    msg.put(Message.CONTENT_TYPE, request.headers.get(Message.CONTENT_TYPE) getOrElse null)
    msg.put(Message.ACCEPT_CONTENT_TYPE, request.headers.get(Message.ACCEPT_CONTENT_TYPE) getOrElse null)

    request.body.asBytes() foreach { arr: Array[Byte] =>
      msg.setContent(classOf[InputStream], new ByteArrayInputStream(arr))
    }

    msg
  }

  private def endpointAddress()(implicit request: Request[RawBuffer]) = "play://" + request.host + request.path

  private def headersAsJava()(implicit request: Request[RawBuffer]) =
    request.headers.toMap.mapValues { s: Seq[String] =>
      s.asJava
    }.asJava

  private def dispatchMessage(inMessage: Message,
                              output: OutputStream,
                              replyPromise: Promise[Message])
                             (implicit request: Request[RawBuffer]) {

    val dOpt = Option(transportFactory.getDestination(endpointAddress))
    dOpt match {
      case Some(destination) =>
        destination.dispatchMessage(inMessage, output, replyPromise)
      case _ =>
        replyPromise.failure(new IllegalArgumentException)
    }
  }

  def setTransportFactory(factory: PlayTransportFactory) {
    this.transportFactory = factory
  }

}
