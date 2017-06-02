import java.util.Locale
import com.github.nscala_time.time.Imports.{DateTime, LocalDate}


object Content {

  def formatAllBirthdays(
      bDayOfTheDay: Seq[Person],
      bDayOfTheWeek: Seq[Person],
      bDayOfTheMonth: Seq[Person],
      language: Language): String = {

    val today: LocalDate = new LocalDate(DateTime.now)
    val dayOfWeek: String = today.dayOfWeek.getAsText(language.locale)
    val monthOfYear: String = today.monthOfYear.getAsText(language.locale)

    Seq(
      s"$dayOfWeek ${today.getDayOfMonth} $monthOfYear ${today.getYear}",
      formatBirthdays(language.thisDay, bDayOfTheDay, language),
      formatBirthdays(language.thisWeek, bDayOfTheWeek, language),
      formatBirthdays(language.thisMonth, bDayOfTheMonth, language)
    ).mkString("\n\n")
  }


  def formatBirthdays(header: String, birthdays: Seq[Person], language: Language): String =
    if (birthdays.isEmpty)
      s"$header: ${language.nobody}"
    else
      (s"$header:" +: birthdays
        .sortBy{case Person(_, _, birthday) => (birthday.month, birthday.day)}
        .map{case Person(name, surname, birthday) =>
          val date = new LocalDate(DateTime.now.getYear, birthday.month, birthday.day)
          formatBirthdayEntry(name, surname, date, language.locale)
        }).mkString("\n")


  def formatBirthdayEntry(name: String, surname: String, date: LocalDate, locale: Locale): String = {
    val dayOfWeek: String = date.dayOfWeek.getAsText(locale)
    val monthOfYear: String = date.monthOfYear.getAsText(locale)
    s"    - ${formatName(name)} ${formatName(surname)}: $dayOfWeek ${date.getDayOfMonth} $monthOfYear"
  }


  def formatName(name: String): String = name.toLowerCase.capitalize
}
