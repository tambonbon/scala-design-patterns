package patterns.structural.adapter

object AdapterForScalaPattern {
  import AdapterPattern._

  implicit class LoggerFinalImplicit(logger: LoggerFinal) extends Log {
    def info(message: String): Unit = logger.log(message, "info")
    def debug(message: String): Unit = logger.log(message, "debug")
    def warning(message: String): Unit = logger.log(message, "warning")
    def error(message: String): Unit = logger.log(message, "error")
  }
}
