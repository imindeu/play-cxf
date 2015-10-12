name := "play-cxf-hello"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies += "org.springframework" % "spring-context" % "4.2.0.RELEASE"

libraryDependencies += "eu.imind.play" %% "play-cxf_play24" % "1.2.1"

libraryDependencies += "org.apache.cxf" % "cxf-rt-bindings-soap" % "3.1.2"

libraryDependencies += "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.2"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
