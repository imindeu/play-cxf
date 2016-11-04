package unittest.services.coffee.heater

import org.scalatest.{FlatSpec, Matchers}
import services.coffee.heater.ElectricHeater
import services.coffee.ingredient.Water
import services.coffee.ingredient.Water.Temperature._

class ElectricHeaterTest extends FlatSpec with Matchers {

  trait Spec {
    val heater = new ElectricHeater
  }

  "An ElectricHeater" should "boil cold water" in new Spec {
    val water = new Water(Cold)
    heater.boilWater(water) should be (water.copy(Hot))
  }

  it should "boil hot water" in new Spec {
    val water = new Water(Hot)
    heater.boilWater(water) should be (water.copy(Hot))
  }

}
