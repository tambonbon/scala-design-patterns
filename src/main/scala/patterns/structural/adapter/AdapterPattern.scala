package patterns.structural.adapter

/**
  * We often have to make several components work together.
  * However, not everytime those components are compatible with each other
  * --> Adapter patterns helps incompatible interfaces work with each other without modifying source code
  */
object AdapterPattern {
  // Suppose we want to write the log message that takes the message, and a severity
  // Of course we cannot change the source, so we use adapter pattern
  
  // Suppose we cannot change this
  class Logger {
    def log(message: String, severity: String): Unit = 
      println(s"${severity.toUpperCase}: $message")
  }

  // Next, we provide the interface for abstraction
  trait Log {
    def info(message: String): Unit
    def debug(message: String): Unit
    def warning(message: String): Unit
    def error(message: String): Unit
  }

  // Next, we implement them
  class AppLogger extends Logger with Log { // this is our ADAPTER
    def info(message: String): Unit = log(message, "info")
    def debug(message: String): Unit = log(message, "debug")
    def warning(message: String): Unit = log(message, "warning")
    def error(message: String): Unit = log(message, "error")
  }

  def main(args: Array[String]): Unit = {
    val logger = new AppLogger // we dont need the Logger instance
    logger.info("this is an info")
    // Remarks:
    // This is basic adapter pattern
    // However, there are other cases where the class we want to adapt is `final` and we are unable to extend it
  }

  final class LoggerFinal {
    def log(message: String, severity: String): Unit = 
      println(s"${severity.toUpperCase}: $message")    
  }

  class AppLoggerFinal extends Log {
    // if we cannot extend the final class Logger..
    // .. we need to wrap the final class inside a class and then use it to call the log method with different params
    private val logger = new LoggerFinal

    def info(message: String): Unit = logger.log(message, "info")
    def debug(message: String): Unit = logger.log(message, "debug")
    def warning(message: String): Unit = logger.log(message, "warning")
    def error(message: String): Unit = logger.log(message, "error")
  }
}
