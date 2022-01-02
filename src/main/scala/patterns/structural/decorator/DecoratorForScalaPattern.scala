package patterns.structural.decorator

import java.util.Base64
import java.nio.charset.Charset
import java.io.BufferedReader
import java.io.FileReader

// The Decorator design patterns in scala is also called Stackable Traits
object DecoratorForScalaPattern {
  import DecoratorPattern._

  // Instead of defining abstract decorator class, we will define different modifications for new traits
  trait CapitalizedInputReaderTrait extends InputReader {
    abstract override def readLines(): Stream[String] =
      // Abstract override allows us to call `super` for a method in a trait..
      // .. that is declared abstract
      // This is permissible for traits as long as the trait is mixed in after another trait
      // or a class that implements the preceding method
      super.readLines().map(_.toUpperCase())
  }
  trait Base64InputReaderTrait extends InputReader {
    abstract override def readLines(): Stream[String] =
      super.readLines().map { case line =>
        Base64.getEncoder
          .encodeToString(line.getBytes(Charset.forName("UTF-8")))
      }
  }

  def main(args: Array[String]): Unit = {
    val stream = new BufferedReader(
      new FileReader("src/main/scala/patterns/structural/decorator/deco.txt")
    )
    try {
      val reader = new AdvancedInputReader(stream) with Base64InputReaderTrait with CapitalizedInputReaderTrait // note the inverse order 
      reader.readLines().foreach(println(_))
    } finally {
      stream.close()
    }
  }
}
