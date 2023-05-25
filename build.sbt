ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "2.13.10"
import sbt.Keys._
import sbt._

val baseSettings = Seq(
  resolvers ++= Seq(
    "cloudera-repo-releases" at "https://repository.cloudera.com/artifactory/repo"
  ),
  Test / logBuffered := false,
  libraryDependencies ++= baseDependencies,
  organization := "com.example"
)

val baseDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % "test",
  "org.mockito" %% "mockito-scala" % "1.17.12" % "test"
)

lazy val baselib = (project in file("base-lib"))
  .settings(baseSettings: _*)
  .settings(
    name := "base-lib",
    version := "1.0.1"
  )
  .enablePlugins(AssemblyPlugin)

lazy val liba = (project in file("lib-a"))
  .settings(baseSettings: _*)
  .settings(
    name := "lib-a",
    version := "1.0.0",
    libraryDependencies ++= baseDependencies ++ Seq(
      "com.example" %% "base-lib" % "1.0.0"
    )
  )
  .enablePlugins(AssemblyPlugin)

lazy val libb = (project in file("lib-b"))
  .settings(baseSettings: _*)
  .settings(
    name := "lib-b",
    version := "1.0.1",
    libraryDependencies ++= baseDependencies ++ Seq(
      "com.example" %% "base-lib" % "1.0.1"
    )
  )
  .enablePlugins(AssemblyPlugin)

//Shade locally
/*
lazy val shadedliba = project
  .enablePlugins(AssemblyPlugin)
  .settings(
    assembly / assemblyShadeRules := Seq(
      ShadeRule.rename("com.example.baselib.**" -> "shaded.@0").inAll
    ),
    assembly / assemblyJarName := "shaded-lib-a.jar",
    libraryDependencies ++= Seq("com.example" %% "lib-a" % "1.0.0"),
    publish / skip := true,
    addArtifact(Artifact("shaded-lib-a", "assembly"), assembly)
  )
 */

//Shade and publish
//https://github.com/sbt/sbt-assembly#q-despite-the-concerned-friends-i-still-want-publish-%C3%BCber-jars-what-advice-do-you-have
/*
lazy val shadedlibaRoot = project
  .enablePlugins(AssemblyPlugin)
  .settings(
    assembly / assemblyShadeRules := Seq(
      ShadeRule.rename("com.example.baselib.**" -> "shaded.@0").inAll
    ),
    libraryDependencies ++= Seq("com.example" %% "lib-a" % "1.0.0"),
    publish / skip := true
  )

lazy val shadedliba = project
  .settings(baseSettings: _*)
  .settings(
    name := "shaded-lib-a",
    version := "1.0.0",
    Compile / packageBin := (shadedlibaRoot / assembly).value
  )
 */

lazy val myapp = (project in file("my-app"))
  .settings(baseSettings: _*)
  .settings(
    name := "my-app",
    version := "1.0.0",
    libraryDependencies ++= baseDependencies ++ Seq(
      "com.example" %% "lib-a" % "1.0.0",
      "com.example" %% "lib-b" % "1.0.1"
    )
  )

//Shade locally
/*
lazy val myapp = (project in file("my-app"))
  .settings(baseSettings: _*)
  .settings(
    name := "my-app",
    version := "1.0.0",
    unmanagedJars in Compile ++= Seq(
      shadedliba.base / "target" / s"scala-${scalaBinaryVersion.value}" / s"shaded-lib-a.jar"
    ),
    update := (update dependsOn (shadedliba / assembly)).value,
    libraryDependencies ++= baseDependencies ++ Seq(
      "com.example" %% "lib-b" % "1.0.1"
    )
  )
 */

//Shade and publish
/*
lazy val myapp = (project in file("my-app"))
  .settings(baseSettings: _*)
  .settings(
    name := "my-app",
    version := "1.0.0",
    libraryDependencies ++= baseDependencies ++ Seq(
      "com.example" %% "shaded-lib-a" % "1.0.0",
      "com.example" %% "lib-b" % "1.0.1"
    )
  )
 */

lazy val root = (project in file("."))
  .aggregate(baselib, liba, libb, myapp)
