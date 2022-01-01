package patterns.creational.prototype

// We're creating objects by *cloning* them from existing ones
object PrototypePattern {
  // Use `copy` method
  case class Cell(dna: String, proteins: List[String])

  def main(args: Array[String]): Unit = {
    val iniCell = Cell("attxr", List("jrkw12"))
    val copyCell = iniCell.copy()
  }
}
