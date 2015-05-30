package info.siddhuw.logback

import com.typesafe.scalalogging.LazyLogging
import info.siddhuw.models.Log
import info.siddhuw.models.LogLevel._
import org.scalatest.{Matchers, FlatSpec}

class LogbackContainsLogsSpec extends FlatSpec
with LogbackSpec
with LazyLogging
with Matchers {

  "LogbackSpec" should "verify that the logged output contains the following in order" in {
    val expectedContains = List(
      Log(DEBUG, "second-line"),
      Log(DEBUG, "third-line")
    )

    containsLogs(expectedContains) {
      logger.debug("first-line")
      logger.debug("second-line")
      logger.debug("third-line")
      logger.debug("fourth-line")
    }
  }

  it should "fail if the logged output contains the lines specified, but not consecutively" in {
    val expectedContains = List(
      Log(DEBUG, "second-line"),
      Log(DEBUG, "fourth-line")
    )

    an [IllegalArgumentException] should be thrownBy containsLogs(expectedContains) {
      logger.debug("first-line")
      logger.debug("second-line")
      logger.debug("third-line")
      logger.debug("fourth-line")
    }
  }

  it should "fail if the logged output does not contain any of the lines expected" in {
    val expectedContains = List(
      Log(DEBUG, "blooh"),
      Log(DEBUG, "bleeh")
    )

    an [IllegalArgumentException] should be thrownBy containsLogs(expectedContains) {
      logger.debug("first-line")
      logger.debug("second-line")
      logger.debug("third-line")
      logger.debug("fourth-line")
    }
  }

  it should "fail if the logged output doesn't contain some of the lines expected" in {
    val expectedContains = List(
      Log(DEBUG, "second-line"),
      Log(DEBUG, "bleeh")
    )

    an [IllegalArgumentException] should be thrownBy containsLogs(expectedContains) {
      logger.debug("first-line")
      logger.debug("second-line")
      logger.debug("third-line")
      logger.debug("fourth-line")
    }
  }
}
