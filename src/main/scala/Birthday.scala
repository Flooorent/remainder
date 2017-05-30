import com.github.nscala_time.time.Imports._


case class Birthday(year: Option[Int], month: Int, day: Int) {
  override def toString(): String = f"${year.getOrElse("<unknown year>")}-$month%02d-$day%02d"
}

case class Person(name: String, surname: String, birthday: Birthday) {
  override def toString(): String = s"$name $surname: $birthday"
}


object Birthday {

  /** Return all people that have their birthdays in the interval [(day, month) + minDays, (day, month) + maxDays]
    *
    * @param people: people to filter
    * @param date: date from which to create the interval
    * @param minDays: number of days to add to date to get the interval's lower bound
    * @param maxDays: number of days to add to date to get the interval's upper bound
    * @return all people whose birthdays belong to the specified interval
    */
  def getComingBirthdays(people: Seq[Person], date: LocalDate, minDays: Int, maxDays: Int): Seq[Person] =
    people
      .filter { case Person(name, surname, birthday) =>
        val nextBirthday: LocalDate =
          if (birthday.month < date.getMonthOfYear) // to handle edge cases when we start towards the end of the year
            new LocalDate(date.getYear + 1, birthday.month, birthday.day)
          else
            new LocalDate(date.getYear, birthday.month, birthday.day)

        (date + minDays.days <= nextBirthday) && (nextBirthday <= date + maxDays.days)
      }


  def getBirthdaysOfTheDay(people: Seq[Person], date: LocalDate): Seq[Person] =
    getComingBirthdays(people, date, 0, 0)

  /** NB: we don't count the first day as part of the week! */
  def getBirthdaysOfTheWeek(people: Seq[Person], date: LocalDate): Seq[Person] =
    getComingBirthdays(people, date, 1, 7)

  /** NB: we don't count the first week as part of the month! */
  def getBirthdaysOfTheMonth(people: Seq[Person], date: LocalDate): Seq[Person] =
    getComingBirthdays(people, date, 8, 30)
}
