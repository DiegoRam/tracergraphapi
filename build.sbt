import play.Project._

name := "TracerGrahpWeApi"

version := "1.0"

playScalaSettings

libraryDependencies += "com.orientechnologies" % "orientdb-core" % "1.7.8"

libraryDependencies += "com.orientechnologies" % "orient-commons" % "1.7.8"

libraryDependencies += "com.tinkerpop.blueprints" % "blueprints-core" % "2.5.0"

libraryDependencies += "com.orientechnologies" % "orientdb-graphdb" % "1.7.8"

libraryDependencies += "com.ansvia.graph" % "blueprints-scala_2.10" % "0.1.39-20140917-SNAPSHOT"

libraryDependencies += "com.wordnik" %% "swagger-play2" % "1.3.3"

libraryDependencies += "com.wordnik" %% "swagger-play2-utils" % "1.3.3"
