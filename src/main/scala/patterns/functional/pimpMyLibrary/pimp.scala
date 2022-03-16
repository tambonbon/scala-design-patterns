package patterns.functional.pimpMyLibrary

/**
 * Imagine we want to add some useful methods to standard String class
 */
package object pimp { // package object -> not do anything extra to be able to access its member from the classes in the same package in Scala
  implicit class StringExtensions(val s: String) extends AnyVal { // this is pimp my library design patter..
    def isAllUpperCase: Boolean =  // .. note: the class is implicit, and it extends AnyVal
      !(0 until s.length).exists{
        case index => s.charAt(index).isLower
      }
  }
}
