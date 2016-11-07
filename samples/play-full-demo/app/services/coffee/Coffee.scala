package services.coffee

import services.coffee.Coffee.Type.Type

case class Coffee(coffeeType: Type)

object Coffee {

  object Type extends Enumeration {
    type Type = Value
    val Espresso, Doppio = Value

    def parse(str: String) = str match {
      case "Espresso" => Espresso
      case "Doppio"   => Doppio
      case _          => throw new NoSuchElementException("There is no Coffee type for the give string.")
    }
  }
}
