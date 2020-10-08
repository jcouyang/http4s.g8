package $organization;format="package"$.$name;format="word,lower"$

import cats.syntax.all._

package object route {
  val all = joke.CRUD <+> joke.random <+> config.get
}
