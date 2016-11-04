package services.coffee

import services.coffee.Coffee.Type.Type

case class Coffee(coffeeType: Type)

object Coffee {

  object Type extends Enumeration {
    type Type = Value
    val Espresso, Doppio = Value
  }
}
