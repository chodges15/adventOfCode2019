
import org.scalatest.FunSuite
import Day4.SecureContainer
import Day4.SecureContainer.{hasAscendingDigits, hasAtLeastTwoAdjacentDigits, hasTwoAdjacentDigits}

class TestSecureContainer extends FunSuite {
  def part1Validators: List[String => Boolean] = List(hasAtLeastTwoAdjacentDigits, hasAscendingDigits)
  def part2Validators: List[String => Boolean] = List(hasTwoAdjacentDigits, hasAscendingDigits)
  test("test consecutive digits pass") {
    assert(SecureContainer.hasAtLeastTwoAdjacentDigits("1233456"))
  }

  test("test nonconsecutive digits fail") {
    assert(SecureContainer.hasAtLeastTwoAdjacentDigits("123456") == false)
  }

  test("check for ascending digits") {
    assert(SecureContainer.hasAscendingDigits("1233456"))
  }

  test("check for descending digits") {
    assert(SecureContainer.hasAtLeastTwoAdjacentDigits("654321") == false)
  }

  test("test cases from problem part 1") {
    assert(SecureContainer.meetsCriteria(111111, part1Validators))
    assert(SecureContainer.meetsCriteria(223450, part1Validators) == false)
    assert(SecureContainer.meetsCriteria(123789, part1Validators) == false)
  }

  test("test three sets of two numbers") {
    assert(SecureContainer.meetsCriteria(112233, part2Validators))
  }

  test("test three consecutive numbers at the end fails") {
    assert(SecureContainer.meetsCriteria(123444, part2Validators) == false)
  }

  test("test two consecutive numbers at end") {
    assert(SecureContainer.meetsCriteria(111122, part2Validators))
  }

  test("test two consecutive numbers at beginning") {
    assert(SecureContainer.meetsCriteria(223456, part2Validators))
  }

  test("problem part 1") {
    assert(SecureContainer.getCountOfNumbersMeetingCriteria(234208, 765869, part1Validators) == 1246)
  }

  test("problem part 2") {
    assert(SecureContainer.getCountOfNumbersMeetingCriteria(234208, 765869, part2Validators) == 518)
  }
}
