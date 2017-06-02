import com.github.nscala_time.time.Imports.{DateTime, LocalDate}
import Birthday._

import scala.util.{Failure, Success}


object Main {

  def main(args: Array[String]): Unit = {

    Args.getConfig(args) match {
      case Failure(exception) => throw exception
      case Success(conf) =>

        val language = Language.withName(conf.language)
        val today = new LocalDate(DateTime.now)

        val people: Seq[Person] = Util.retrievePeople(Util.birthdaysPath)

        val birthdaysOfTheDay: Seq[Person] = getBirthdaysOfTheDay(people, today)
        val birthdaysOfTheWeek: Seq[Person] = getBirthdaysOfTheWeek(people, today)
        val birthdaysOfTheMonth: Seq[Person] = getBirthdaysOfTheMonth(people, today)

        val content: String = Content.formatAllBirthdays(
          birthdaysOfTheDay,
          birthdaysOfTheWeek,
          birthdaysOfTheMonth,
          language)

        val mail = new MailAgent(
          to = conf.to,
          cc = null,
          bcc = null,
          from = conf.from,
          password = conf.password,
          subject = "TEST LOCALHOST",
          content = content)

        mail.sendMessage()

        println("done")
    }
  }
}
