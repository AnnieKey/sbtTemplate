


name := "sbtTemplate"

scalaVersion := "2.13.0"

//PROJECTS SETTINGS
lazy val root = (project in file("."))
  .aggregate(content, mainApp)
  .enablePlugins(ScalastylePlugin)
  .enablePlugins(DependencyGraphPlugin)
  .enablePlugins(DockerPlugin)
  //the reason for enabling JavaAppPackaging
  //[error] [1] You have no mappings defined! This will result in an empty package
  //[error] Try enabling an archetype, e.g. `enablePlugins(JavaAppPackaging)`
  .enablePlugins(JavaAppPackaging)

lazy val content = Project(id="content", base = file("content"))

lazy val mainApp = Project(id="mainApp", base = file("mainApp"))
  .dependsOn(content)


//DOCKER SETTINGS
// standard tcp ports
dockerExposedPorts ++= Seq(9000, 9001)
// for udp ports
dockerExposedUdpPorts += 4444


