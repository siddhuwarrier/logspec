package info.siddhuw.example

import com.typesafe.scalalogging.LazyLogging

class Example extends LazyLogging {
  def foo(processingTimeMs: Long): Unit = {
    logger.info("Start foo")
    processFor(processingTimeMs)
    logger.info("End foo")
  }

  private def processFor(processingTimeMs: Long): Unit = {
    logger.info("Start processing {}",new java.lang.Long(processingTimeMs))
    Thread.sleep(processingTimeMs)
    logger.info("End processing")
  }
}
