package fpinscala.errorhandling
import org.scalatest._
import scala.{Option => _, Some => _, Either => _, _}

class OptionSpec extends FunSpec with Matchers {

  describe("Error Handling") {

    it("can map") {
      Some(4).map(_.toString) should be(Some("4"))
    }

    it("can do or else") {
      val something = Some(4)
      val maybeSomething = None

      something.getOrElse((x: Int) => 4) should be(4)
      maybeSomething.getOrElse(5) should be(5)
    }

    it("Can flatMap") {
      val x = Some(3).flatMap(x => Some(x + 6))
      x should be(Some(9))
    }
  }
}
