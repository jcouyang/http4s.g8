package $organization;format="package"$.$name;format="word,lower"$
import ciris._
import org.http4s.UriI
import com.twitter.finagle._
import com.twitter.finagle.http._
import enumeratum._

sealed trait Env extends EnumEntry

object Env extends Enum[Env] with CirisEnum[Env] {
  case object Local extends Env
  case object PreProd extends Env
  case object Prod extends Env

  val values = findValues
}
import Env._

case class Config(
  env: Env,
  service: Uri
)

object Config {
  val appEnv = env("APP_ENV").as[Env]
  
  def all = appEnv.map {
    case Local => Config(
      env = Local,
      service = Uri.uri("https://icanhazdadjoke.com")
    )
    case PreProd => ???
    case Prod => ???
  }
}

trait HasConfig {
  val config: Config
}
