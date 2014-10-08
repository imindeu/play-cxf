name := "play-cxf-hello"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

libraryDependencies += "org.springframework" % "spring-context" % "3.2.4.RELEASE"

libraryDependencies += "eu.imind.play" %% "play-cxf_play23" % "1.1.0"

libraryDependencies += "org.apache.cxf" % "cxf-rt-bindings-soap" % "2.7.7"

libraryDependencies += "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "2.7.7"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
