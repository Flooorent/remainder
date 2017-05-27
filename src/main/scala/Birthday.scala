case class Birthday(year: Option[Int], month: Int, day: Int) {
  override def toString(): String = f"${year.getOrElse("<unknown year>")}-$month%02d-$day%02d"
}

case class Person(name: String, surname: String, birthday: Birthday) {
  override def toString(): String = s"$name $surname: $birthday"
}
