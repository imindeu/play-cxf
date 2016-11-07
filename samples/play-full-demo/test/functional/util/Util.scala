package functional.util

import scala.xml.Utility.trim
import scala.xml.XML

object Util {

  def createTrimmedXml = trim _ compose XML.loadString
}
