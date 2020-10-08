val scala = "$scala_version$"
val Http4sVersion = "$http4s_version$"
val MunitVersion = "0.7.11"
val TwitterVersion = "20.8.0"
val CirisVersion = "1.1.2"

lazy val root = (project in file("."))
  .settings(
    organization := "$organization$",
    name := "$name$",
    libraryDependencies ++= Seq(
      "org.http4s"  %% "http4s-core" % Http4sVersion,
      "org.http4s"  %% "http4s-client" % Http4sVersion,
      "org.http4s"  %% "http4s-dsl" % Http4sVersion,
      "org.http4s"  %% "http4s-finagle" % s"\Main$Http4sVersion+",
      "is.cir" %% "ciris" % CirisVersion,
      "is.cir" %% "ciris-enumeratum" % CirisVersion,
      "com.twitter" %% "twitter-server" % TwitterVersion,
      "org.scalameta" %% "munit" % MunitVersion % Test,
      "org.scalameta" %% "munit-scalacheck" % MunitVersion % Test,
      "org.http4s"  %% "http4s-dsl" % Http4sVersion % Test,
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full),
  )
