package functional

import akka.stream.Materializer
import functional.util.Util
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._

class CoffeeService extends PlaySpec with OneAppPerTest {

  trait Spec {
    val url = "/service/coffee"

    implicit lazy val materializer: Materializer = app.materializer

    val Espresso = "Espresso"
    val Doppio = "Doppio"
  }

  "CoffeeService" should {

    {
      def body(coffeeType: String) = Util.createTrimmedXml(
        s"""
           |<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cof="http://coffee.services/">
           |   <soapenv:Header/>
           |   <soapenv:Body>
           |      <cof:makeCoffee>$coffeeType</cof:makeCoffee>
           |   </soapenv:Body>
           |</soapenv:Envelope>
         """.stripMargin
      )

      def response(coffeeType: String) = Util.createTrimmedXml(
        s"""
           |<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
           |   <soap:Body>
           |      <makeCoffeeResponse xmlns="http://coffee.services/">Coffee($coffeeType)</makeCoffeeResponse>
           |   </soap:Body>
           |</soap:Envelope>
         """.stripMargin
      )

      "send an Espresso on valid request" in new Spec {
        val request = FakeRequest(POST, url)
          .withXmlBody(body(Espresso))

        route(app, request).foreach { r =>
          status(r) mustBe OK
          Util.createTrimmedXml(contentAsString(r)) mustEqual response(Espresso)
        }
      }

      "send a Doppio on valid request" in new Spec {
        val request = FakeRequest(POST, url)
          .withXmlBody(body(Doppio))

        route(app, request).foreach { r =>
          status(r) mustBe OK
          Util.createTrimmedXml(contentAsString(r)) mustEqual response(Doppio)
        }
      }

      "send error messages on unknown coffee type" in new Spec {
        val request = FakeRequest(POST, url)
          .withXmlBody(body("NotKnownCoffeeType"))

        route(app, request).foreach { r =>
          status(r) mustBe OK
          contentAsString(r) must include(
            "<faultstring>There is no Coffee type for the give string.</faultstring>")
        }
      }

    }

    {

      def body = Util.createTrimmedXml(
        s"""
           |<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cof="http://coffee.services/">
           |   <soapenv:Header/>
           |   <soapenv:Body>
           |      <cof:NotKnownOperation></cof:NotKnownOperation>
           |   </soapenv:Body>
           |</soapenv:Envelope>
         """.stripMargin
      )

      "send request error on bad request" in new Spec {
        val request = FakeRequest(POST, url)
          .withXmlBody(body)

        route(app, request).foreach { r =>
          status(r) mustBe OK
          contentAsString(r) must include("NotKnownOperation was not recognized.")
        }
      }
    }

  }
}