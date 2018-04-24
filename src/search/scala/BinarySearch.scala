package search.scala

import sort.scala.{BubbleSort, HeapSort}

object BinarySearch extends App {

  def binarySearch(arr: Array[Int], i: Int, i1: Int, key: Int): Int = {
    -1
  }

  def search(arr: Array[Int], key : Int) : Int = {
    binarySearch(arr, 0, arr.length - 1, key)
  }
  val array : Array[Int] = Array(1,3,5,2,6,4,9,0,30,12)
  HeapSort.sort(array)
  array foreach println

}
