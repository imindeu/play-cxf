package unittest.services.coffee

import org.scalatest.{FlatSpec, Matchers}
import org.scalamock.scalatest.MockFactory
import services.coffee.{Coffee, CoffeeServiceImpl}
import services.coffee.Coffee.Type
import services.coffee.bean.BeanContainer
import services.coffee.heater.Heater
import services.coffee.ingredient.Water
import services.coffee.ingredient.Water.Temperature._
import services.coffee.water.WaterContainer

class CoffeeServiceImplTest extends FlatSpec with Matchers with MockFactory {

  trait Spec {
    val heater         = mock[Heater]
    val beanContainer  = mock[BeanContainer]
    val waterContainer = mock[WaterContainer]

    val service = new CoffeeServiceImpl
    service.heater = heater
    service.beanContainer = beanContainer
    service.waterContainer = waterContainer
  }

  "A CoffeeServiceImpl" should "make an espresso" in new Spec {
    val water = new Water(Cold)

    (service.waterContainer.getWater _).expects().once.returning(water)
    (service.heater.boilWater _).expects(water).once
    (service.beanContainer.groundCoffee _).expects(*).once
    
    service.makeCoffee("Espresso") should be (Coffee(Type.Espresso).toString)
  }

  it should "make an doppio" in new Spec {
    val water = new Water(Cold)

    (service.waterContainer.getWater _).expects().once.returning(water)
    (service.heater.boilWater _).expects(water).once
    (service.beanContainer.groundCoffee _).expects(*).twice

    service.makeCoffee("Doppio") should be (Coffee(Type.Doppio).toString)
  }

  it should "throw NoSuchElementException for not known coffee type" in new Spec {
    (service.waterContainer.getWater _).expects().never
    (service.heater.boilWater _).expects(*).never
    (service.beanContainer.groundCoffee _).expects(*).never

    a [NoSuchElementException] should be thrownBy {
      service.makeCoffee("NotKnownCoffeeType")
    }
  }

  it should "throw UninitializedFieldError exception when one of the dependencies is null" in new Spec {
    val heaters = Seq(null, heater)
    val beanContainers = Seq(null, beanContainer)
    val waterContainers = Seq(null, waterContainer)

    for (heater         <- heaters;
         beanContainer  <- beanContainers;
         waterContainer <- waterContainers
        if heater == null || beanContainer == null || waterContainer == null) {
      service.heater = heater
      service.beanContainer = beanContainer
      service.waterContainer = waterContainer

      a [UninitializedFieldError] should be thrownBy {
        service.makeCoffee("Espresso")
      }

      a [UninitializedFieldError] should be thrownBy {
        service.makeCoffee("Doppio")
      }
    }
  }

}
