package patterns.creational.factory.anotherExample

sealed trait Animal {
  def speak(): Unit
}

sealed trait Dog extends Animal
private class LittleDog extends Dog {
  def speak(): Unit = println("ang ang")
}
private class GenericDog extends Dog {
  def speak(): Unit = println("Woof")
}

sealed trait Cat extends Animal
private class GrumpyCat extends Cat {
  def speak(): Unit = println("meomeo")
}
private class GenericCat extends Cat {
  def speak(): Unit = println("Meoww")
}

/* Animal Factory classes */
trait  AnimalFactory {
  def getAnimal(criteria: String): Animal
}

class DogFactory extends AnimalFactory {
  def getAnimal(criteria: String): Animal = criteria match {
    case "little" | "small" => new LittleDog
    case "generic" => new GenericDog
  }
}

class CatFactory extends AnimalFactory {
  def getAnimal(criteria: String): Animal = criteria match {
    case "grumpy" => new GrumpyCat
    case _ => new GenericCat
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val dogFactory: AnimalFactory = new DogFactory
    val smallDog = dogFactory.getAnimal("small")
    smallDog.speak()
  }
}
