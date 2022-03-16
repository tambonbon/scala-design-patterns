package patterns.functional.pimpMyLibrary

import pimp._
object PimpExample {
  def main(args: Array[String]): Unit = {
    System.out.println(s"Is 'test' all upper case: ${"test".isAllUpperCase}")
    System.out.println(s"Is 'Tes' all upper case: ${"Test".isAllUpperCase}")
    System.out.println(s"Is 'TESt' all upper case: ${"TESt".isAllUpperCase}")
    System.out.println(s"Is 'TEST' all upper case: ${"TEST".isAllUpperCase}")
  }
}
