scalaVersion := play.core.PlayVersion.scalaVersion

libraryDependencies ++= Seq(
  guice, ws, logback, jdbc, evolutions, filters, cacheApi, jcache)
