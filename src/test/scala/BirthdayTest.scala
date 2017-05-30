import org.scalatest.FunSuite
import com.github.nscala_time.time.Imports.LocalDate


import Birthday._


class BirthdayTest extends FunSuite {

  test("getComingBirthdays should return only people that have their birthdays in the interval") {
    val john = Person("john", "doe", Birthday(Some(1958), 8, 17))
    val jane = Person("jane", "moe", Birthday(Some(1990), 9, 19))

    assert(getComingBirthdays(Seq(john, jane), new LocalDate(2017, 8, 15), 1, 7) == Seq(john), "small time frame: only john")
    assert(getComingBirthdays(Seq(john, jane), new LocalDate(2018, 8, 15), 0, 1).isEmpty, "too small of a timeframe: empty iterator")
    assert(getComingBirthdays(Seq(john, jane), new LocalDate(2019, 8, 15), 0, 45) == Seq(john, jane), "large timeframe: john and jane")
  }

  test("getComingBirthdays should handle new year") {
    val john = Person("john", "doe", Birthday(Some(1958), 1, 2))
    assert(getComingBirthdays(Seq(john), new LocalDate(2016, 12, 30), 1, 7) == Seq(john))
  }
}
