scalaVersion := play.core.PlayVersion.scalaVersion

libraryDependencies ++= Seq(
  guice, ws, logback, jdbc, evolutions, filters, cacheApi, jcache,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3",
  "javax.mail" % "mail" % "1.4.5",
  "mysql" % "mysql-connector-java" % "8.0.16",
  "org.mindrot" % "jbcrypt" % "0.4",
  "com.typesafe.play" %% "play-test" % _root_.play.core.PlayVersion.current % Test,
  "org.scalikejdbc" %% "scalikejdbc-test" % "3.3.4",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.4",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.7.0-scalikejdbc-3.3",
  "org.scalatest" %% "scalatest" % "3.0.7",
  "org.scalikejdbc" %% "scalikejdbc-play-fixture" % "2.7.0-scalikejdbc-3.3",
  "org.slf4j" % "slf4j-api" % "1.7.26",
  "org.scalamock" %% "scalamock" % "4.2.0" % Test,
  "org.jvnet.mock-javamail" % "mock-javamail" % "1.9" % Test)
