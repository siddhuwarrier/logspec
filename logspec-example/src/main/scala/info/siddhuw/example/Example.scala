package info.siddhuw.example

import com.typesafe.scalalogging.LazyLogging

class Example extends LazyLogging {
  private val SleepyTime = 1000L
  def foo(name: String): Unit = {
    logger.info("Start foo")
    processFor(name)
    logger.info("End foo")
  }

  private def processFor(name: String): Unit = {
    logger.info("Start processing {}",name)
    Thread.sleep(SleepyTime)
    logger.info("End processing")
  }
}
