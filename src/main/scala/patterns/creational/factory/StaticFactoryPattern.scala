package patterns.creational.factory

object StaticFactoryPattern {
  // drawback: if another extension of base class is added --> that base class has to be modified as well
  trait Animal
  class Bird extends Animal
  class Mammal extends Animal
  class Fish extends Animal
  // here, everytime we add another class extending Animal, we need to change the object companion too
  
  object Animal {
    def apply(animal: String): Animal = animal.toLowerCase match {
      case "bird"    => new Bird
      case "mammal"  => new Mammal
      case "fish"    => new Fish
      case x: String => throw new RuntimeException(s"Unknown animal: $x")
    }
  }
}
