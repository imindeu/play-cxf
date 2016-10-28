import org.specs2.matcher.{AnyMatchers, MustExpectable}
import org.specs2.mutable.Specification
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

import scala.xml.{NodeSeq, XML}
import scala.xml.Utility.trim

object Util {

  def createTrimmedXml = trim _ compose XML.loadString

  trait SoapAsserter extends AnyMatchers {
    self: WithApplication  =>

    def Url: String
    def Request = FakeRequest(POST, Url)

    def sendAndAssertRequest(requestBody: NodeSeq, responseBody: NodeSeq) = {
      val request = Request.withXmlBody(requestBody)

      val response = route(app, request).get
      val result = createTrimmedXml(contentAsString(response))

      MustExpectable(status(response)) must equalTo(OK)
      MustExpectable(result) must equalTo(responseBody)
    }
  }

}
