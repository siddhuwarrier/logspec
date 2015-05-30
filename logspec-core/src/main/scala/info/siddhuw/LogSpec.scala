package info.siddhuw

import info.siddhuw.models.Log

trait LogSpec {
  def expectLogs(logs: List[Log])(testBlock: => Unit)
}
