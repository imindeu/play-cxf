import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import Util.{SoapAsserter, createTrimmedXml}

@RunWith(classOf[JUnitRunner])
class HelloServiceSpec extends Specification {

  "HelloService" should {

    trait HelloAsserter extends WithApplication with SoapAsserter {
      override val Url: String = "/service/hello"
    }

    "send the wsdl file on request" in new WithApplication() {
      val request = FakeRequest(GET, "/service/hello?wsdl")
      val response = route(app, request).get

      status(response) must equalTo(OK)
      contentAsString(response) must contain("HelloWorldImplService")
    }

    "return error on bad request" in new HelloAsserter {
      val body = createTrimmedXml(
        s"""
           |<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hel="http://hello.services/">
           |   <soapenv:Header/>
           |   <soapenv:Body>
           |      <hel:badRequest>
           |      </hel:badRequest>
           |   </soapenv:Body>
           |</soapenv:Envelope>
        """.stripMargin)
      val error = "No binding operation info while invoking unknown method with params unknown"

      val request = FakeRequest(GET, "/service/hello")
      val response = route(app, request).get

      status(response) must equalTo(OK)
      contentAsString(response) must contain(error)
    }

    "in sayHi request" >> {

      def requestBody(arg0: Option[String]) = createTrimmedXml(
        s"""
           |<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hel="http://hello.services/">
           |   <soapenv:Header/>
           |   <soapenv:Body>
           |      <hel:sayHi>
           |         ${arg0.fold("")(a => s"<arg0>$a</arg0>")}
           |      </hel:sayHi>
           |   </soapenv:Body>
           |</soapenv:Envelope>
        """.stripMargin)

      def responseBody(arg: String) = createTrimmedXml(
        s"""
           |<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
           |   <soap:Body>
           |      <ns2:sayHiResponse xmlns:ns2="http://hello.services/">
           |         <return>Hello $arg</return>
           |      </ns2:sayHiResponse>
           |   </soap:Body>
           |</soap:Envelope>
        """.stripMargin)

      "say hello to Béla for passed 'Béla' as argument" in new HelloAsserter {
        val arg = "Béla"
        sendAndAssertRequest(requestBody(Option(arg)), responseBody(arg))
      }

      "say hello for empty string" in new HelloAsserter {
        val arg = ""
        sendAndAssertRequest(requestBody(Option(arg)), responseBody(arg))
      }

      "say hello for missing argument" in new HelloAsserter {
        val arg = ""
        sendAndAssertRequest(requestBody(None), responseBody(arg))
      }
    }

    "in abc request" >> {

      def requestBody(arg0: Option[String]) = createTrimmedXml(
        s"""
           |<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hel="http://hello.services/">
           |   <soapenv:Header/>
           |   <soapenv:Body>
           |      <hel:abc>
           |         ${arg0.fold("")(a => s"<arg0>$a</arg0>")}
           |      </hel:abc>
           |   </soapenv:Body>
           |</soapenv:Envelope>
        """.stripMargin)

      def responseBody(arg: String) = createTrimmedXml(
        s"""
           |<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
           |   <soap:Body>
           |      <ns2:abcResponse xmlns:ns2="http://hello.services/">
           |         <return>$arg</return>
           |      </ns2:abcResponse>
           |   </soap:Body>
           |</soap:Envelope>
        """.stripMargin)

      "transform ABC to lowercase" in new HelloAsserter {
        val arg = "ABC"
        sendAndAssertRequest(requestBody(Option(arg)), responseBody(arg.toLowerCase))
      }

      "return empty string for empty string" in new HelloAsserter {
        val arg = ""
        sendAndAssertRequest(requestBody(Option(arg)), responseBody(arg))
      }

      "return empty string for missing argument" in new HelloAsserter {
        val arg = ""
        sendAndAssertRequest(requestBody(None), responseBody(arg))
      }
    }

  }

}