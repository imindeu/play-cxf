package services.coffee.heater
import services.coffee.ingredient.Water

import services.coffee.ingredient.Water.Temperature._

class ElectricHeater extends Heater {

  override def boilWater(water: Water): Water = water.copy(temperature = Hot)
}
