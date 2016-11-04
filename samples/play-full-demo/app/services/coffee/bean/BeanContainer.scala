package services.coffee.bean

import services.coffee.ingredient.Bean

trait BeanContainer {

  def groundCoffee(bean: Bean): Bean
}
