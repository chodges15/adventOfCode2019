package Day2

import scala.collection.immutable.Vector
import scala.util.parsing.combinator._

sealed trait OpCode

case class Add(left: Int, right:Int, dest: Int) extends OpCode
case class Multiply(left: Int, right:Int, dest: Int) extends OpCode
case class Terminate() extends OpCode

class FullInstruction(raw: String) {
  val OP_POS = 0
  val LEFT_POS = 1
  val RIGHT_POS = 2
  val DEST_POS = 3
  val content = raw.toString.split(',').map(_.toInt)
  def getLeftSide = content(LEFT_POS)
  def getRightSide = content(RIGHT_POS)
  def getDest = content(DEST_POS)
}

class OpCodeParser extends RegexParsers {
  def addInstruction: Parser[Add] = """0, \d, \d, \d""".r ^^ {str => {
    val instr = new FullInstruction(str)
    Add(instr.getLeftSide, instr.getRightSide, instr.getDest)
  }
  }
}

object ProgramAlarm1202 extends OpCodeParser {
  val memory: Vector[Int] = Vector.empty
  var program_counter: Int = 0
  def executeInstruction(op: OpCode, left: Int, right: Int, dest: Int, memory: Vector[Int]): Option[Vector[Int]] = {
    op match {
      case Terminate() => None
      case Add(left, right, dest) => Some(memory.updated(dest, left+right))
      case Multiply(left, right, dest) => Some(memory.updated(dest, left*right))
    }
  }

  def parseNextInstruction(memory: Vector[Int], pc: Int): OpCode = {

  }

  def step(memory: Vector[Int]): Vector[Int] = {
    parseNextInstruction
  }

  def runProgram(memory: Vector[Int]): Vector[Int] = {
    step(memory).fold(memory)(runProgram(memory))
  }
}
