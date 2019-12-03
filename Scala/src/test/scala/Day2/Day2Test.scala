import org.scalatest.FunSuite

class ProgramAlarm1202Test extends FunSuite {
  test("test1") {
    assert(Day2.ProgramAlarm1202.executeInstruction() === 1)
  }
}