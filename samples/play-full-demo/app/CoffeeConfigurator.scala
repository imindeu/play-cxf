import com.google.inject.{Inject, Singleton}
import org.apache.cxf.transport.play.CtxConfigurator
import org.springframework.context.support.ClassPathXmlApplicationContext
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

@Singleton
class CoffeeConfigurator @Inject() (
  applicationLifecycle: ApplicationLifecycle,
  val ctx: ClassPathXmlApplicationContext) extends CtxConfigurator {

  ctx.start()

  applicationLifecycle.addStopHook{ () =>
    Future.successful(ctx.stop())
  }
}