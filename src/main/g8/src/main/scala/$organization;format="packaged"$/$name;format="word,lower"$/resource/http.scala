package $organization;format="package"$.$name;format="word,lower"$
package resource

import cats.effect._
import org.http4s._
import org.http4s.client.Client
import org.http4s.finagle.Finagle
import com.twitter.finagle.Http
import org.http4s.Uri.Scheme

trait HasClient {
  val jokeClient: Client[IO]
}

object http {
  def mk(uri: Uri)(implicit ctx: ContextShift[IO]): Resource[IO, Client[IO]] =
    (uri.scheme, uri.host, uri.port) match {
      case (Some(Scheme.https), Some(host), None) =>
        Finagle.mkClient[IO](
          Http.client.withHttp2
            .withTls(host.value)
            .withHttpStats
            .withStatsReceiver(PrometheusExporter.metricStatsReceiver)
            .newService(s"\$host:443")
        )
      case (Some(Scheme.https), Some(host), Some(port)) =>
        Finagle.mkClient[IO](
          Http.client.withHttp2
            .withTls(host.value)
            .withHttpStats
            .withStatsReceiver(PrometheusExporter.metricStatsReceiver)
            .newService(s"\$host:\$port")
        )
      case (_, Some(host), Some(port)) =>
        Finagle.mkClient[IO](
          Http.client.withHttpStats.withHttp2
            .withStatsReceiver(PrometheusExporter.metricStatsReceiver)
            .newService(s"\$host:\$port")
        )
      case _ =>
        Resource.liftF(IO.raiseError(new Exception(s"cannot initialize HttpClient for \$uri")))
    }
}
