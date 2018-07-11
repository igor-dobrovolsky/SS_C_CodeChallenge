name := "ss-c-coding-challenge"

version := "0.0.1"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test exclude("junit", "junit-dep")
)

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.1"
//libraryDependencies += "org.apache.commons" % "commons-collections4" % "4.1"
