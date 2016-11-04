package unittest.services.coffee.water

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import services.coffee.ingredient.Water
import services.coffee.water.ListWaterContainer
import services.coffee.ingredient.Water.Temperature._

import scala.util.Success

class ListWaterContainerTest extends FlatSpec with Matchers with MockFactory {

  "A ListWaterContainer" should "get some water" in {
    val water = new Water(Cold)
    val waters = Seq(water)
    val waterContainer = new ListWaterContainer(waters)

    waterContainer.getWater() should be (water)
  }

  it should "throw exception when the container is empty" in {
    val waters = Nil
    val waterContainer = new ListWaterContainer(waters)

    a [NoSuchElementException] should be thrownBy {
      waterContainer.getWater()
    }
  }

}
