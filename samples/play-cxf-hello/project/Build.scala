import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "play-cxf-hello"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    anorm,
    "org.springframework" % "spring-context" % "3.2.4.RELEASE",
    "eu.imind" %% "play-cxf" % "1.0",
    "org.apache.cxf" % "cxf-rt-bindings-soap" % "2.7.7",
    "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "2.7.7"
  )

  val main = play.Project(appName, appVersion, appDependencies)

}
