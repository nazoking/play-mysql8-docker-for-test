resolvers ++= Seq(
  "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.16"
)
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.12")
