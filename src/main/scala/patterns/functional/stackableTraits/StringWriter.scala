package patterns.functional.stackableTraits

trait StringWriter { // base
  def write(data: String): String
}

class BasicStringWriter extends StringWriter { // basic impl
  def write(data: String): String = s"Writing the following data: $data"
}

trait CapitalizingStringWriter extends StringWriter { // stackable traits
  abstract override def write(data: String): String = {
    super.write(data.split("\\s").map(_.capitalize).mkString(""))
  }
}

trait UppercasingStringWriter extends StringWriter { // stackable traits
  abstract override def write(data: String): String = {
    super.write(data.toUpperCase())
  }
}

trait LowercasingStringWriter extends StringWriter { // stackable traits
  abstract override def write(data: String): String = {
    super.write(data.toLowerCase())
  }
}

/**
 * Remarks: Magic happens because of abstract override --> allows us to call `super` on an abstract method of `super` class
 */

object StringWriterMain {
  def main(args: Array[String]): Unit = {
    val writer1 = new BasicStringWriter
      with UppercasingStringWriter // TODO: compare with structural patterns/read again
      with CapitalizingStringWriter // evaluated from right -> left 
    val writer2 = new BasicStringWriter
      with CapitalizingStringWriter
      with LowercasingStringWriter
    val writer3 = new BasicStringWriter
      with CapitalizingStringWriter
      with UppercasingStringWriter
      with LowercasingStringWriter
    val writer4 = new BasicStringWriter
      with CapitalizingStringWriter
      with LowercasingStringWriter
      with UppercasingStringWriter

    println(s"Writer 1: ${writer1.write("We like learning Scala!")}")   
    println(s"Writer 2: ${writer2.write("We like learning Scala!")}")   
    println(s"Writer 3: ${writer3.write("We like learning Scala!")}")   
    println(s"Writer 4: ${writer4.write("We like learning Scala!")}")   
  }
}