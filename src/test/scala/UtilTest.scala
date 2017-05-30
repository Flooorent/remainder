import org.scalatest.FunSuite
import Util._


class UtilTest extends FunSuite {

  test("retrievePeople should parse people from json input") {
    val expected: Iterator[Person] = Iterator(
      Person("john", "doe", Birthday(Some(1958), 8, 17)),
      Person("jane", "moe", Birthday(Some(1990), 9, 18)),
      Person("sherlock", "holmes", Birthday(None, 1, 6)))

    assert(retrievePeople("/birthdays_test.txt") sameElements expected)
  }
}
