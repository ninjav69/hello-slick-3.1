import slick.driver.MySQLDriver.api._
import slick.lifted.{ProvenShape, Rep, Tag}

/**
 * Created by ninjav on 11/22/16.
 */
class Person(tag: Tag) extends Table[(Int, String, String, String, String)](tag, "PERSON") {

  def id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("NAME")
  def surname: Rep[String] = column[String]("SURNAME")
  def email: Rep[String] = column[String]("EMAIL")
  def mobile: Rep[String] = column[String]("MOBILE")

  def * : ProvenShape[(Int, String, String, String, String)] =
    (id, name, surname, email, mobile)
}
