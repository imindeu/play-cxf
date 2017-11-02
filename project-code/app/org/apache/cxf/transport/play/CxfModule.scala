package org.apache.cxf.transport.play

import com.google.inject.{AbstractModule, Inject, Provides}

import scala.concurrent.ExecutionContext

class CxfModule @Inject()(implicit val ec: ExecutionContext) extends AbstractModule {
  def configure():Unit = {}

  @Provides
  def provideCxfController(ctx: CtxConfigurator):CxfController = CxfModule.cxfController

}

object CxfModule {
  def cxfController(implicit ec: ExecutionContext) = new CxfController

  def getCxfControllerInstance(implicit ec: ExecutionContext) = cxfController
}
