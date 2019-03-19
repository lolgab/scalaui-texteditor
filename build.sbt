name := "scalaui text editor"

scalaVersion := "2.11.12"

enablePlugins(ScalaNativePlugin)

libraryDependencies += "it.lolgab" %%% "scalaui" % "0.0.1"
libraryDependencies += "com.outr" %%% "reactify" % "3.0.3"

nativeMode := "release"
