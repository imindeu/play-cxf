// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

def propOr(name: String, value: String): String =
  (sys.props get name) orElse
  (sys.env get name) getOrElse
  value

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % propOr("play.version", "2.2.0"))
