val Http4sVersion = "1.0.0-M4"
val MunitVersion = "0.7.11"
val TwitterVersion = "20.8.0"
val CirisVersion = "1.1.2"
val DoobieVersion = "0.9.0"
val CirceVersion = "0.13.0"

ThisBuild / scalaVersion := "$scala_version$"
lazy val root = (project in file("."))
  .settings(
    organization := "$organization$",
    name := "$name;format="norm"$",
    libraryDependencies ++= Seq(
      "org.http4s"                 %% "http4s-blaze-client"            % Http4sVersion,
      "org.http4s"                 %% "http4s-circe"                   % Http4sVersion,
      "org.http4s"                 %% "http4s-dsl"                     % Http4sVersion,
      "org.http4s"                 %% "http4s-finagle"                 % Http4sVersion,
      "io.circe"                   %% "circe-generic"                  % CirceVersion,
      "io.circe"                   %% "circe-literal"                  % CirceVersion,
      "org.tpolecat"               %% "doobie-postgres"                % DoobieVersion,
      "org.tpolecat"               %% "doobie-quill"                   % DoobieVersion,
      "org.tpolecat"               %% "doobie-hikari"                  % DoobieVersion,
      "com.twitter"                %% "twitter-server"                 % TwitterVersion,
      "com.twitter"                %% "twitter-server-logback-classic" % TwitterVersion,
      "com.twitter"                %% "finagle-stats"                  % TwitterVersion,
      "us.oyanglul"                %% "dhall-generic"                  % "0.2.0",
      "org.dhallj"                 %% "dhall-imports"                  % "0.4.0",
      "com.samstarling"            %% "finagle-prometheus"             % "0.0.15",
      "ch.qos.logback"              % "logback-classic"                % "1.2.3",
      "io.zipkin.finagle2"         %% "zipkin-finagle-http"            % "2.2.1",
      "org.scalameta"              %% "munit"                          % MunitVersion % Test,
      "org.scalameta"              %% "munit-scalacheck"               % MunitVersion % Test,
      "org.mockito"                %% "mockito-scala"                  % "1.15.0"     % Test,
      "com.github.alexarchambault" %% "scalacheck-shapeless_1.14"      % "1.2.3"      % Test,
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full),
    addCompilerPlugin(scalafixSemanticdb),
    addCommandAlias(
      "rmUnused",
      """set scalacOptions -= "-Xfatal-warnings";scalafix RemoveUnused;set scalacOptions += "-Xfatal-warnings"""",
    ),
  )

lazy val db = project
  .settings(
    organization := "$organization$",
    name := "$name;format="norm"$-db-migration",
    libraryDependencies ++= Seq(
      "org.flywaydb"  % "flyway-core"     % "6.5.5",
      "org.tpolecat" %% "doobie-core"     % DoobieVersion,
      "org.tpolecat" %% "doobie-postgres" % DoobieVersion,
    ),
  )
