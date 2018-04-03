package fpinscala.laziness

import Stream._
trait CustomStream[+A] {

  def foldRight[B](z: => B)(f: (A, => B) => B): B = // The arrow `=>` in front of the argument type `B` means that the function `f` takes its second argument by name and may choose not to evaluate it.
    this match {
      case Cons(h, t) =>
        f(h(), t().foldRight(z)(f)) // If `f` doesn't evaluate its second argument, the recursion never occurs.
      case _ => z
    }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b) // Here `b` is the unevaluated recursive step that folds the tail of the CustomStream. If `p(a)` returns `true`, `b` will never be evaluated and the computation terminates early.

  @annotation.tailrec
  final def find(f: A => Boolean): Option[A] = this match {
    case Empty      => None
    case Cons(h, t) => if (f(h())) Some(h()) else t().find(f)
  }

  def toList: List[A] = ???

  def take(n: Int): Stream[A] = ???

  def drop(n: Int): CustomStream[A] = ???

  def takeWhile(p: A => Boolean): Stream[A] = ???

  def forAll(p: A => Boolean): Boolean = ???

  def headOption: Option[A] = ???

  // 5.7 map, filter, append, flatmap using foldRight. Part of the exercise is
  // writing your own function signatures.

  def startsWith[B](s: CustomStream[B]): Boolean = ???
}
case object Empty extends CustomStream[Nothing]
case class Cons[+A](h: () => A, t: () => CustomStream[A])
    extends CustomStream[A]

object CustomStream {
  def cons[A](hd: => A, tl: => CustomStream[A]): CustomStream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: CustomStream[A] = Empty

  def apply[A](as: A*): CustomStream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

  val ones: CustomStream[Int] = CustomStream.cons(1, ones)
  def from(n: Int): CustomStream[Int] = ???

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): CustomStream[A] = ???
}
