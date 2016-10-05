package org.apache.cxf.transport.play

import com.google.inject.{Provides, AbstractModule}

class CxfModule extends AbstractModule {
  def configure():Unit = {}

  @Provides
  def provideCxfController(ctx: CtxConfigurator):CxfController = CxfModule.cxfController

}

object CxfModule {
  val cxfController = new CxfController

  def getCxfControllerInstance = cxfController
}
