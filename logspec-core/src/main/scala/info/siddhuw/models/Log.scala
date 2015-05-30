package info.siddhuw.models

import java.util.regex.{PatternSyntaxException, Pattern}

import LogLevel.LogLevel
import ch.qos.logback.classic.Level

case class Log(level: LogLevel, msg: String, args: Option[Array[AnyRef]] = None) {
  override def equals(o: Any) = o match {
    case other: Log =>
      other.level == level &&
        compareMsgs(msg, other.msg) &&
        args.getOrElse(Array[AnyRef]()).toList == other.args.getOrElse(Array[AnyRef]()).toList
    case _ =>
      false
  }

  private def compareMsgs(msg: String, otherMsg: String): Boolean = {
    val trimmedMsg = msg.replaceAll("[\\{\\}]", "").trim
    val otherTrimmedMsg = otherMsg.replaceAll("[\\{\\}]", "").trim
    (try {
      Pattern.matches(trimmedMsg, otherTrimmedMsg)
    }
    catch {
      case _: PatternSyntaxException =>
        trimmedMsg == otherTrimmedMsg
    }) || (try {
      Pattern.matches(otherTrimmedMsg, trimmedMsg)
    }
    catch {
      case _: PatternSyntaxException =>
        false
    })
  }
}

object LogLevel extends Enumeration {
  type LogLevel = Value
  val TRACE, DEBUG, INFO, WARN, ERROR = Value

  def apply(logbackLevel: Level): LogLevel = {
    logbackLevel match {
      case Level.TRACE =>
        TRACE
      case Level.DEBUG =>
        DEBUG
      case Level.INFO =>
        INFO
      case Level.WARN =>
        WARN
      case Level.ERROR =>
        ERROR
      case _ =>
        throw new IllegalArgumentException(s"Unrecognised loglevel $logbackLevel")
    }
  }
}