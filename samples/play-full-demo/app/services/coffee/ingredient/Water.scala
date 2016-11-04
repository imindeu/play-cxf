package services.coffee.ingredient

import services.coffee.ingredient.Water.Temperature.Temperature

case class Water(temperature: Temperature)

object Water {

  object Temperature extends Enumeration {
    type Temperature = Value
    val Cold, Hot = Value
  }
}

