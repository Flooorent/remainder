import com.github.nscala_time.time.Imports._


case class Birthday(year: Option[Int], month: Int, day: Int) {
  override def toString(): String = f"${year.getOrElse("<unknown year>")}-$month%02d-$day%02d"
}

case class Person(name: String, surname: String, birthday: Birthday) {
  override def toString(): String = s"$name $surname: $birthday"
}


object Birthday {

  def getFollowingWeekBirthdays(day: Int, month: Int): Iterator[Person] =
    getFollowingBirthdays(day, month, 6)

  def getFollowingMonthBirthdays(day: Int, month: Int): Iterator[Person] =
    getFollowingBirthdays(day, month, 30)


  /** Return all people that have their birthdays in the interval [(day, month), (day, month) + span]
    *
    * @param day: day from which to start the scan
    * @param month: month from which to start the scan
    * @param span: number of days that define the window
    * @return all people whose birthdays belong to the specified interval
    */
  def getFollowingBirthdays(day: Int, month: Int, span: Int): Iterator[Person] = {
    val date: DateTime = DateTime.now.month(month).day(day)

    // TODO: bug: critical case on two years
    // TODO: unit tests
    Util
      .retrievePeople()
      .filter { case Person(name, surname, birthday) =>
        val nextBirthday: DateTime = date.month(birthday.month).day(birthday.day)
        (date <= nextBirthday) && (nextBirthday <= (date + span.days))
      }
  }
}
