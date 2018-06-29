package sort.scala

import scala.annotation.tailrec

object SelectionSort extends  App {


  def selectionSort1[T : Ordering](l : List[T]) : List[T] = {

    def sort(l: List[T], result: List[T]) : List[T] = {
      l match {
        case Nil => result
        case h :: tail => select(h, tail, Nil, result)
      }
    }

    @tailrec
    def select(m: T, tail: List[T], temp : List[T], result: List[T]) : List[T] = {
      tail match {
        case Nil => sort(temp, m::result)
        case h::t =>
          if (implicitly[Ordering[T]].gt(m,h)) select(m, t, h :: temp, result)
          else select(h, t, m :: temp, result)
      }
    }

    sort(l, Nil)
  }

  def selectionSort2[T : Ordering](xs : List[T]) : List[T] = {

    @tailrec
    def selectAndSort(xs: List[T], result: List[T]) : List[T] = {
      xs match {
        case Nil => result
        case _ => {
          val minItem = xs.reduceLeft(min)
          val remainList = xs.filter(_ != minItem)
          selectAndSort(remainList, result ::: List.fill(xs.length - remainList.length)(minItem))
        }
      }
    }

    def min = (a : T, b : T) => if (implicitly[Ordering[T]].lt(a,b)) a else b

    selectAndSort(xs, Nil)
  }

  val l = List(5, 3, 2, 2, 1, 1, 9, 39 ,219)
  println(selectionSort1(l))
  println(selectionSort2(l))

}
