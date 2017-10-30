name := "play-full-demo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.3"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

libraryDependencies ++= Seq(
  "eu.imind.play" %% "play-cxf_play26" % "1.3.0",
  "org.apache.cxf" % "cxf-rt-bindings-soap" % "3.1.2",
  "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.2",
  "org.springframework" % "spring-context" % "5.0.1.RELEASE",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.scalactic" %% "scalactic" % "3.0.4" % Test,
  "org.scalatest" %% "scalatest" % "3.0.4" % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
