package fpinscala.laziness
import org.scalatest._

class LazySpec extends FunSpec with Matchers {

  describe("Custom Stream") {
    it("Can be turned into a list") {
      val st = Stream(1, 2, 3)
      st.toList should be(List(1, 2, 3))
    }

    it("We can take a specified number of items from the stream") {
      val st = CustomStream(1, 2, 3, 4, 6, 7)

      st.take(3) should be(List(1, 2, 3))
      st.take(0) should be(List())
      st.take(10) should be(List(1, 2, 3, 4, 6, 7))
    }

    it("We can drop a specified number of items from the stream") {
      val st = CustomStream(1, 2, 3, 4, 6, 7)

      st.drop(3).toList should be(List(4, 6, 7))
      st.drop(0).toList should be(List(1, 2, 3, 4, 6, 7))
      st.drop(10).toList should be(List())
    }

    it("Can support takeWhile") {
      val st = CustomStream(1, 2, 3, 4, 6, 7)

      st.takeWhile(_ < 10).toList should be(List(1, 2, 3, 4, 6, 7))
      st.takeWhile(_ > 10).toList should be(List())
    }

    it("Supports forAll") {
      var st = CustomStream(1, 2, 3, 4)

      st.forAll(_ < 5) should be(true)
      st.forAll(_ == 3) should be(false)
    }
  }

}
