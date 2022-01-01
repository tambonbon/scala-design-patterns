package patterns.creational.builder

object RequireStatementBuilderPattern {
  
  case class Person(
    firstName: String = "",
    lastName: String = "",
    age: Int = 0
  ) {
    require(firstName != "", "First name is required")
    require(lastName != "", "Last name is required")
  }

  def main(args: Array[String]): Unit = {
    val tam1 = Person("Tam", "Nguyen", 24)
    val tam2 = Person("Tam", "", 24 )
  }
}
