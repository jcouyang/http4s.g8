val scala = "$scala_version$"
val scalaCross = "$scala_cross_version$"
val supportedScalaVersions = List(scala,scalaCross)
val Http4sVersion = "$http4s_version$"
val FinagleVersion = "$finagle_version$"
val MunitVersion = "0.7.5"

inScope(Scope.GlobalScope)(
  List(
    organization := "$organization$",
    licenses := Seq("Apache License 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("$home_page$")),
    developers := List(
      Developer("$developer_id$", "$developer$", "$developer_email$", url("$developer_uri$"))
    ),
    scmInfo := Some(
      ScmInfo(
        url("$scm_uri$"),
        "$scm_ssh$"
      )
    ),
    pgpPublicRing := file(".") / ".gnupg" / "pubring.asc",
    pgpSecretRing := file(".") / ".gnupg" / "secring.asc",
    releaseEarlyWith := SonatypePublisher,
    scalaVersion := scala
  )
)


lazy val root = (project in file("."))
  .settings(
    name := "$name$",
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(
      "org.http4s"  %% "http4s-core" % Http4sVersion,
      "org.http4s"  %% "http4s-client" % Http4sVersion,
      "com.twitter" %% "finagle-http" % FinagleVersion,
      "org.scalameta" %% "munit" % MunitVersion % Test,
      "org.scalameta" %% "munit-scalacheck" % MunitVersion % Test,
      "org.http4s"  %% "http4s-dsl" % Http4sVersion % Test,
    ),
    testFrameworks += new TestFramework("munit.Framework"),
  )
