package list;

/**
 * Created by shaojie.xu on 27/01/2018.
 */
public class MergeTwoSortedList {

    /**
     * We model the above recurrence directly, first accounting for edge cases.
     * Specifically, if either of l1 or l2 is initially null, there is no merge to perform,
     * so we simply return the non-null list. Otherwise, we determine which of l1 and l2 has a smaller head,
     * and recursively set the next value for that head to the next merge result.
     * Given that both lists are null-terminated, the recursion will eventually terminate.​​
     *
     * Time complexity : O(n + m)
     * Because each recursive call increments the pointer to l1 or l2 by one (approaching the dangling null at the end of each list),
     * there will be exactly one call to mergeTwoLists per element in each list.
     * Therefore, the time complexity is linear in the combined size of the lists.
     *
     * Space complexity : O(n + m)
     * The first call to mergeTwoLists does not return until the ends of both l1 and l2 have been reached,
     * so n + m stack frames consume O(n+m) space.
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode recursiveMerge(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null)
        {
            return l1;
        }
        else if (l1.getData() < l2.getData()) {
            l1.setNext(recursiveMerge(l1.getNext(), l2));
            return l1;
        }
        else {
            l2.setNext(recursiveMerge(l1, l2.getNext()));
            return l2;
        }
    }


    /**
     * First, we set up a false "prehead" node that allows us to easily return the head of the merged list later.
     * We also maintain a prev pointer, which points to the current node for which we are considering adjusting its next pointer.
     * Then, we do the following until at least one of l1 and l2 points to null:
     *      if the value at l1 is less than or equal to the value at l2, then we connect l1 to the previous node and increment l1.
     *      Otherwise, we do the same, but for l2. Then, regardless of which list we connected,
     *      we increment prev to keep it one step behind one of our list heads.
     * After the loop terminates, at most one of l1 and l2 is non-null.
     * Therefore (because the input lists were in sorted order),
     * if either list is non-null,
     * it contains only elements greater than all of the previously-merged elements.
     * This means that we can simply connect the non-null list to the merged list and return it.
     *
     *
     * Time complexity : O(n + m)  Because exactly one of l1 and l2 is incremented on each loop iteration,
     * the while loop runs for a number of iterations equal to the sum of the lengths of the two lists.
     * All other work is constant, so the overall complexity is linear.
     *
     * Space complexity : O(1)
     * The iterative approach only allocates a few pointers, so it has a constant overall memory footprint.
     *
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode iterativeMerge(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> dummyHead = new ListNode(0);
        ListNode previous = dummyHead;
        while (l1 != null && l2 != null) {
            if(l1.getData() < l2.getData()) {
                previous.setNext(l1);
                l1 = l1.getNext();
            } else {
                previous.setNext(l2);
                l2 = l2.getNext();
            }
            previous = previous.getNext();
        }

        previous.setNext(l1 == null ? l2 : l1);

        return dummyHead.getNext();
    }

    /**
     * Merge lists one by ones
     * Convert merge k lists problem to merge 2 lists (k-1) times
     *
     * Time complexity : O(kN) where k is the number of linked lists.
     * Space complexity : O(1).
     *
     * @param lists
     * @return
     */
    public static ListNode mergeNLists(ListNode<Integer>[] lists) {

        ListNode<Integer> dummyList = new ListNode<>(0);

        for(ListNode<Integer> list : lists)  {
            dummyList = recursiveMerge(dummyList, list);
        }

        return  dummyList;

    }

    /**
     * Merge K lists with divide and conquer method
     * This approach walks alongside the one above but is improved a lot
     * Pair up k lists and merge each pair
     * After the first pairing, k lists are merged into k/2 lists, with average Nk/2 length
     * Repeat this procedure until we get the final sorted linked list
     * Thus, we'll traverse almost NN nodes per pairing and merging, and repeat this procedure about log(k) times.
     * Time complexity : O(Nlog(k)) where k is the number of linked lists.
     * Space complexity : O(1)
     *
     * {list0, list1, list2, list3, list4}
     *     |     |      |      |      |                     fist merge
     *      list0        list2     list4
     *        |            |         |                      second merge
     *           list0         list4
     *             |             |                          third merge
     *                  list0
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKlists(ListNode<Integer>[] lists) {
        int k = lists.length;
        int interval = 1;
        while (interval < k) {
            for (int i = 0; i < k - interval; i += interval * 2) {
                lists[i] = recursiveMerge(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    public static void main(String... args) {
        ListNode<Integer> head1 = new ListNode<>(1);
        ListNode<Integer> head2 = new ListNode<>(2);
        ListNode<Integer> two = new ListNode<>(2);
        ListNode<Integer> three = new ListNode<>(3);
        ListNode<Integer> four = new ListNode<>(4);
        ListNode<Integer> five = new ListNode<>(5);
        head1.setNext(two);
        two.setNext(four);
        head2.setNext(three);
        three.setNext(five);
        System.out.println(head1.toString());
        System.out.println(head2.toString());
//        ListNode<Integer> mergedList1 = recursiveMerge(head1, head2);
//        System.out.println(mergedList1);


        ListNode<Integer> head3 = new ListNode<>(10);
        ListNode<Integer> head4 = new ListNode<>(20);
        ListNode<Integer> twenty = new ListNode<>(20);
        ListNode<Integer> thirty = new ListNode<>(30);
        ListNode<Integer> forty = new ListNode<>(40);
        ListNode<Integer> fifty = new ListNode<>(50);
        head3.setNext(twenty);
        twenty.setNext(forty);
        head4.setNext(thirty);
        thirty.setNext(fifty);
        System.out.println(head3.toString());
        System.out.println(head4.toString());

//        ListNode<Integer> mergedList2 = iterativeMerge(head3, head4);
//        System.out.println(mergedList2);

        ListNode  listNodes[] = {head1, head2, head3, head4};
        System.out.println(mergeKlists(listNodes));




    }

}
