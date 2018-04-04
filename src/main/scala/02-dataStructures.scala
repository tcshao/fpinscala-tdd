package fpinscala.datastructures

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int =
    ints match { // A function that uses pattern matching to add up a list of integers
      case Nil => 0 // The sum of the empty list is 0.
      case Cons(x, xs) =>
        x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
    }

  def product(ds: List[Double]): Double = ds match {
    case Nil          => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs)  => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _)))          => x
    case Nil                                   => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t)                            => h + sum(t)
    case _                                     => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil        => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil         => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  // EXERCISE 3.2 p. 35
  def tail[A](l: List[A]): List[A] = ???

  // EXERCISE 3.3 p. 36
  def setHead[A](l: List[A], h: A): List[A] = ???

  // EXERCISE 3.4 p. 36
  def drop[A](l: List[A], n: Int): List[A] = ???

  // EXERCISE 3.5 p. 36
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = ???

  // EXERCISE 3.9 p. 40 -> Compute using foldRight
  def length[A](l: List[A]): Int = ???

  // EXERCISE 3.10 p. 40
  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = ???

  // EXERCISE 3.11 p. 41
  def sumViaFold(l: List[Int]): Int = ???

  def productViaFold(l: List[Int]): Int = ???

  def lengthViaFold[A](l: List[A]): Int = ???

  // EXERCISE 3.12 p. 41 -> write using a fold
  def reverse[A](l: List[A]): List[A] = ???

  // EXERCISE 3.13 p. 41
  def foldLeftViaFoldRight[A, B](l: List[A], z: B)(f: (B, A) => B): B = ???

  // EXERCISE 3.14 p. 41-> write using a fold
  def appendViaFold[A](l: List[A], m: List[A]): List[A] = ???

  // EXERCISE 3.15 p. 41 -> try to write using functions already defined
  def concat[A](l: List[List[A]]): List[A] = ???

  // EXERCISE 3.16 p. 42
  def mapPlusOne(l: List[Int]): List[Int] = ???

  // EXERCISE 3.17 p. 42
  def mapDoubleToString(l: List[Double]): List[String] = ???

  // EXERCISE 3.18 p. 42
  def map[A, B](l: List[A])(f: A => B): List[B] = ???

  // EXERCISE 3.19 p. 42
  def filter[A](l: List[A])(f: A => Boolean): List[A] = ???

  // EXERCISE 3.20 p. 42
  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = ???

  // EXERCISE 3.21 p. 42
  def filterViaFlatMap[A](l: List[A])(f: A => Boolean): List[A] = ???

  // EXERCISE 3.22 p. 42
  def addPairs(l: List[Int], r: List[Int]): List[Int] = ???

  // EXERCISE 3.23 p. 42
  def zipWith[A, B, C](l: List[A], r: List[B])(f: (A, B) => C): List[C] = ???

  // EXERCISE 3.24
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = ???
}