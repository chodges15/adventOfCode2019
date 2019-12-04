import org.scalatest.FunSuite
import Day2.ProgramAlarm1202._

class ProgramAlarm1202Test extends FunSuite {
  val additionProgram = Vector(1, 0, 0, 0, 99)
  val multiplicationProgram = Vector(2, 3, 0, 3, 99)
  val anotherMultiplicationProgram = Vector(2, 4, 4, 5, 99, 0)
  val terminationProgram = Vector(99, 0, 0, 0)
  val test_program_99_sq = Vector(2,4,4,5,99,0)
  val test_program_medium = Vector(1,1,1,4,99,5,6,0,99)


  test("test addition parsing") {
    assert(parseNextInstruction(additionProgram.take(4)) == Day2.Add(0, 0, 0))
  }
  test("test multiplication parsing") {
    assert(parseNextInstruction(multiplicationProgram.take(4)) == Day2.Multiply(3, 0 , 3))
  }
  test( "test termination parsing") {
    assert(parseNextInstruction(terminationProgram) == Day2.Terminate())
  }

  test("test multiplication operation") {
    assert(executeInstruction(Day2.Multiply(3, 0, 3), multiplicationProgram) == Some(Vector(2,3,0,6,99)))
  }

  test("test addition operation") {
    assert(executeInstruction(Day2.Add(0, 0, 0), additionProgram) == Some(Vector(2,0,0,0,99)))
  }

  test( "run addition program step by step") {
    assert(step(additionProgram, 0) == Some(Vector(2,0,0,0,99)))
    assert(step(additionProgram, 4) == None)
  }

  test( "run addition program") {
    assert(runProgram(additionProgram) == Vector(2,0,0,0,99))
  }

  test( "run multiplication program") {
    assert(runProgram(multiplicationProgram) == Vector(2,3,0,6,99))
  }
  
  test( "run 99 squared test") {
    assert(runProgram(test_program_99_sq) == Vector(2,4,4,5,99,9801))
  }

  test( "run medium size test") {
    assert(runProgram(test_program_medium) == Vector(30,1,1,4,2,5,6,0,99))
  }

  test( "test with puzzle input") {
    assert(runProgram(question_input.updated(1,12).updated(2,2)).head == 9581917)
  }

  test("look for noun and verb that produce 19690720") {
    assert(findInputsToCreateMagicNumber(19690720) == Some(25,5))
  }
}