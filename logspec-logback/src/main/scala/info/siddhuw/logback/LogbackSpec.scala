package info.siddhuw.logback

import ch.qos.logback.classic.{Logger => LogbackLogger}
import info.siddhuw.LogSpec
import info.siddhuw.models.Log
import org.slf4j.{Logger, LoggerFactory}

trait LogbackSpec extends LogSpec {
  override def expectLogs(expected: List[Log])(testBlock: => Unit): Unit = {
    val actual = spyOnLogs(testBlock)
    require(actual == expected, s"Actual logs $actual do not match expected logs $expected")
  }


  private def spyOnLogs(testBlock: => Unit): List[Log] = {
    val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME).asInstanceOf[LogbackLogger]
    val spiedAppender = new SpiedLogbackAppender

    try {
      rootLogger.addAppender(spiedAppender)
      testBlock
    }
    finally {
      rootLogger.detachAppender(spiedAppender)
    }

    spiedAppender.receivedLogs.toList
  }
}
