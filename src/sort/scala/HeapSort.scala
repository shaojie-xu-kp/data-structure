package sort.scala

object HeapSort extends App {

    var mess = Array(3, 9, 8, 13, 2, 5, 4);

    sort(mess)

    mess.foreach(println)

  def sort(a: Array[Int]): Unit = {
    var m = a.length - 1
    buildHeap(a, m)
    while (m >= 1) {
      swap(a, 0, m)
      m-=1
      heapify(a, 0, m)
    }
  }

  def buildHeap(a: Array[Int], m: Int): Unit = {
    for (i <- m/2 to 0 by -1) {
      heapify(a, i, m)
    }
  }

  /**Pushes an illegally located element down the heap to restore heap property.*/
  @annotation.tailrec
  def heapify(a: Array[Int], loc: Int, lastLeaf: Int): Unit = {
    val l = left(loc)
    val r = right(loc)

    var max = loc

    if(l <= lastLeaf && a(l) > a(max)) max = l
    if(r <= lastLeaf && a(r) > a(max)) max = r

    if(max != loc) {
      swap(a, max, loc)
      heapify(a, max, lastLeaf)
    }
  }

  /**Returns position of left child (possibly empty). */
  def left(loc: Int): Int = {
    return 2*loc
  }

  /**Returns position of right child (possibly empty). */
  def right(loc: Int): Int = {
    return 2*loc+1
  }

  def swap(a: Array[Int], i: Int, j:Int): Unit = {
    val staging = a(i)
    a(i) = a(j)
    a(j) = staging
  }
}