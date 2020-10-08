package $organization;format="package"$.$name;format="word,lower"$

import scala.concurrent.ExecutionContext
import cats.effect._
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await
import com.twitter.server.TwitterServer
import com.twitter.util.logging._
import org.http4s.finagle.Finagle
import org.http4s.implicits._

object Main extends TwitterServer {
  implicit val ctx: ContextShift[IO] = IO.contextShift(ExecutionContext.global)
  val port = flag("port", ":8080", "Service Port Number")
  val resource = AppResource.apply
  def main() = {
    Resource.use { deps =>
      val service = Router.route(deps)
      val server = Http
        .server
        .withLabel("$name;format="norm"$")
        .serve(port(), Finagle.mkService[IO](service.orNotFound))
      logger.info(s"Server Started on \${port()}")
      onExit { server.close() }
      IO(Await.ready(server))
    }
  }.unsafeRunSync
}
