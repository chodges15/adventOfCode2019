import org.scalatest.FunSuite

class ProgramAlarm1202Test extends FunSuite {
  val additionProgram = Vector(1, 0, 0, 0, 99)
  val multiplicationProgram = Vector(2, 3, 0, 3, 99)
  val anotherMultiplicationProgram = Vector(2, 4, 4, 5, 99, 0)

  test("test addition parsing") {
    assert(Day2.ProgramAlarm1202.parseNextInstruction(additionProgram.take(4)) == Day2.Add(0, 0, 0))
  }
}