package org.apache.cxf.transport.play

import org.springframework.context.support.ClassPathXmlApplicationContext

abstract class CtxConfigurator {
  def ctx:ClassPathXmlApplicationContext
}
