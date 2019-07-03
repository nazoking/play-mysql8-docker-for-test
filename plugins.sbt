resolvers ++= Seq(
  "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.16"
)

// scalariform
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

// Use the Play sbt plugin for Play projects https://playframework.com
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.0")
addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "3.3.+")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.12")
addSbtPlugin("eu.unicredit" % "sbt-swagger-codegen" % "0.0.11")
