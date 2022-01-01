package patterns.creational.factory

/** The purpose is the same as all factory design patterns—to encapsulate the
  * object creation logic and hide it from the user. The difference is how it is
  * implemented.
  *
  * The abstract factory design pattern relies on object composition in contrast
  * to inheritance .. .. which is used by the factory method. Here, we have a
  * separate object, which provides an interface to create instances of the
  * classes we need.
  */

object AbstractFactoryPattern {
  import FactoryPattern._
  trait DatabaseConnectorFactory {
    def connect(): SimpleConnection
  }

  class MySqlFactory extends DatabaseConnectorFactory {
    override def connect(): SimpleConnection = new MySQLSimpleConnection
  }

  class PgSqlFactory extends DatabaseConnectorFactory {
    override def connect(): SimpleConnection = new PostgreSQLSimpleConnection
  }

  // We can then use our factory by passing it to a class..
  // .. that will call the required methods
  class DatabaseClient(connectorFactory: DatabaseConnectorFactory) {
    def executeQuery(query: String): Unit = {
      val connection = connectorFactory.connect()
      connection.executeQuery(query)
    }
  }

  def main(args: Array[String]): Unit = {
    val clientMySql: DatabaseClient = new DatabaseClient(new MySqlFactory)
    val clientPgSql: DatabaseClient = new DatabaseClient(new PgSqlFactory)
    clientMySql.executeQuery("SELECT * FROM users")
    clientPgSql.executeQuery("SELECT * FROM employees")
  }
}
