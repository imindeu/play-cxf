package services.coffee.heater

import services.coffee.ingredient.Water

trait Heater {

  def boilWater(water: Water): Water
}
