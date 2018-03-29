package fpinscala.datastructures
import org.scalatest._

class DataStructuresSpec extends FunSpec with Matchers {
  describe("List") {
    it("tail should return the tail") {
      List.tail(List(1, 2, 3)) should be(List(2, 3))
    }

    it("should return an empty list when an empty list is passed") {
      List.tail(List[Int]()) should be(List[Int]())
    }

    it("set head should replace the head") {
      List.setHead(List(1, 2, 3, 4), 5) should be(List(5, 2, 3, 4))
    }

    it("should drop the specified number of elements") {
      List.drop(List(1, 2, 3, 4, 5, 6, 7), 5) should be(List(6, 7))
    }

    it("dropWhile should drop items that match the predicate") {
      val list = List(2, 4, 6, 8, 1, 3)
      List.dropWhile(list, ((x: Int) => x < 3)) should be(List(4, 6, 8, 1, 3))

      // negative case, nothing dropped
      List.dropWhile(list, ((x: Int) => x > 10)) should be(list)
    }

    it("Can compute the Length") {
      List.length(List(1, 2, 3, 4, 5, 6)) should be(6)
    }

    it("can fold left") {
      List.foldLeft(List(1, 2, 3, 4), 0)(_ + _) should be(10)
    }

    it("can be reversed") {
      List.reverse(List(1, 2, 3, 4, 5)) should be(List(5, 4, 3, 2, 1))
    }

    it("misc fold left") {

      List.sumViaFold(List(1, 2, 3)) should be(6)

      List.productViaFold(List(1, 2, 3)) should be(6)

      List.lengthViaFold(List(1, 2, 3, 4, 5)) should be(5)
    }

    it("we can append two lists") {
      List.appendViaFold(List(1, 2, 3), List(5, 6, 7)) should be(
        List(1, 2, 3, 5, 6, 7))
    }

    it("we can concatenate a list of lists") {
      val l = List(1, 2, 3)
      val m = List(4, 5, 6)
      val n = List(7, 8, 9)
      List.concat(List(l, m, n)) should be(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    it("we can map stuff") {
      List.mapPlusOne(List(1, 2, 3, 4)) should be(List(2, 3, 4, 5))

      List.mapDoubleToString(List(0.0, 0.1)) should be(List("0.0", "0.1"))

      List.map(List(1, 2, 3, 4))(_ * 2) should be(List(2, 4, 6, 8))
    }

    it("we can filter") {
      // remove odds
      List.filter(List(2, 3, 5, 7, 1))(_ < 3) should be(List(2, 1))
    }

    it("we can flatMap") {
      List.flatMap(List(1, 2, 3))(i => List(i, i)) should be(
        List(1, 1, 2, 2, 3, 3))

      List.filterViaFlatMap(List(2, 3, 5, 7, 1))(_ < 3) should be(List(2, 1))
    }

    it("we can add pairs") {
      List.addPairs(List(1, 2, 3), List(4, 5, 6)) should be(List(5, 7, 9))
    }

    it("we can zip") {

      List.zipWith(List("One", "Two", "Three"), List(1, 2, 3))((_, _)) should be(
        List(("One", 1), ("Two", 2), ("Three", 3)))
    }
  }
}
