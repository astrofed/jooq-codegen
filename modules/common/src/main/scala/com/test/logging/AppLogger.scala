package com.test.logging

import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by <Chekhonadskikh, Fedor> on 12/14/18 17:42 AM
  */
trait AppLogger {

  private lazy val logger: Logger = LoggerFactory.getLogger(this.getClass)

  protected def info(message: => String): Unit =
    if (logger.isInfoEnabled)
      logger.info(message)

  protected def warn(message: => String): Unit =
    if (logger.isWarnEnabled)
      logger.warn(message)

  protected def debug(message: => String): Unit =
    if (logger.isDebugEnabled)
      logger.debug(message)

  protected def trace(message: => String): Unit =
    if (logger.isTraceEnabled)
      logger.trace(message)

  protected def error(message: => String): Unit =
    if (logger.isErrorEnabled)
      logger.error(message)

  protected def error(message: => String, throwable: Throwable): Unit =
    if (logger.isErrorEnabled)
      logger.error(message, throwable)

  protected def ticked[R](note: => String)(code: => R): R =
    if (logger.isTraceEnabled) {
      val start = System.nanoTime()
      val result = code
      logger.trace(s"$note took ${(System.nanoTime() - start) / 1e6} ms")
      result
    } else
      code

  protected def timed[R](note: => String)(code: => R): R = {
    val start = System.nanoTime()
    val result = code
    trace(s"$note with result: $result")
    debug(s"$note took ${(System.nanoTime() - start) / 1e6} ms")
    result
  }

  protected def timedF[T](msg: String)(f: => Future[T])(implicit ec: ExecutionContext): Future[T] =
    Future.successful(
      if (logger.isDebugEnabled || logger.isTraceEnabled)
        Some(System.nanoTime())
      else
        None
    )
      .flatMap {
        case None =>
          f
        case Some(start) =>
          debug(s"$msg started")

          f
            .map {
              result =>
                trace(s"$msg with result: $result")

                debug(s"$msg finished (took ${(System.nanoTime() - start) / 1e6} ms)")

                result
            }
            .recover {
              case e: Throwable =>
                if (logger.isTraceEnabled)
                  error(s"$msg finished (took ${(System.nanoTime() - start) / 1e6} ms) thrown exception ", e)
                else
                  debug(s"$msg finished (took ${(System.nanoTime() - start) / 1e6} ms) thrown exception: ${e.getMessage}")

                throw e
            }

      }

}
