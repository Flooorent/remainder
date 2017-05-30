import com.github.nscala_time.time.Imports._


case class Birthday(year: Option[Int], month: Int, day: Int) {
  override def toString(): String = f"${year.getOrElse("<unknown year>")}-$month%02d-$day%02d"
}

case class Person(name: String, surname: String, birthday: Birthday) {
  override def toString(): String = s"$name $surname: $birthday"
}


object Birthday {

  def getFollowingWeekBirthdays(people: Iterator[Person], month: Int, day: Int): Iterator[Person] =
    getFollowingBirthdays(people, month, day, 6)

  def getFollowingMonthBirthdays(people: Iterator[Person], month: Int, day: Int): Iterator[Person] =
    getFollowingBirthdays(people, month, day, 30)


  /** Return all people that have their birthdays in the interval [(day, month), (day, month) + span]
    *
    * @param people: people to filter
    * @param month: month from which to start the scan
    * @param day: day from which to start the scan
    * @param span: number of days that define the window
    * @return all people whose birthdays belong to the specified interval
    */
  def getFollowingBirthdays(people: Iterator[Person], month: Int, day: Int, span: Int): Iterator[Person] = {
    val date: LocalDate = new LocalDate(DateTime.now.getYear, month, day)

    people
      .filter { case Person(name, surname, birthday) =>
        val nextBirthday: LocalDate =
          if (birthday.month < month) // to handle edge cases when we start towards the end of the year
            new LocalDate(date.getYear + 1, birthday.month, birthday.day)
          else
            new LocalDate(date.getYear, birthday.month, birthday.day)

        (date <= nextBirthday) && (nextBirthday <= (date + span.days))
      }
  }
}
