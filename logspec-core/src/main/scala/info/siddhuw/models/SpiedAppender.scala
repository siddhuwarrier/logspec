package info.siddhuw.models

import scala.collection.mutable

trait SpiedAppender {
  def receivedLogs: mutable.ListBuffer[Log]
}
