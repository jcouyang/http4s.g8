package $organization;format="package"$.$name;format="word,lower"$
package routers

import org.http4s._
import cats.data._
import cats._
import cats.effect._

object Router {
  def route(implicit r: AppResource) = configRoute <+> jokeRoute
}
