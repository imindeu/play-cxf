import org.springframework.context.support.ClassPathXmlApplicationContext
import play.api.{Application, GlobalSettings}

object Global extends GlobalSettings {

  val possibleEnvironments = Set("production", "test", "development")
  val ctx = new ClassPathXmlApplicationContext

  override def onStart(app: Application) {
    super.onStart(app)

    ctx.setConfigLocation("applicationContext.xml")
    app.configuration.getString("application.environment", Some(possibleEnvironments)).foreach(
      env => ctx.getEnvironment.setActiveProfiles(env)
    )
    ctx.refresh()
    ctx.start()
  }

  override def onStop(app: Application) {
    ctx.stop()
    super.onStop(app)
  }
}
