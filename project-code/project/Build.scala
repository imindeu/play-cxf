import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "play-cxf"
  val appVersion = "1.0"

  val appDependencies = Seq(
    "org.springframework" % "spring-context" % "[3.2.0.RELEASE,)",
    "org.apache.cxf" % "cxf-api" % "2.7.7",
    "org.apache.cxf" % "cxf-rt-core" % "2.7.7"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    organization := "eu.imind"
  )

}
