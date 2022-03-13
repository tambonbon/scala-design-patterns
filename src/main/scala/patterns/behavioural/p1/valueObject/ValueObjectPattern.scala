package patterns.behavioural.p1.valueObject

/**
 * VALUE OBJECT: small, simple and immutable objects. Their equality is based not on identity, but on value equality.
 *    - they are used to represent date, money, numbers,etc.
 * In Java, there's no direct support for value object. Dev need to declare field as finals and implement `equals` and `hashCode`
 * In Scala, immutability is very well supported
 */
object ValueObjectPattern {
  case class Date(day: Int, month: String, year: Int)
  class BadDate(day: Int, month: String, year: Int)

  def main(args: Array[String]): Unit = {
    val newYear_date1 = Date(1, "january", 2022)
    val newYear_date2 = Date(1, "january", 2022)
    println(newYear_date1 == newYear_date2) // scala does everything in the background, creating hashcode, equals and toString

    val newYear_baddate1 = new BadDate(1, "january", 2022)
    val newYear_baddate2 = new BadDate(1, "january", 2022)
    println(newYear_baddate1 == newYear_baddate2)
  }
}
