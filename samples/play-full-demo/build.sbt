name := "play-full-demo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "eu.imind.play" %% "play-cxf_play25" % "1.3.0",
  "org.apache.cxf" % "cxf-rt-bindings-soap" % "3.1.2",
  "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.2",
  "org.springframework" % "spring-context" % "4.3.3.RELEASE",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
