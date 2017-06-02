import java.util.Locale
import com.github.nscala_time.time.Imports.{DateTime, LocalDate}


object Content {


  // TODO: add french locale => replace "This Day" by "Aujourd'hui"
  def formatAllBirthdays(
      bDayOfTheDay: Seq[Person],
      bDayOfTheWeek: Seq[Person],
      bDayOfTheMonth: Seq[Person]): String = {

    val today: LocalDate = new LocalDate(DateTime.now)
    val dayOfWeek: String = today.dayOfWeek.getAsText(new Locale("en"))
    val monthOfYear: String = today.monthOfYear.getAsText(new Locale("en"))

    Seq(
      s"$dayOfWeek ${today.getDayOfMonth} $monthOfYear ${today.getYear}",
      formatBirthdays("This day", bDayOfTheDay),
      formatBirthdays("This week", bDayOfTheWeek),
      formatBirthdays("This month", bDayOfTheMonth)
    ).mkString("\n\n")
  }



  def formatBirthdays(header: String, birthdays: Seq[Person]): String =
    if (birthdays.isEmpty)
      s"$header: nobody"
    else
      (s"$header:" +: birthdays
        .sortBy{case Person(_, _, birthday) => (birthday.month, birthday.day)}
        .map{case Person(name, surname, birthday) =>
          val date = new LocalDate(DateTime.now.getYear, birthday.month, birthday.day)
          val locale = new Locale("en")
          formatBirthdayEntry(name, surname, date, locale)
        }).mkString("\n")


  def formatBirthdayEntry(name: String, surname: String, date: LocalDate, locale: Locale): String = {
    val dayOfWeek: String = date.dayOfWeek.getAsText(locale)
    val monthOfYear: String = date.monthOfYear.getAsText(locale)
    s"    - ${formatName(name)} ${formatName(surname)}: $dayOfWeek ${date.getDayOfMonth} $monthOfYear"
  }


  def formatName(name: String): String = name.toLowerCase.capitalize
}
