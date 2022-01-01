package patterns.creational.builder

object TypeSafeBuilderPattern {
  // Sometimes, we need constraints to validate on dependencies of the fields
  // Lets say we need to have constraint that a Person needs to have at least a firstName or lastName defined
  // --> We'll use ADT for this purpose

  sealed trait BuildStep
  sealed trait HasFirstName extends BuildStep
  sealed trait HasLastName extends BuildStep

  class Person(val firstName: String, val lastName: String, val age: Int) {
    // since the constructor is now private, we have to make some overloads

  }

  class PersonBuilder[PassedStep <: BuildStep](
      var firstName: String,
      var lastName: String,
      var age: Int
  ) {
    protected def this() = this("", "", 0)
    protected def this(pb: PersonBuilder[_]) =
      this(pb.firstName, pb.lastName, pb.age)

    def setFirstName(firstName: String): PersonBuilder[HasFirstName] = {
      this.firstName = firstName
      new PersonBuilder[HasFirstName](this)
    }
    def setLastName(lastName: String): PersonBuilder[HasLastName] = {
      this.lastName = lastName
      new PersonBuilder[HasLastName](this)
    }
    def setAge(age: Int): PersonBuilder[PassedStep] = {
      this.age = age
      this
    }
    def build()(implicit ev: PassedStep =:= HasLastName): Person =
      new Person(firstName, lastName, age)
  }

  object PersonBuilder {
    def apply() = new PersonBuilder[BuildStep]()
  }

  def main(args: Array[String]): Unit = {
    val tam1 = PersonBuilder().setFirstName("Tam").setLastName("Nguyen").setAge(24).build() // if lack setLastName --> error
  }
  /**
   * Remarks
   * 1. Order is important
   * 2. Compiler error message is not informative
   * 3. Mutability is not recommended
   */
}
