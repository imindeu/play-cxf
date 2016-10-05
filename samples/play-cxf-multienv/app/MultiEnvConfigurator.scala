import com.google.inject.{Inject, Singleton}
import org.apache.cxf.transport.play.CtxConfigurator
import org.springframework.context.support.ClassPathXmlApplicationContext
import play.api.inject.ApplicationLifecycle
import play.api.Configuration

import scala.concurrent.Future

@Singleton
class MultiEnvConfigurator @Inject() (configuration: Configuration, applicationLifecycle: ApplicationLifecycle) extends CtxConfigurator {

  val possibleEnvironments = Set("production", "test", "development")
  val ctx = new ClassPathXmlApplicationContext

  ctx.setConfigLocation("applicationContext.xml")
  configuration.getString("application.environment", Some(possibleEnvironments)).foreach(
    env => ctx.getEnvironment.setActiveProfiles(env)
  )
  ctx.refresh()
  ctx.start()

  applicationLifecycle.addStopHook{ () =>
    Future.successful(ctx.stop())
  }
}
