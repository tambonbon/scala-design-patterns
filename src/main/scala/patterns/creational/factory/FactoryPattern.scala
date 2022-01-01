package patterns.creational.factory

/** Factory pattern encapsulate an actual class instantiation. It simply
  * provides an interface to create an object, and then the subclasses of the
  * factory .. decide which concrete class to instantiate. This design pattern
  * could become .. useful in cases where we want to create different objects
  * during the runtime of the application. This design pattern is also helpful
  * when object creation would otherwise require extra parameters to be passed
  * by the developer.
  */
object FactoryPattern {
  // We'll define own implementation for `SimpleConnection` for MySQL and PostGreSQL
  // Why use?
  // Because creating connection depend on the type of database we're using, but implementation stay the same!

  trait SimpleConnection {
    def getName(): String
    def executeQuery(query: String): Unit
  }

  class MySQLSimpleConnection extends SimpleConnection {
    def getName(): String = s"MySQL"
    def executeQuery(query: String): Unit = println(s"$query")
  }
  class PostgreSQLSimpleConnection extends SimpleConnection {
    def getName(): String = s"PostgreSQL"
    def executeQuery(query: String): Unit = println(s"$query")
  }

  // We will use these implementations in our factory method, called "connect"
  abstract class DatabaseClient {
    def executeQuery(query: String): Unit = {
      val connection = connect()
      connection.executeQuery(query)
    }
    protected def connect(): SimpleConnection // factory method
  }

  class MySQLClient extends DatabaseClient {
    protected def connect(): SimpleConnection = new MySQLSimpleConnection
  }

  class PostgreSQLClient extends DatabaseClient {
    protected def connect(): SimpleConnection = new PostgreSQLSimpleConnection
  }

  // Using is simple
  def main(args: Array[String]): Unit = {
    val clientMySql: DatabaseClient = new MySQLClient
    val clientPgSql: DatabaseClient = new PostgreSQLClient
    clientMySql.executeQuery("SELECT * FROM users")
    clientPgSql.executeQuery("SELECT * FROM employees")
  }
}
