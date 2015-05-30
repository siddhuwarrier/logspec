package info.siddhuw.models

import ch.qos.logback.classic.Level
import org.scalatest.{Matchers, FlatSpec}


class LogLevelFactorySpec extends FlatSpec with Matchers {
  "The LogLevel Factory (apply method)" should "build a LogLevel of DEBUG from a Logback DEBUG logging level" in {
    LogLevel(Level.DEBUG) should equal(LogLevel.DEBUG)
  }

  it should "build a LogLevel of INFO from a Logback INFO logging level" in {
    LogLevel(Level.INFO) should equal(LogLevel.INFO)
  }

  it should "build a LogLevel of WARN from a Logback WARN logging level" in {
    LogLevel(Level.WARN) should equal(LogLevel.WARN)
  }

  it should "build a LogLevel of TRACE from a Logback TRACE logging level" in {
    LogLevel(Level.TRACE) should equal(LogLevel.TRACE)
  }

  it should "build a LogLevel of ERROR from a Logback ERROR logging level" in {
    LogLevel(Level.ERROR) should equal(LogLevel.ERROR)
  }

  it should "fail to build a LogLevel if there is no equivalent for the Logback logging level" in {
    an [IllegalArgumentException] should be thrownBy LogLevel(Level.ALL)
  }
}
