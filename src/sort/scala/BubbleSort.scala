package sort.scala

import scala.collection.mutable.ArrayBuffer

object BubbleSort extends App {

  def imperativeBubbleSort[T : Ordering](a : ArrayBuffer[T]) : ArrayBuffer[T] = {
    for (i <- 0 until a.length - 1; j <- 0 until a.length - 1 - i) {
      if (implicitly[Ordering[T]].gt(a(j), a(j+1))) {
        val temp = a(j)
        a(j) = a(j + 1)
        a(j + 1) = temp
      }
    }
    a
  }

  val a = ArrayBuffer(5, 3, 2, 2, 1, 1, 9, 39 ,219)
  println(imperativeBubbleSort(a))



  def bubbleSort[T : Ordering](list: List[T]): List[T] = {
    def sort(as: List[T], bs: List[T]): List[T] =
      if (as.isEmpty) bs
      else bubble(as, Nil, bs)

    def bubble(as: List[T], zs: List[T], bs: List[T]): List[T] = as match {
      case h1 :: h2 :: t =>
        if (implicitly[Ordering[T]].gt(h1, h2)) bubble(h1 :: t, h2 :: zs, bs)
        else bubble(h2 :: t, h1 :: zs, bs)
      case h1 :: Nil => sort(zs, h1 :: bs)
    }

    sort(list, Nil)
  }

  val l = List(5, 3, 2, 2, 1, 1, 9, 39 ,219)
  println(bubbleSort(l))


}
