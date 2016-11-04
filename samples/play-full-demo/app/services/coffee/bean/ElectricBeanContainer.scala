package services.coffee.bean

import services.coffee.ingredient.Bean
import services.coffee.ingredient.Bean._

class ElectricBeanContainer extends BeanContainer {

  override def groundCoffee(bean: Bean): Bean = bean.copy(Type.Grounded)
}
