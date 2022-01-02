package patterns.structural.facade

import com.typesafe.scalalogging.LazyLogging
import java.util.Base64
import org.json4s.DefaultFormats
import org.json4s.StringInput
import org.json4s
import patterns.creational.builder.BuilderPattern

/** 
 * When we build big application or library, we tend to have to know a lot of (domain) knowledge.
 * For the users, however, they are not obliged to the same amount of knowledge that we do.
 * In fact, we tend to make it simplier for them, and make sure that components of the app are easy to use.
 * 
 * Facade pattern is useful to hide complexities and ease user interacion..
 * .. by wrapping a complex system to a simplier one.
 * 
 * We've seen wrapping stuff around before
 * - Adapter: transforming one interface to another
 * - Decorator: add extra functionality
 * - Facde: make things simplier
 */
object FacadePattern {
  // Imagine we want users to download some data from a server..
  // .. and get it deserialized in the form of objects
  // The server returns our data in encoded form..
  // .. so we should decode it first, parse it ..
  // .. and finally return the right objects.
  // Too many operations, too complicated 
  // --> why not use a class at once, which handles everything?

  // First we'll have our functionalities:
  trait DataDownloader extends LazyLogging {
    def download(url: String): Array[Byte] = {
      logger.info("Downloading from: {}", url)
      Thread.sleep(5000)
      "ew0KICAgICJuYW1lIjogIkl2YW4iLA0KICAgICJhZ2UiOiAyNg0KfQ==".getBytes
      // it's equivalent to {"name":"Ivan","age":26}
    }
  }
  trait DataDecoder {
    def decode(data: Array[Byte]): String = new String(
      Base64.getDecoder().decode(data), "UTF-8"
    )
  }
  // trait DataDeserializer {
  //   implicit val formats = DefaultFormats

  //   def parse[T](data: String)(implicit m: Manifest[T]): T = 
  //     JsonMethods.parse(StringInput(data)).extract[T]
  // }
  
  // Anyone can use these these implementations, but they require some knowledge and make things complicated
  // So we have a facade class
  class DataReader extends DataDecoder with DataDownloader {
    def readPerson(url: String): String = {
      val data = download(url)
      val json = decode(data)
      json
      // clearly, we only need 1 class to exploit 3 other classes without touching their complexities
    }
  }
}
