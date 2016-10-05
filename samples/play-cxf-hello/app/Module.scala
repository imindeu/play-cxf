import com.google.inject.AbstractModule
import org.apache.cxf.transport.play.CtxConfigurator

class Module extends AbstractModule {

  override def configure():Unit = {
    bind(classOf[CtxConfigurator]).to(classOf[HelloConfigurator])
  }

}
