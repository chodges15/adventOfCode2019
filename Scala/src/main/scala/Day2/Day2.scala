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
  def executeInstruction(op: OpCode, memory: Vector[Int]): Option[Vector[Int]] = {
    op match {
      case Terminate() => None
      case Add(left, right, dest) => Some(memory.updated(dest, left+right))
      case Multiply(left, right, dest) => Some(memory.updated(dest, left*right))
    }
  }

  def parseNextInstruction(instruction: Vector[Int]): OpCode = {
    val LEFT_POS = 1
    val RIGHT_POS = 2
    val DEST_POS = 3
    if(instruction.contains(99)) {
      Terminate()
    }
    instruction.head match {
      case 0 => Add(instruction(LEFT_POS), instruction(RIGHT_POS), instruction(DEST_POS))
      case 1 => Multiply(instruction(LEFT_POS), instruction(RIGHT_POS), instruction(DEST_POS))
    }
  }

  def step(memory: Vector[Int], programCounter: Int): Option[Vector[Int]] = {
    executeInstruction(parseNextInstruction(memory.slice(programCounter, programCounter+instruction_size)), memory)
  }

  def runProgram(memory: Vector[Int], programCounter: Int): Vector[Int] = {
    step(memory, initial_program_counter).fold(memory)(runProgram(memory, programCounter+4))
  }
}
