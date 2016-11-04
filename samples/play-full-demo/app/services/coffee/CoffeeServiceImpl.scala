package services.coffee

import com.google.inject.Inject
import services.coffee.bean.BeanContainer
import services.coffee.heater.Heater
import services.coffee.Coffee.Type.Type
import services.coffee.ingredient.{Bean, Robusta}
import services.coffee.ingredient.Bean.{Type => BeanType}
import services.coffee.Coffee.{Type => CoffeeType}
import services.coffee.water.WaterContainer

class CoffeeServiceImpl extends CoffeeService {

  @Inject()
  var heater: Heater = null

  @Inject()
  var beanContainer: BeanContainer = null

  @Inject()
  var waterContainer: WaterContainer = null

  override def makeCoffee(coffeeType: Type): Coffee = {
    if (!isInitialized) throw UninitializedFieldError("One of the dependencies is not initialized.")

    val bean = new Robusta(BeanType.Whole)
    makeCoffee(bean, chooseStategy(coffeeType))
  }

  private def makeCoffee(bean: Bean, recept: (Bean) => Coffee) = recept(bean)

  private def chooseStategy(coffeeType: Type) = coffeeType match {
    case CoffeeType.Espresso => makeEspresso
    case CoffeeType.Doppio   => makeDoppio
  }

  private val makeEspresso = (bean: Bean) => {
    val water        = waterContainer.getWater()
    val groundedBean = beanContainer.groundCoffee(bean)
    val boiledWater  = heater.boilWater(water)

    Coffee(CoffeeType.Espresso)
  }

  private val makeDoppio = (bean: Bean) => {
    val water         = waterContainer.getWater()
    val groundedBean1 = beanContainer.groundCoffee(bean)
    val groundedBean2 = beanContainer.groundCoffee(bean)
    val boiledWater   = heater.boilWater(water)

    Coffee(CoffeeType.Doppio)
  }

  private def isInitialized = heater != null && beanContainer != null && waterContainer != null
}