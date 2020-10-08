package $organization;format="package"$.$name;format="word,lower"$
package resource

import com.twitter.finagle.tracing.{Trace, TraceId}
import cats.Show

trait HasTracer {
  val tracer: TraceId = Trace.id
  implicit val tracerShow = Show[TraceId] { traceId =>
    traceId.traceIdHigh.map(_.toString).getOrElse("") ++ traceId.traceId.toString()
  }
}
