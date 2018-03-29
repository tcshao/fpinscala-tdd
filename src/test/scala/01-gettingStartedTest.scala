import fpinscala.gettingstarted._
import org.scalatest._

class GettingStartedSpec extends FunSpec with Matchers {

  describe("Fibonacci Sequence") {
    it("The 4th number should be 3") {
      MyModule.fib(4) should be(3)
    }

    it("Tabulate should construct a set set of elements") {
      val tabulatedList = List.tabulate(10) { MyModule.fib(_) }
      tabulatedList should have size 10
      tabulatedList(9) should be(34)
    }
  }

  describe("is Sorted function") {
    it("Should decide if an array is sorted") {
      val unsortedArray = Array(3, 4, 2, 1)
      PolymorphicFunctions.isSorted(unsortedArray)(_ > _) should be(false)

      val sortedArray = Array(1, 2, 3, 4, 5)
      PolymorphicFunctions.isSorted(sortedArray)(_ > _) should be(true)
    }
  }

  describe("Curried Function") {
    it("Should be decomposable") {
      val add2Numbers = (x: Int, y: Int) => x + y

      // a partial takes one argument and waits for the rest
      val partial = PolymorphicFunctions.partial1(2, add2Numbers)
      partial(4) should be(6)

      // a curried function allows you to feed in parameters on at a time
      val curried = PolymorphicFunctions.curry(add2Numbers)
      curried(2)(2) should be(4)

      val uncurried = PolymorphicFunctions.uncurry(curried)
      uncurried(2, 2) should be(4)
    }
  }
}
