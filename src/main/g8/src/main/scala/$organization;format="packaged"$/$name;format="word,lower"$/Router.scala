package $organization;format="package"$.$name;format="word,lower"$

import org.http4s._
import cats.data._
import cats._
import cats.effect._

object Router {
  def route: Kleisli[Kleisli[OptionT[IO, *], Request[IO], *], Deps, Response[IO]] = ???
}
