package patterns.behavioural.p1.command

import scala.collection.mutable.ListBuffer

/**
 * This time, we'll use `by-name-parameters`, or passing functions as parameters
 */
object CommandPatternScala {
  import CommandPattern._

  class RobotControllerByName {
    val history = ListBuffer[() => Unit]()
    
    def issueCommand(command: => Unit): Unit = { // we don't pass actual command object, just by-name param 
    // so, this methods defers a call to whatever retrives the value passed, until it's really needed
      command _ +=: history
      command
    }

    def showHistory() = history.foreach(println(_))
  }

  def main(args: Array[String]): Unit = {
    val robot = new Robot
    val robotController = new RobotControllerByName

    robotController.issueCommand(MakeSandwichCommand(robot).execute())
    // by-name param is useful when we dont want to write extra code for command interface and its implementation
  }
}
