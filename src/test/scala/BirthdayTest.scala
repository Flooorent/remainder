import org.scalatest.FunSuite
import Birthday._


class BirthdayTest extends FunSuite {

  test("getFollowingBirthdays should return only people that have their birthdays in the interval") {
    val john = Person("john", "doe", Birthday(Some(1958), 8, 17))
    val jane = Person("jane", "moe", Birthday(Some(1990), 9, 19))

    assert(getFollowingBirthdays(Iterator(john, jane), 8, 15, 7) sameElements Iterator(john), "small time frame: only john")
    assert(getFollowingBirthdays(Iterator(john, jane), 8, 15, 1).isEmpty, "too small of a timeframe: empty iterator")
    assert(getFollowingBirthdays(Iterator(john, jane), 8, 15, 45) sameElements Iterator(john, jane), "large timeframe: john and jane")
  }

  test("getFollowingBirthdays should handle new year") {
    val john = Person("john", "doe", Birthday(Some(1958), 1, 2))
    assert(getFollowingBirthdays(Iterator(john), 12, 30, 7) sameElements Iterator(john))
  }
}
