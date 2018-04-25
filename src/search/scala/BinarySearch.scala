package search.scala

import sort.scala.{HeapSort}
import scala.annotation.tailrec
object BinarySearch extends App {


  /**
    * time complexity O(log n)
    * space complexity O(1) in place search algorithm
    * @param arr
    * @param key
    * @return index of the key
    */
  def search(arr: Array[Int], key : Int) : Int = {
    @tailrec
    def binarySearch(minIndex: Int, maxIndex: Int): Int = {
      val midIndex = (minIndex + maxIndex) / 2
      if (midIndex > maxIndex)
        -1
      else if (arr(midIndex) == key)
        midIndex
      else if (arr(midIndex) > key)
        binarySearch(minIndex, midIndex -1)
      else
        binarySearch(midIndex + 1, maxIndex)
    }

    binarySearch(0, arr.length - 1)
  }
  val array : Array[Int] = Array(1,3,5,2,6,4,9,0,30,12)
  HeapSort.sort(array)
  array foreach println
  val index = search(arr = array, key = 0)
  index match {
    case -1 => println("key not found")
    case _ => println(s"key found at position $index")
  }
}
