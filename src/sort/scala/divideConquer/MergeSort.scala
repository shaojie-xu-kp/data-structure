package sort.scala.divideConquer

/**
  * Worst - O(n log n)
  * Best - O(n log n)
  * Average - O(n log n)
  *
  */
object MergeSort extends App {


  def merge[T : Ordering](l1: List[T], l2:List[T]) : List[T] = {
    (l1, l2) match {
      case (l1, Nil) => l1
      case (Nil, l2) => l2
      case (l1Head :: l1Tail, l2Head :: l2Tail) =>
        if(implicitly[Ordering[T]].lteq(l1Head, l2Head))  l1Head::merge(l1Tail, l2)
        else l2Head::merge(l1, l2Tail)
    }
  }

  def mergeSort[T : Ordering](l : List[T]) : List[T] = {
    l match {
      case Nil => Nil
      case lhead :: Nil => l
      case lhead :: ltail =>
        val (left, right) = l.splitAt(l.length/2)
        merge(mergeSort(left), mergeSort(right))
    }
  }

  implicit val orderByAge : Ordering[Person] = (p1, p2) => p1.age - p2.age
  implicit val orderByName : Ordering[Person] = (p1, p2) => p1.name.compare(p2.name)

  val employee = List(Person("Jack",21), Person("Angela", 58), Person("Jacobo", 30), Person("Anna", 20), Person("Ian",45))
  println(mergeSort(employee)(orderByAge))
  println(mergeSort(employee)(orderByName))

}
