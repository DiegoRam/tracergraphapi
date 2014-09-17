logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.4")

addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "0.99.5")