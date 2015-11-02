package controllers

import org.joda.time.DateTime
import play.api._
import play.api.mvc._
import services.sunsetrise.{LatLonDate, SunSetRiseService}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def sunriseLondon = Action {
    val service = new SunSetRiseService()
    val port = service.getSunSetRiseServiceSoap12

    val now = DateTime.now()
    val request = new LatLonDate
    request.setYear(now.getYear)
    request.setMonth(now.getMonthOfYear)
    request.setDay(now.getDayOfMonth)
    request.setLatitude(51.5287714f)
    request.setLongitude(-0.2420229f)

    val response = port.getSunSetRiseTime(request)
    Ok(views.html.sunrise(response))
  }

}