package Day5

import java.awt.JobAttributes.DestinationType

import scala.collection.immutable.Vector



sealed trait ParameterMode

case class PositionMode() extends ParameterMode
case class ImmediateMode() extends ParameterMode

case class Operand(value: Int, mode: ParameterMode, memory: Vector[Int]) {
  def getValue(): Int = {
    mode match {
      case PositionMode() => memory(value)
      case ImmediateMode() => value
    }
  }
}

sealed trait OpCode

case class Add(left: Operand, right: Operand, dest: Int) extends OpCode
case class Multiply(left: Operand, right: Operand, dest: Int) extends OpCode
case class Terminate() extends OpCode
case class Input(dest: Int) extends OpCode
case class Output(source: Int) extends OpCode


object TestProgram {
  val memory: Vector[Int] = Vector.empty
  val initial_program_counter: Int = 0
  val large_instruction_size = 4
  val small_instruction_size = 2
  val question_input = Vector(3,225,1,225,6,6,1100,1,238,225,104,0,1001,191,50,224,101,-64,224,224,4,224,1002,223,8,223,101,5,224,224,1,224,223,223,2,150,218,224,1001,224,-1537,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1002,154,5,224,101,-35,224,224,4,224,1002,223,8,223,1001,224,5,224,1,224,223,223,1102,76,17,225,1102,21,44,224,1001,224,-924,224,4,224,102,8,223,223,1001,224,4,224,1,224,223,223,101,37,161,224,101,-70,224,224,4,224,1002,223,8,223,101,6,224,224,1,223,224,223,102,46,157,224,1001,224,-1978,224,4,224,102,8,223,223,1001,224,5,224,1,224,223,223,1102,5,29,225,1101,10,7,225,1101,43,38,225,1102,33,46,225,1,80,188,224,1001,224,-73,224,4,224,102,8,223,223,101,4,224,224,1,224,223,223,1101,52,56,225,1101,14,22,225,1101,66,49,224,1001,224,-115,224,4,224,1002,223,8,223,1001,224,7,224,1,224,223,223,1101,25,53,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,108,226,226,224,1002,223,2,223,1005,224,329,101,1,223,223,108,677,677,224,1002,223,2,223,1006,224,344,1001,223,1,223,8,677,677,224,102,2,223,223,1006,224,359,101,1,223,223,7,226,677,224,102,2,223,223,1005,224,374,101,1,223,223,107,226,226,224,102,2,223,223,1006,224,389,101,1,223,223,7,677,226,224,1002,223,2,223,1006,224,404,1001,223,1,223,1107,677,226,224,1002,223,2,223,1006,224,419,1001,223,1,223,1007,226,226,224,102,2,223,223,1005,224,434,101,1,223,223,1008,226,677,224,102,2,223,223,1005,224,449,1001,223,1,223,1007,677,677,224,1002,223,2,223,1006,224,464,1001,223,1,223,1008,226,226,224,102,2,223,223,1006,224,479,101,1,223,223,1007,226,677,224,1002,223,2,223,1005,224,494,1001,223,1,223,108,226,677,224,1002,223,2,223,1006,224,509,101,1,223,223,8,226,677,224,102,2,223,223,1005,224,524,1001,223,1,223,107,677,677,224,1002,223,2,223,1005,224,539,101,1,223,223,107,226,677,224,1002,223,2,223,1006,224,554,101,1,223,223,1107,226,677,224,1002,223,2,223,1006,224,569,1001,223,1,223,1108,677,226,224,102,2,223,223,1005,224,584,1001,223,1,223,1008,677,677,224,102,2,223,223,1005,224,599,1001,223,1,223,1107,677,677,224,102,2,223,223,1006,224,614,101,1,223,223,7,226,226,224,102,2,223,223,1005,224,629,1001,223,1,223,1108,677,677,224,102,2,223,223,1006,224,644,1001,223,1,223,8,677,226,224,1002,223,2,223,1005,224,659,101,1,223,223,1108,226,677,224,102,2,223,223,1005,224,674,101,1,223,223,4,223,99,226)

  def add(memory: Vector[Int], left: Operand, right: Operand, dest: Int): Vector[Int] = {
    memory.updated(dest, left.getValue() + right.getValue())
  }

  def multiply(memory: Vector[Int], left: Operand, right: Operand, dest: Int): Vector[Int] = {
    memory.updated(dest, left.getValue() * right.getValue())
  }

  def executeInstruction(op: OpCode, memory: Vector[Int], input: Int = 0): Option[(Vector[Int], Int)] = {
    op match {
      case Terminate() => None
      case Add(left, right, dest) => Some(add(memory, left, right, dest), large_instruction_size)
      case Multiply(left, right, dest) => Some(multiply(memory, left, right, dest), large_instruction_size)
      case Input(dest) => Some(memory.updated(dest, input), small_instruction_size)
      case Output(source) => {
        println(memory(source))
        Some(memory, small_instruction_size)
      }
    }
  }

  def parseInstructionParameterModes(instruction: Int): Array[ParameterMode] = {
    instruction.toString().
      reverse.
      padTo(5, '0').
      reverse.
      toCharArray().
      take(3).
      collect{
        case 0 => PositionMode()
        case 1 => ImmediateMode()
      }.
      reverse
  }

  def parseNextInstruction(instr: Vector[Int]): OpCode = {
    val LEFT_POS = 1
    val RIGHT_POS = 2
    val DEST_POS = 3
    val paramMode = parseInstructionParameterModes(instr.head)
    instr.toString.takeRight(2)  match {
      case "01" => Add(Operand(instr(LEFT_POS), paramMode(LEFT_POS)),
        Operand(instr(RIGHT_POS), paramMode(RIGHT_POS)),
        instr(DEST_POS))
      case "02" => Multiply(Operand(instr(LEFT_POS), paramMode(LEFT_POS)),
        Operand(instr(RIGHT_POS), paramMode(RIGHT_POS)),
        instr(DEST_POS))
      case "03" => Input(instr(LEFT_POS))
      case "04" => Output(instr(LEFT_POS))
      case "99" => Terminate()
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

