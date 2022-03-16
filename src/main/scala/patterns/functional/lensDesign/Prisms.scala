package patterns.functional.lensDesign

// https://blog.rockthejvm.com/lens/
// Nested data structure with interfaces
// We’d like to be able to manipulate their features while still working against the main “interface”.
object Prisms {
  sealed trait Shape
  case class Circle(radius: Double) extends Shape
  case class Rectangle(w: Double, h: Double) extends Shape
  case class Triangle(a: Double, b: Double, c: Double) extends Shape

  def main(args: Array[String]): Unit = {
    val aCircle = Circle(20)
    val aRectangle = Rectangle(10, 20)
    val aTriangle = Triangle(3, 4, 5)

    val shape: Shape = aCircle

    // We'd like to increase radius of this Shape if it's a Circle, or leave it intact otherwise
    // We can do pattern matching, but what if there're dozen of shapes? How many matchings are enough..?
    // --> use Prism
    import monocle.Prism
    val circlePrism =
      Prism[Shape, Double] { // getter: Shape => Option[Double], setter: Double => Shape
        case Circle(r) => Some(r)
        case _         => None
      }(r => Circle(r))

    val circle = circlePrism(30) // returns a Shape (actually a Circle)
    val noRadius = circlePrism.getOption(
      aRectangle
    ) // will return None because that shape is not a Circle
    val radius = circlePrism.getOption(aCircle) // returns Some(20)
  }

}
