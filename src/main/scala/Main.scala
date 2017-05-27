import java.io.InputStream

import scala.io.Source

import net.liftweb.json.{DefaultFormats, parse}


object Main {

  /** args(0): to (gmail)
    * args(1): from (gmail)
    * args(2): password
    */
  def main(args: Array[String]): Unit = {

    implicit val formats = DefaultFormats

    val fileStream: InputStream = getClass.getResourceAsStream("/birthdays.txt")

    val people: Iterator[Person] = Source
      .fromInputStream(fileStream)
      .getLines
      .map(person => parse(person).extract[Person])

    val content: String = people.mkString("\n")

    val mail = new MailAgent(
      to = args(0),
      cc = null,
      bcc = null,
      from = args(1),
      password = args(2),
      subject = "TEST LOCALHOST",
      content = content)

    mail.sendMessage()

    println("done")
  }
}
