package main.scala

/**
  * Created by shaojie.xu on 16/04/2018.
  */
object InsertionSort extends  App {

  /**
    * insertion sort only for Int type
    * @param xs
    * @return
    */
  def insertionSort1(xs: List[Int]): List[Int] = {
    def insert(y: Int, ys: List[Int]): List[Int] =
      ys match {
        case List() => y :: List()
        case z :: zs =>
          if (y < z) y :: z :: zs
          else z :: insert(y, zs)
      }

    xs match {
      case List() => List()
      case y :: ys => insert(y, insertionSort1(ys))
    }
  }

  /**
    * insertion sort for generic type with an lessThan function as an intput parameter
    * @param xs
    * @param lessThan
    * @tparam T
    * @return
    */
  def insertionSort2[T](xs: List[T])(lessThan : (T, T) => Boolean): List[T] = {
    def insert(y : T, ys : List[T]) : List[T] =
      ys match {
        case List() => y :: List()
        case z :: zs =>
            if (lessThan(y, z)) y :: z :: zs
            else z :: insert(y, zs)
      }

      xs match {
        case List() => List()
        case y :: ys => insert(y, insertionSort2(ys)(lessThan))
      }
  }

  /**
    * insertion sort for generic type with ord Ordering as an implicit input parameter
    * @param xs
    * @param ord
    * @tparam T
    * @return
    */
  def insertionSort3[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    def insert(y : T, ys : List[T]) : List[T] =
      ys match {
        case List() => y :: List()
        case z :: zs =>
          if (ord.lt(y, z)) y :: z :: zs
          else z :: insert(y, zs)
      }

    xs match {
      case List() => List()
      case y :: ys => insert(y, insertionSort3(ys))
    }
  }



  val l = List(3,4,5,1,2,6,8,0,9,7)
  println(insertionSort1(l))

  val fruit1 = List("apple", "pear", "orange", "pineapple")
  println(insertionSort2(fruit1)((x , y) => x < y))

  val fruit2 = "banana" :: "kiwi" :: "melon" :: fruit1
  println(insertionSort3(fruit2))


  implicit val rationalOrder : Ordering[Rational] = (x, y) => (x.numer * y.denom - y.numer * x.denom)

  val half = new Rational(1, 2)
  val third = new Rational(1, 3)
  val fourth = new Rational(1, 4)
  val one = new Rational(2,2)
  val two = new Rational(64,32)
  val rationals = List(one, third, half, two, fourth)

  println(insertionSort3(rationals))

}


class Rational(x: Int, y: Int) {

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)

  lazy val numer: Int = x / g
  lazy val denom: Int = y / g

  override def toString: String = s"$numer/$denom"
}
