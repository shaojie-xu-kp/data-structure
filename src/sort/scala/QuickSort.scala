package sort.scala

/**
  * Worst - O(n^2)
  * Best - O(n log n)
  * Average - O(n log n)
  *
  * */
object QuickSort extends App {


  /**
    * use view bound to do a generic quick sort
    * view bound has been deprecated
    * @param l
    * @tparam T
    * @return
    */
  def quickSortOrdered[T <% Ordered[T]](l: List[T]) : List[T] = {
    l match  {
      case Nil => Nil
      case head :: Nil => List(head)
      case head :: tail =>  quickSortOrdered(tail.filter(_ <= head)) ::: List(head) ::: quickSortOrdered(tail.filter(_ > head))
    }
  }

  /**
    * use context bound and implicitly to do a generic quick sort
    * it is scala way of type class pattern
    * it has advantage over the quickSortOrdered method as the type T does not have to be of type Ordered
    * @param l
    * @tparam T
    * @return
    */
  def quickSortOrdring[T: Ordering](l: List[T]) : List[T] = {
    l match  {
      case Nil => Nil
      case head :: Nil => List(head)
      case head :: tail =>
                    quickSortOrdring(tail.filter(implicitly[Ordering[T]].lteq(_, head)))  :::
                    List(head) :::
                    quickSortOrdring(tail.filter(implicitly[Ordering[T]].gt(_, head)))
    }
  }



  val l = List(5, 3, 2, 2, 1, 1, 9, 39 ,219)

  println(quickSortOrdring(l))
  println(quickSortOrdered(l))

  val fruits = List("banana", "apple", "orange","kiwi", "pineapple", "pear")

  println(quickSortOrdring(fruits))
  println(quickSortOrdered(fruits))

  val employee = List(Person("Jack",21), Person("Angela", 58), Person("Jacopo", 30), Person("Anna", 20), Person("Ian",45))
  println(quickSortOrdered(employee))

  implicit val orderByAge : Ordering[Person] = (p1, p2) => p1.age - p2.age

  // sort by name order which is implemented by Person class itself
  println(quickSortOrdered(employee))

  // sort by name which is an implicit ordering strategy
  // and it is implicitly injected into quickSortOrdring
  println(quickSortOrdring(employee))

}

case class Person(name : String, age : Int) extends Ordered[Person] {
  override def compare(that: Person): Int = name.compare(that.name)
}