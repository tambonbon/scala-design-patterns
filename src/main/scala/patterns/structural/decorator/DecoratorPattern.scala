package patterns.structural.decorator

import java.io.BufferedReader
import collection.JavaConverters._
import java.util.Base64
import java.nio.charset.Charset
import java.io.InputStreamReader
import java.io.BufferedInputStream
import java.io.Reader
import java.io.FileReader
/**
 * Sometimes we want to add new functionality to existing code, but we dont want to use inheritance
 * --> Decorator pattern: to add functionality to objects 
 *    1. without extending them
 *    2. without affecting behaviours of other objects in the same class
 * 
 * Decorator pattern works by wrapping the decorated objects, in runtime
 * Useful when multiple extensions of a class and they could be combined
 * .. instead of writing all possible combination..
 * .. decorators can be created and stack the modifications on top of each other
 */
object DecoratorPattern {
  
  // First, we define the trait
  trait InputReader {
    def readLines(): Stream[String]
  }

  // Then we provide basic impl
  class AdvancedInputReader(reader: BufferedReader) extends InputReader {
    def readLines(): Stream[String] = reader.lines().iterator().asScala.toStream
  }

  // Create Decorator class
  abstract class DecoratorInputReader(inputReader: InputReader) extends InputReader {
    def readLines(): Stream[String] = inputReader.readLines()
  }

  // Then we have several implementations of our decorator
  class CapitalizedInputReader(inputReader: InputReader) extends DecoratorInputReader(inputReader) {
    override def readLines(): Stream[String] = inputReader.readLines().map(_.toUpperCase())
  }
  class Base64InputReader(inputReader: InputReader) extends DecoratorInputReader(inputReader) {
    override def readLines(): Stream[String] = super.readLines().map {
      case line => Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8")))
    }
  }

  // Now we can use these decorators to add extra functionalities to our input stream as needed
  def main(args: Array[String]): Unit = {
    val stream = new BufferedReader(new FileReader("src/main/scala/patterns/structural/decorator/deco.txt"))
    try {
      val reader = new CapitalizedInputReader(new Base64InputReader(new AdvancedInputReader(stream))) //note we're wrapping decorator class
      reader.readLines().foreach(println(_))
    } finally {
      stream.close()
    }
  }
}
