package $organization;format="package"$.$name;format="word,lower"$
package routers

import org.http4s._
import cats.data._
import cats._
import cats.effect._

object Joke {
  def route = Kleisli { r: HasClient => HttpRoutes.of[IO] {

  }}
}
