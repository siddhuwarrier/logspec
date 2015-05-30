package info.siddhuw.example

import info.siddhuw.logback.LogbackSpec
import info.siddhuw.models.Log
import org.scalatest.{Matchers, FlatSpec}
import info.siddhuw.models.LogLevel._

class ExampleLoggingSpec extends FlatSpec
with Matchers
with LogbackSpec {
  "The Example" should "log the following in order" in {
    val name = "Example"
    val expected = List(
      Log(INFO, "Start foo"),
      Log(INFO, "Start processing {}", Some(Array(name))),
      Log(INFO, "End processing"),
      Log(INFO, "End foo")
    )

    expectLogs(expected) {
      new Example().foo(name)
    }
  }
}
