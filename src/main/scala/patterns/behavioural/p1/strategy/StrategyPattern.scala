package patterns.creational.factory.anotherExample

import com.github.tototoshi.csv.CSVReader
import java.io.InputStreamReader
import org.json4s.DefaultFormats
import org.json4s.StreamInput
import org.json4s.jackson.JsonMethods
import org.json4s._

/** Strategy Pattern enables us to define a family of algorithms, and select one
  * at runtime Somewhat similar to bridge pattern
  */
object StrategyPattern {
  // Imagine we're writing an app that needs to load data from a file, and use this data somehow
  // The file will be in various format, and based on which format we will have accordingly parsing strategy

  case class Person(name: String, age: Int, address: String)

  // We define a common interface that will parse all available different formats
  trait Parser[T] {
    def parse(file: String): List[T]
  }

  // Implementations for parsing csv
  class CSVParser extends Parser[Person] {
    override def parse(file: String): List[Person] =
      CSVReader
        .open(new InputStreamReader(this.getClass.getResourceAsStream(file)))
        .all()
        .map {
          case List(name, age, address) =>
            Person(name, age.toInt, address)
          case _ => Person(???, ???, ???)
        }
  }
  // Implementations for parsing json
  class JsonParser extends Parser[Person] {
    implicit val formats = DefaultFormats
    override def parse(file: String): List[Person] =
      ???
  }

  // We'll use factory design pattern to pick up the right implementation at run time
  object Parser {
    def apply(filename: String): Parser[Person] =
      filename match {
        case f if f.endsWith(".json") => new JsonParser
        case f if f.endsWith(".csv")  => new CSVParser
        case f => throw new RuntimeException(s"Unknown format: $f")
      }
  }

  class PersonApp[T](parser: Parser[T]) {
    def write(file: String): Unit = {
      println(s"Got the parsing file ${parser.parse(file)}")
    }
  }

  def main(args: Array[String]): Unit = {
    val csvPeople = Parser("people.csv")
    val jsonPeople = Parser("people.json")
    val applicationCsv = new PersonApp(csvPeople)
    val applicationJson = new PersonApp(jsonPeople)
    System.out.println("Using the csv: ")
    applicationCsv.write("people.csv")

  }
}
