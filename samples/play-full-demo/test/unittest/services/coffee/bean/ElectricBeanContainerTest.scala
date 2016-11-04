package unittest.services.coffee.bean

import org.scalatest.{FlatSpec, Matchers}
import services.coffee.bean.ElectricBeanContainer
import services.coffee.ingredient.Bean.Type
import services.coffee.ingredient.Java

class ElectricBeanContainerTest extends FlatSpec with Matchers {

  trait Spec {
    val beanContainer = new ElectricBeanContainer
  }

  "An ElectricBeanContainer" should "ground whole coffee beans" in new Spec {
    val bean = new Java(Type.Whole)
    beanContainer.groundCoffee(bean) shouldEqual bean.copy(Type.Grounded)
  }

  it should "ground already grounded beans" in new Spec {
    val bean = new Java(Type.Grounded)
    beanContainer.groundCoffee(bean) shouldEqual bean.copy(Type.Grounded)
  }

}
