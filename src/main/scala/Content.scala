import java.util.Locale
import com.github.nscala_time.time.Imports.{DateTime, LocalDate}


object Content {


  // TODO: add french locale => replace "This Day" by "Aujourd'hui"
  // TODO: add current date to "This day"
  def formatAllBirthdays(
    bDayOfTheDay: Seq[Person],
    bDayOfTheWeek: Seq[Person],
    bDayOfTheMonth: Seq[Person]): String =
      Seq(
        formatBirthdays("This day", bDayOfTheDay),
        formatBirthdays("This week", bDayOfTheWeek),
        formatBirthdays("This month", bDayOfTheMonth)
      ).mkString("\n\n")


  // TODO: order birthdays
  def formatBirthdays(header: String, birthdays: Seq[Person]): String =
    if (birthdays.isEmpty)
      s"$header: nobody"
    else
      (s"$header:" +: birthdays.map{case Person(name, surname, birthday) =>
        val date = new LocalDate(DateTime.now.getYear, birthday.month, birthday.day)
        val locale = new Locale("en")
        val dayOfWeek: String = date.dayOfWeek.getAsText(locale)
        val monthOfYear: String = date.monthOfYear.getAsText(locale)
        s"    - $name $surname: $dayOfWeek, ${date.getDayOfMonth} $monthOfYear"}).mkString("\n")

}
