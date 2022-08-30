name := """restaurant-apis"""
organization := "com.samiul"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

// newly added
//libraryDependencies ++= Seq(
//  "com.typesafe.play" %% "play-slick" % "3.0.0",
//  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0",
//  "com.h2database" % "h2" % "1.4.196"
//)


// from VDO
libraryDependencies ++= Seq(
  // "com.vmunier" %% "scalajs-scripts" % "1.1.2",
  // "com.meuniere" %% "scalajs-scripts" % "1.1.2",
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.postgresql" % "postgresql" % "42.2.11",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "org.mindrot" % "jbcrypt" % "0.4",
  "com.typesafe.play" %% "play-slick-evolutions" % "4.0.2",
  // "org.scalactic" %% "scalactic" % "3.0.1",
  // "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  specs2 % Test
)

// libraryDependencies += guice
// libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
// libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.samiul.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.samiul.binders._"
