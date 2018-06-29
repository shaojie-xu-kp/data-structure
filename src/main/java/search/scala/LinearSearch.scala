package search.scala

import scala.collection.mutable.ArrayBuffer

object LinearSearch extends  App {

  def search (arr : ArrayBuffer[Int], target : Int) : Option[Int] = {

    def linearSearch(arr : ArrayBuffer[Int]) : Option[Int] = {

      if(arr.isEmpty)
        None
      else if (arr.head == target)
        Some(target)
      else
        linearSearch(arr.tail)
    }

    linearSearch(arr)
  }

  val arr = ArrayBuffer(1,2,3,4,5,6,7)
  val target = 8
  search(arr, target) match {
    case None => println(s"target $target not found")
    case Some(x) => println(s"target $x found")
  }

}
