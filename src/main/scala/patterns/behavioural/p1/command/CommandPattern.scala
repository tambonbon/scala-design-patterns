package patterns.behavioural.p1.command

import scala.collection.mutable.ListBuffer

/** Sometimes we want to pass information to other objects on how to perform
  * some action at later time. The objects executing our objects will be called
  * `invoker`, and it might not be even aware of the command it actually runs.
  * It just knows the interface, which is how to execute the command.
  *
  * Purpose: Encapsulate the information needed to perform an action in later
  * stage.. ..and pass the information to the object that will run the actual
  * code.
  *
  *   - Command: the interface and its implementation that are being called by
  *     the invoker
  *   - Receiver: the objects that knows how commands are execute. think of it
  *     as an object being passed to the Command and then used in the interface
  *     method
  *   - Invoker: invokes the command by inteface method. might not even know
  *     what commands are being invoked
  *   - Client: more or less guides which command are executed by using invoker
  */
object CommandPattern {
  // Imagine we have a robot that cooks. We connect it to a controller and sends command to robot.
  // `RobotCommand` and its implementations are commands.
  // `Robot` is receiver as it knows how to run all the commands
  // `RobotController` is invoker, not knowing what type of commands it executes, just run them whenever needed

  // Receiver
  class Robot {
    def cleanUp(): Unit = System.out.println("Cleaning up.")
    def pourJuice(): Unit = System.out.println("Pouring juice.")
    def makeSandwich(): Unit = System.out.println("Making a sandwich.")
  }

  // Command
  trait RobotCommand {
    def execute(): Unit
  }
  case class MakeSandwichCommand(robot: Robot) extends RobotCommand {
    def execute(): Unit = robot.makeSandwich()
  }
  case class PourJuiceCommand(robot: Robot) extends RobotCommand {
    def execute(): Unit = robot.pourJuice()
  }

  // Invoker
  class RobotController {
    val history = ListBuffer[RobotCommand]()
    
    def issueCommand(command: RobotCommand): Unit = {
      command +=: history
      command.execute()
    }

    def showHistory() = history.foreach(println(_))
  }

  def main(args: Array[String]): Unit = {
    val robot = new Robot
    val robotController = new RobotController
    robotController.issueCommand(MakeSandwichCommand(robot))
    robotController.issueCommand(PourJuiceCommand(robot))

    robotController.showHistory()

  }
}
