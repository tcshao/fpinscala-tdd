package fpinscala.errorhandling
import org.scalatest._
import scala.{Option => _, Some => _, Either => _, _}

class OptionSpec extends FunSpec with Matchers {

  describe("Error Handling") {

    it("can map") {
      Some(4).map(_.toString) should be(Some("4"))
    }

    it("can do get or else") {
      val something = Some(4)
      val maybeSomething = None

      something.getOrElse((x: Int) => 4) should be(4)
      maybeSomething.getOrElse(5) should be(5)
    }

    it("Can flatMap") {
      val x = Some(3).flatMap(x => Some(x + 6))
      x should be(Some(9))
    }

    it("Can do or else") {
      val something = Some(4)
      val maybeSomething = None

      something.orElse(None) should be(Some(4))
      maybeSomething.orElse(Some(3)) should be(Some(3))
    }

    it("Can compute variance") {
      val domain = List[Double](5, 5, 8, 12, 15, 18)

      val result = Option.variance(domain)

      result should be(Some(24.25))
    }

    it("Can process map2") {
      val something = Some(4)
      val maybeSomething = None
      val somethingElse = Some(34)

      val result = Option.map2(something, somethingElse)(_ + _)

      result should be(Some(38))

      val nonResult =
        Option.map2(maybeSomething, maybeSomething)((x, y) => List[Int]())

      nonResult should be(None)
    }
  }
}
