package fpinscala.laziness
import org.scalatest._

class LazySpec extends FunSpec with Matchers {

  describe("Custom Stream") {
    it("Can be turned into a list") {
      val st = Stream(1, 2, 3)
      st.toListRecursive should be(List(1, 2, 3))
    }

    it("We can take a specified number of items from the stream") {
      val st = Stream(1, 2, 3, 4, 6, 7)

      st.take(3).toListRecursive should be(List(1, 2, 3))
      st.take(0).toListRecursive should be(List())
      st.take(10).toListRecursive should be(List(1, 2, 3, 4, 6, 7))
    }

    it("We can drop a specified number of items from the stream") {
      val st = Stream(1, 2, 3, 4, 6, 7)

      st.drop(3).toListRecursive should be(List(4, 6, 7))
      st.drop(0).toListRecursive should be(List(1, 2, 3, 4, 6, 7))
      st.drop(10).toListRecursive should be(List())
    }

    it("Can support takeWhile") {
      val st = Stream(1, 2, 3, 4, 6, 7)

      st.takeWhile(_ < 10).toListRecursive should be(List(1, 2, 3, 4, 6, 7))
      st.takeWhile(_ > 10).toListRecursive should be(List())
    }

    it("Supports forAll") {
      var st = Stream(1, 2, 3, 4)

      st.forAll(_ < 5) should be(true)
      st.forAll(_ == 3) should be(false)
    }

    it("Can support takeWhile via FoldRight") {
      val st = Stream(1, 2, 3, 4, 6, 7)

      st.takeWhile(_ < 10).toListRecursive should be(List(1, 2, 3, 4, 6, 7))
      st.takeWhile(_ > 10).toListRecursive should be(List())
    }

    it("Can support map via FoldRight") {
      val st = Stream(1, 2, 3, 4, 6, 7)

      //st.mapViaFoldRight(_ * 2).toListRecursive should be(
      //  List(2, 4, 6, 8, 12, 14))
    }

    it("Can support filder via FoldRight") {
      val st = Stream(1, 2, 3, 4, 6, 7)

      //st.filterViaFoldRight(_ == 4).toListRecursive should be(List(4))
    }

    it("Can support append via FoldRight") {
      val st = Stream(1, 2, 3, 4, 6, 7)
      val st2 = Stream(2)

      //st.appendViaFoldRight(st2).toListRecursive should be(
      //  List(1, 2, 3, 4, 6, 7, 2))
    }

    it("Can support flatMap via FoldRight") {
      val st = Stream(Stream(1, 2, 3), Stream(4, 5, 6))

      //st.flatMapViaFoldRight(x => x).toListRecursive should be(
      // List(1, 2, 3, 4, 5, 6))
    }

    it("Can generate an infinite List of constants") {
      val constantList = Stream.constant(4)

      constantList.take(4).toListRecursive should be(List(4, 4, 4, 4))
    }

    it("Can generate an infinite List of ints counting up") {
      val constantList = Stream.from(4)

      constantList.take(4).toListRecursive should be(List(4, 5, 6, 7))
    }

    it("Can generate the fibonacci sequence") {
      val fibsList = List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144)

      Stream.fibsGenerator(0, 1).take(13).toListRecursive should be(fibsList)
    }
  }
}
