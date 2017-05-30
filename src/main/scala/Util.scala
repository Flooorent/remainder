import java.io.InputStream
import scala.io.Source
import net.liftweb.json.{DefaultFormats, parse}


object Util {

  val birthdaysPath: String = "/birthdays.txt" // where birthdays are stored


  /**
    * @param birthdaysPath: file path to people with their birthdays in json format
    * @return all people from birthdays file
    */
  def retrievePeople(birthdaysPath: String): Seq[Person] = {
    implicit val formats = DefaultFormats

    val fileStream: InputStream = getClass.getResourceAsStream(birthdaysPath)

    Source
      .fromInputStream(fileStream)
      .getLines
      .map(person => parse(person).extract[Person])
      .toSeq
  }
}
