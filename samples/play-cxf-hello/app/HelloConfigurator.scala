import com.google.inject.{Inject, Singleton}
import org.apache.cxf.transport.play.CtxConfigurator
import org.springframework.context.support.ClassPathXmlApplicationContext
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

@Singleton
class HelloConfigurator @Inject() (applicationLifecycle: ApplicationLifecycle) extends CtxConfigurator {
  override val ctx = new ClassPathXmlApplicationContext("applicationContext.xml")
  ctx.start()

  applicationLifecycle.addStopHook{ () =>
    Future.successful(ctx.stop())
  }

}
