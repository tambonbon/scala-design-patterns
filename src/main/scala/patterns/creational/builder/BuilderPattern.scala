package patterns.creational.builder

/** To create instances of classes using class methods rather than class
  * constructors There are 3 ways to make builder pattern in Scala:
  *   1. Java-like pattern. This makes use of mutability and thus goes against
  *      Scala's mentality of immutable objects. 
      2. Case class with default params. We'll have 2 versions:
  *     a. One that validates params 
        b. One does not
  *   3. Generalized type constraints.
  * Builder patterns really shines when there are large number
  *      of fields
  */
object BuilderPattern {

  object JavaLike {

    class Person(builder: PersonBuilder) {
      val firstName = builder.firstName
      val lastName = builder.lastName
      val age = builder.age
    }

    class PersonBuilder() {
      var firstName = ""
      var lastName = ""
      var age = 0

      def setFirstName(firstName: String): PersonBuilder = {
        this.firstName = firstName
        this
      }
      def setLastName(lastName: String): PersonBuilder = {
        this.lastName = lastName
        this
      }
      def setAge(age: Int): PersonBuilder = {
        this.age = age
        this
      }
      def build(): Person = new Person(this)
    }
  }

  object CaseClass {
    
      case class Person(firstName: String = "", lastName: String = "", age: Int = 0)
    // drawbacks: no validation for the fields
  }

  def main(args: Array[String]): Unit = {
    // import BuilderPattern.{JavaLike, CaseClass}
    println("Java-like impl")
    val person = new JavaLike.PersonBuilder()
      .setFirstName("Tam")
      .setLastName("Nguyen")
      .setAge(24)
      .build()
    println(s"${person.firstName} ${person.lastName}, ${person.age}")

    val person1 = CaseClass.Person(firstName = "Tam", lastName = "Nguyen", age = 24)
    println("CaseClass Impl")
    println(person1)



  }
}
