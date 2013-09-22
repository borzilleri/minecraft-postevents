name := "PostEventsAPI"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.inject" % "guice" % "3.0"
)

play.Project.playJavaSettings
