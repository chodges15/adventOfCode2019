package Day2

import scala.collection.immutable.Vector

sealed trait OpCode

case class Add(left: Int, right:Int, dest: Int) extends OpCode
case class Multiply(left: Int, right:Int, dest: Int) extends OpCode
case class Terminate() extends OpCode


object ProgramAlarm1202 {
  val memory: Vector[Int] = Vector.empty
  val initial_program_counter: Int = 0
  val instruction_size = 4
  val question_input = Vector(1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 10, 19, 2, 9, 19, 23, 1, 9, 23, 27, 2, 27, 9, 31, 1, 31, 5, 35, 2, 35, 9, 39, 1, 39, 10, 43, 2, 43, 13, 47, 1, 47, 6, 51, 2, 51, 10, 55, 1, 9, 55, 59, 2, 6, 59, 63, 1, 63, 6, 67, 1, 67, 10, 71, 1, 71, 10, 75, 2, 9, 75, 79, 1, 5, 79, 83, 2, 9, 83, 87, 1, 87, 9, 91, 2, 91, 13, 95, 1, 95, 9, 99, 1, 99, 6, 103, 2, 103, 6, 107, 1, 107, 5, 111, 1, 13, 111, 115, 2, 115, 6, 119, 1, 119, 5, 123, 1, 2, 123, 127, 1, 6, 127, 0, 99, 2, 14, 0, 0)

  def executeInstruction(op: OpCode, memory: Vector[Int]): Option[Vector[Int]] = {
    op match {
      case Terminate() => None
      case Add(left, right, dest) => Some(memory.updated(dest, memory(left) + memory(right)))
      case Multiply(left, right, dest) => Some(memory.updated(dest, memory(left) * memory(right)))
    }
  }

  def parseNextInstruction(instruction: Vector[Int]): OpCode = {
    val LEFT_POS = 1
    val RIGHT_POS = 2
    val DEST_POS = 3
    instruction.head match {
      case 1 => Add(instruction(LEFT_POS), instruction(RIGHT_POS), instruction(DEST_POS))
      case 2 => Multiply(instruction(LEFT_POS), instruction(RIGHT_POS), instruction(DEST_POS))
      case 99 => Terminate()
    }
  }

  def step(memory: Vector[Int], programCounter: Int): Option[Vector[Int]] = {
    executeInstruction(parseNextInstruction(memory.slice(programCounter, programCounter + instruction_size)), memory)
  }

  def runProgram(memory: Vector[Int], programCounter: Int = 0): Vector[Int] = {
    step(memory, programCounter).fold(memory)(runProgram(_, programCounter + 4))
  }

  def findInputsToCreateMagicNumber(magicNumber: Int): Option[(Int, Int)] = {
    // Brute force!
    for (noun <- 0 to 99) {
      for (verb <- 0 to 99) {
        if(runProgram(question_input.updated(1, noun).updated(2, verb)).head == magicNumber)
          return Some(noun, verb)
      }
    }
    None
  }
}

