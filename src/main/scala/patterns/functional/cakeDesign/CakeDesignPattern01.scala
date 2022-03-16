package patterns.functional.cakeDesign

/**
 * Cake pattern allows to build system as a layered components
 * ---> System = Composition of multiple components <---
 * To build a component it uses traits. Components consists of multiple layers --> cake.
 * Components are injecting to other components via "self-typed" --> Dependency Injection
 * 
 * https://medium.com/rahasak/scala-cake-pattern-e0cd894dae4e
 */
object CakeDesignPattern01 {
  
  case class Employee()

  trait EmployeeDbComp {
    val employeeDb: EmployeeDb // single entry point to EmployeeDb trait
    // EmployeeDb component is an abstract component, as we can have mult. impl. of this component...
    trait EmployeeDb { // actual database related functions comes with `EmployeeDb` trait
      def creatEmployee(employee: Employee): Unit
      def getEmployee(empID: Int): Employee
    }
  }

  // ... like this
  trait CassandraEmployeeDbComp extends EmployeeDbComp {

  // this: CakezCassandraCluster =>

  // val employeeDb = new CassandraEmployeeDb

  // class CassandraEmployeeDb extends EmployeeDb {

  //   def logger = LoggerFactory.getLogger(this.getClass)

  //   override def createEmployee(employee: Employee) = {
  //     logger.debug(s"Create employee with id: ${employee.empId} name: ${employee.name}")

  //     // cassandra based insert query
  //     val statement = QueryBuilder.insertInto("employee")
  //       .value("emp_id", employee.empId)
  //       .value("name", employee.name)
  //       .value("department", employee.department)
  //     session.execute(statement)
  //   }

  //   override def getEmployee(empId: Int): Employee = {
  //     logger.debug(s"get employee with ID: ${empId}")

  //     // cassandra based slect query
  //     val selectStmt = select().all()
  //       .from("employee")
  //       .where(QueryBuilder.eq("emp_id", empId))
  //       .limit(1)

  //     val resultSet = session.execute(selectStmt)
  //     val row = resultSet.one()

  //     Employee(row.getInt("emp_id"), row.getString("name"), row.getString("department"))
    // }
  // }

}
  
}
