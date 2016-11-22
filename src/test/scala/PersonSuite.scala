import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Span, Seconds}
import org.scalatest.{BeforeAndAfter, FunSuite}
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery
import slick.driver.MySQLDriver.api._

/**
 * Created by ninjav on 11/22/16.
 */
class PersonSuite extends FunSuite with BeforeAndAfter with ScalaFutures {
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  val persons = TableQuery[Person]

  var db: Database = _

  def createSchema() =
    db.run((persons.schema).create).futureValue

  def insertPerson() =
    db.run(persons += (1, "Alan", "Pickard", "al.ninjav@gmail.com", "0820636769")).futureValue

  before { db = Database.forConfig("mydb") }

  test("Creating the schema works") {
    createSchema()

    val tables = db.run(MTable.getTables).futureValue

    assert(tables.size == 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("person")) == 1)
  }

  test("Inserting a person works") {
    createSchema()

    val insertCount = insertPerson()
    assert(insertCount == 1)
  }
}
