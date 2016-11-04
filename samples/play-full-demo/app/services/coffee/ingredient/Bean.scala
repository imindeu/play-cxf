package services.coffee.ingredient

import services.coffee.ingredient.Bean.Type.Type

sealed trait Bean {

  def beanType: Type

  def copy(beanType: Type = beanType): Bean
}

case class Java(beanType: Type) extends Bean {

  override def copy(beanType: Type = beanType): Java = new Java(beanType)
}

case class Robusta(beanType: Type) extends Bean {

  override def copy(beanType: Type = beanType): Robusta = new Robusta(beanType)
}

object Bean {

  object Type extends Enumeration {
    type Type = Value
    val Whole, Grounded = Value
  }
}