name := "sbtTemplate"

scalaVersion := "2.13.0"

//PROJECTS SETTINGS
lazy val root = (project in file("."))
  .aggregate(content, mainApp)
  .enablePlugins(ScalastylePlugin)
  .enablePlugins(DependencyGraphPlugin)
  .enablePlugins(DockerPlugin)

lazy val content = Project(id="content", base = file("content"))

lazy val mainApp = Project(id="mainApp", base = file("mainApp"))
  .dependsOn(content)


//DOCKER SETTINGS
// standard tcp ports
dockerExposedPorts ++= Seq(9000, 9001)
// for udp ports
dockerExposedUdpPorts += 4444

addCommandAlias("run", "mainApp/run")