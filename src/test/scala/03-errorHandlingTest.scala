import fpinscala.errorhandling
import org.scalatest._
import scala.{Option => _, Some => _, Either => _, _}

class ErrorHandlingSpec extends FunSpec with Matchers {
  describe("Option Type") {
    it("should be able to map from one type to the other") {
      Option(4).map(_.toString) should be(Some("4"))
    }

    it("Should support an alternate Option if you cant get the value") {
      val something = Some(4)
      val maybeSomething = None

      something.getOrElse((x: Int) => 4) should be(4)
      maybeSomething.getOrElse(5) should be(5)
    }
  }
}
