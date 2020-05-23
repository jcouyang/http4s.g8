package $organization;format="package"$.$name;format="word,lower"$

import cats.effect._
import org.http4s.client.Client
import org.http4s.finagle.Finagle
import com.twitter.finagle.Http

trait HasClient {
  val client: Client[IO]
}

trait Deps extends HasConfig with HasClient

object AppResource {
  def apply(implicit ctx: ContextShift[IO]): Resource[IO, Deps] = for {
    cfg <- Resource.liftF(Config.all.load[IO])
    cl <- Finagle.mkClient[IO](Http.client.withTls(cfg.service.host.map(_.value).getOrElse(""))
      .newService(s"\${cfg.service.host}:\${cfg.service.port}"))
  } yield new Deps{
    val config = cfg
    val client = cl
  }
}
