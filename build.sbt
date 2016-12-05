name := """OLabsShop"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "org.postgresql" % "postgresql" % "9.4.1209.jre7",
  "org.flywaydb" %% "flyway-play" % "3.0.1"
)

