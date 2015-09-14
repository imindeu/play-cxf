name := "play-cxf-multienv"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

libraryDependencies += "org.springframework" % "spring-context" % "4.2.0.RELEASE"

libraryDependencies += "eu.imind.play" %% "play-cxf_play22" % "1.2.0"

libraryDependencies += "org.apache.cxf" % "cxf-rt-bindings-soap" % "3.1.2"

libraryDependencies += "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.2"

play.Project.playScalaSettings
