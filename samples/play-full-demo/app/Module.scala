import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import org.apache.cxf.transport.play.CtxConfigurator
import org.springframework.context.support.ClassPathXmlApplicationContext
import services.coffee.bean.{BeanContainer, ElectricBeanContainer}
import services.coffee.heater.{ElectricHeater, Heater}
import services.coffee.ingredient.Water
import services.coffee.ingredient.Water.Temperature._
import services.coffee.water.{ListWaterContainer, WaterContainer}

class Module extends AbstractModule with ScalaModule {

  override def configure():Unit = {
    val ctx = new ClassPathXmlApplicationContext("applicationContext.xml")

    bind[ClassPathXmlApplicationContext]
      .toInstance(ctx)

    val water = Seq.fill(3)(new Water(Cold))

    bind[Seq[Water]].toInstance(water)
    bind[Heater].to[ElectricHeater]
    bind[BeanContainer].to[ElectricBeanContainer]
    bind[WaterContainer].to[ListWaterContainer]

    val serviceImpl = ctx.getBean("ServiceImplBean")
    requestInjection(serviceImpl)

    bind[CtxConfigurator].to[CoffeeConfigurator]
  }

}
