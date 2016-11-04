package services.coffee

import services.coffee.bean.BeanContainer
import services.coffee.heater.Heater
import services.coffee.Coffee.Type.Type

trait CoffeeService {

  def heater: Heater
  def beanContainer: BeanContainer

  def makeCoffee(coffeeType: Type): Coffee
}