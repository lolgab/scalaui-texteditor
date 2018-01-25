name := "scalaui text editor"

scalaVersion := "2.11.12"

enablePlugins(ScalaNativePlugin)

libraryDependencies += "it.lolgab" %%% "scalaui" % "0.0.1"

nativeMode := "release"
