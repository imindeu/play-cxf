package services.coffee.water

import com.google.inject.Inject
import services.coffee.ingredient.Water

class ListWaterContainer @Inject() (var water: Seq[Water]) extends WaterContainer {

  override def getWater(): Water = {
    val ret = water.headOption.getOrElse(throw new NoSuchElementException("The water container is empty."))
    water = water.tail
    ret
  }

}
