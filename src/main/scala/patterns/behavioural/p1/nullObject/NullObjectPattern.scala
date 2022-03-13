package patterns.behavioural.p1.nullObject

/**
  * Purpose of Null Value Object Pattern: define an actual object representing the null value, and make it neutral.
  * Using null objects removes the need of checking whether something is set to null or not.
  */
object NullObjectPattern {
  // Imagine we have a system having a poll to queue for messages..
  // ..of course, not all message having content, so it might be null..
  // .. instead of checking null, we will check whether it equals to a actual value having empty behaviour

  // Okay, just use Option....
}
