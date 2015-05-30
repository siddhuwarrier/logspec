package info.siddhuw.models

import org.scalatest.{Matchers, FlatSpec}
import LogLevel._


class LogEqualitySpec extends FlatSpec with Matchers {
  "A log" should "equal another log if the first message is a regex match for the second" in {
    val log = Log(DEBUG, ".*somestuff ?\\d+")
    val matchingLog = Log(DEBUG, "ahh somestuff1")
    log should equal (matchingLog)
  }

  it should "apply equality in both directions; i.e., either the LHS log msg or the RHS log msg can be a regex" in {
    val log = Log(DEBUG, ".*somestuff ?\\d+")
    val matchingLog = Log(DEBUG, "ahh somestuff1")
    matchingLog should equal (log)
  }

  it should "not fail if either of the messages is not a compileable regex" in {
    val log = Log(DEBUG, "somestuff {}")
    val matchingLog = Log(DEBUG, "somestuff {}")
    matchingLog should equal (log)
    log should equal (matchingLog)
  }

  it should "equal another log with the same args" in {
    val args = Array[AnyRef]("12", "ss")
    Log(DEBUG, "hello", Some(args)) should equal (Log(DEBUG, "hello", Some(args)))
  }

  it should "not equal another log with a different level" in {
    Log(DEBUG, "hello") should not equal Log(INFO, "hello")
  }

  it should "not equal another log with a different number of args" in {
    val firstArgs = Array[AnyRef]("12", "ss")
    val secondArgs = Array[AnyRef]("12")
    Log(DEBUG, "hello", Some(firstArgs)) should not equal Log(DEBUG, "hello", Some(secondArgs))
  }

  it should "not equal another log if the other log has no args" in {
    val args = Array[AnyRef]("12", "ss")
    Log(DEBUG, "hello", Some(args)) should not equal Log(DEBUG, "hello")
  }
}
