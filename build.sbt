//GENERAL SETTINGS------------------------------------------------------------------------------------------------------
name := "sbtTemplate"
scalaVersion := "2.13.0"


//PROJECTS SETTINGS-----------------------------------------------------------------------------------------------------
lazy val root = (project in file("."))
  .aggregate(content, mainApp)
  .enablePlugins(ScalastylePlugin)
  .enablePlugins(DependencyGraphPlugin)
  .settings(
    publishTo := Some(Resolver.file("sbtTemplate",  new File( "/sbtTemplate" )) ),
    skip in publish := true
  )

lazy val content = Project(id="content", base = file("content"))

lazy val mainApp = Project(id="mainApp", base = file("mainApp"))
  .dependsOn(content)
  .enablePlugins(DockerPlugin)
  //the reason for enabling JavaAppPackaging
  //[error] [1] You have no mappings defined! This will result in an empty package
  //[error] Try enabling an archetype, e.g. `enablePlugins(JavaAppPackaging)`
  .enablePlugins(JavaAppPackaging)
  .settings(
      dockerSettings
  )
  mainClass in (Compile, packageBin) := Some("Main")


//DOCKER SETTINGS-------------------------------------------------------------------------------------------------------
lazy val dockerSettings = Seq(
  dockerExposedPorts ++= Seq(9000, 9001),
  dockerExposedUdpPorts += 4444
)