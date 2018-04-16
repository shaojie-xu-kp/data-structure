package main.scala

/**
  * Created by shaojie.xu on 16/04/2018.
  */
object InsertionSort extends  App {

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



  val l = List(3,4,5,1,2,6,8,0,9,7)
  println(insertionSort1(l))

  val fruit = List("apple", "pear", "orange", "pineapple")
  println(insertionSort2(fruit)((x , y) => x < y))

}
