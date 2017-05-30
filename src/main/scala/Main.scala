import com.github.nscala_time.time.Imports.{DateTime, LocalDate}
import Birthday._


object Main {

  /** args(0): to (gmail)
    * args(1): from (gmail)
    * args(2): password
    */
  def main(args: Array[String]): Unit = {

    val today = new LocalDate(DateTime.now)
    val people: Seq[Person] = Util.retrievePeople(Util.birthdaysPath)

    val birthdaysOfTheDay: Seq[Person] = getBirthdaysOfTheDay(people, today)
    val birthdaysOfTheWeek: Seq[Person] = getBirthdaysOfTheWeek(people, today)
    val birthdaysOfTheMonth: Seq[Person] = getBirthdaysOfTheMonth(people, today)

    val content: String = Content.formatAllBirthdays(birthdaysOfTheDay, birthdaysOfTheWeek, birthdaysOfTheMonth)

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
