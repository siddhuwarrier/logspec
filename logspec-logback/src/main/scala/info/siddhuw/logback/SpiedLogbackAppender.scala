package info.siddhuw.logback

import java.util

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.filter.Filter
import ch.qos.logback.core.spi.FilterReply
import ch.qos.logback.core.{Context, Appender}
import ch.qos.logback.core.status.Status
import info.siddhuw.models.{LogLevel, Log, SpiedAppender}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.language.implicitConversions



class SpiedLogbackAppender extends
SpiedAppender
with Appender[ILoggingEvent] {
  val receivedLogs: mutable.ListBuffer[Log] = new ListBuffer[Log]()

  implicit def iLoggingEventToLog(event: ILoggingEvent): Log = {
    Log(LogLevel(event.getLevel), event.getMessage, Option(event.getArgumentArray))
  }

  override def doAppend(e: ILoggingEvent): Unit = receivedLogs += e

  // -- methods we do not need to implement --
  override def getName: String = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def setName(s: String): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addInfo(s: String): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addInfo(s: String, throwable: Throwable): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addWarn(s: String): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addWarn(s: String, throwable: Throwable): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addError(s: String): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addError(s: String, throwable: Throwable): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addStatus(status: Status): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def getContext: Context = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def setContext(context: Context): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def getCopyOfAttachedFiltersList: util.List[Filter[ILoggingEvent]] = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def getFilterChainDecision(e: ILoggingEvent): FilterReply = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def addFilter(filter: Filter[ILoggingEvent]): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def clearAllFilters(): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def stop(): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def isStarted: Boolean = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")

  override def start(): Unit = throw new NotImplementedException("Spying Stubbed LogbackAppender does not implement this method")
}
