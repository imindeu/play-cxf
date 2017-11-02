package org.apache.cxf.transport.play

import com.google.inject.{AbstractModule, Provides}



class CxfModule extends AbstractModule {

  def configure():Unit = {
    requestInjection(CxfModule.cxfController)
  }

  @Provides
  def provideCxfController(ctx: CtxConfigurator):CxfController = CxfModule.cxfController
}

object CxfModule {

  val cxfController = new CxfController

  def getCxfControllerInstance = cxfController

}
