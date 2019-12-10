package Day4

object SecureContainer {

  def hasAtLeastTwoAdjacentDigits(password: String): Boolean =
    password.exists(c => password.indexOf(c.toString) != password.lastIndexOf(c.toString))

  def hasTwoAdjacentDigits(password: String): Boolean = {
    password.exists(c => (password.lastIndexOf(c.toString) - password.indexOf(c.toString)) == 1)
  }

  def hasAscendingDigits(password: String): Boolean =
    password.toVector.sorted == password.toVector

  def meetsCriteria(password: Int, criteria: List[String => Boolean]): Boolean =
    criteria.forall(_(password.toString()))

  def getCountOfNumbersMeetingCriteria(start: Int, end:Int, criteria: List[String => Boolean]): Int =
    (start to end).toArray.filter(meetsCriteria(_, criteria)).size

}
