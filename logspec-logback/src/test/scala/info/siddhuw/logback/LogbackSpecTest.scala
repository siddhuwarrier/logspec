package info.siddhuw.logback

import com.typesafe.scalalogging.LazyLogging
import info.siddhuw.models.Log
import info.siddhuw.models.LogLevel._
import org.scalatest.{Matchers, FlatSpec}


class LogbackSpecTest extends FlatSpec
with LogbackSpec
with LazyLogging
with Matchers {
  "The LogSpec logback trait" should "verify that a list of logs were received in order" in {
    val expected = List(
      Log(DEBUG, "first-line"),
      Log(DEBUG, "second-line")
    )

    expectLogs(expected) {
      logger.debug("first-line")
      logger.debug("second-line")
    }
  }

  it should "verify logs logged at different levels of verbosity" in {
    val expected = List(
      Log(DEBUG, "first-line"),
      Log(INFO, "second-line"),
      Log(WARN, "third-line")
    )

    expectLogs(expected) {
      logger.debug("first-line")
      logger.info("second-line")
      logger.warn("third-line")
    }
  }

  it should "verify lists of logs with args" in {
    val arg = "shiznit"
    val expected = List(
      Log(DEBUG, "first-line: {}", Some(Array(arg)))
    )

    expectLogs(expected) {
      logger.debug("first-line: {}", arg)
    }
  }

  it should "not fail if the log message matches the expected regular expression" in {
    val expected = List(
      Log(DEBUG, ".*some-important-logs\\d+$")
    )

    expectLogs(expected) {
      logger.debug("This is a message with some-important-logs1234")
    }
  }

  it should "fail if the log messages do not match the regular expressions in the expected logs" in {
    val expected = List(
      Log(DEBUG, ".*some-important-logs\\d+$")
    )

    an [IllegalArgumentException] should be thrownBy expectLogs(expected) {
      logger.debug("This is a message with some-important-log")
    }

  }
  it should "fail if the logs are logged in the wrong order" in {
    val expected = List(
      Log(DEBUG, "first-line"),
      Log(INFO, "second-line"),
      Log(WARN, "third-line")
    )

    an [IllegalArgumentException] should be thrownBy expectLogs(expected) {
      logger.debug("first-line")
      logger.warn("third-line")
      logger.info("second-line")
    }
  }

  it should "fail if not all of the expected logs are actually logged" in {
    val expected = List(
      Log(DEBUG, "first-line"),
      Log(INFO, "second-line")
    )

    an[IllegalArgumentException] should be thrownBy expectLogs(expected) {
      logger.debug("first-line")
      logger.debug("something else unexpected")
    }
  }
}
