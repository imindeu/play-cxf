/*
package functional

import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.xml.Utility.trim
import scala.xml.{NodeSeq, XML}

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
*/
