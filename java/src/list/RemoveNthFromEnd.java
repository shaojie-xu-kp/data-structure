package list;

/**
 * Created by shaojie.xu on 27/01/2018.
 */
public class RemoveNthFromEnd {

    /**
     * First we will add an auxiliary "dummy" node, which points to the list head.
     * The "dummy" node is used to simplify some corner cases such as a list with only one node,
     * or removing the head of the list. On the first pass, we find the list length LL.
     * Then we set a pointer to the dummy node and start to move it through the list till it comes to the (L - n)(L−n) th node.
     * We relink next pointer of the (L - n)(L−n) th node to the (L - n + 2)(L−n+2) th node and we are done.
     *
     * Time complexity : O(L)
     * The algorithm makes two traversal of the list, first to calculate list length LL and second to find the (L - n)(L−n) th node.
     * There are 2L-n2L−n operations and time complexity is O(L)
     *
     * Space complexity : O(1) We only used constant extra space.
     *
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode<Integer> onePointerRemove(ListNode<Integer> head, int n) {
        ListNode<Integer> dummy = new ListNode(0);
        dummy.setNext(head);
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.getNext();
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.getNext();
        }
        first.setNext(first.getNext().getNext());
        return dummy.getNext();
    }

    /**
     * Maintain two pointers, first and second
     * first moves n + 1 positions to keep the distance with the second one n positions
     * then move both first and second pointers to the end of the linked list
     * at this point, first pointer is null and second pointer points to the next af next
     * return the dummy head next
     * it does only one loop
     *
     * Time complexity : O(L). The algorithm makes one traversal of the list of LL nodes. Therefore time complexity is O(L).
     * Space complexity : O(1). We only used constant extra space.
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode<Integer> twoPointerRemove(ListNode<Integer> head, int n) {
        ListNode<Integer> dummy = new ListNode(0);
        dummy.setNext(head);
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i <= n; i ++) {
            first = first.getNext();
        }

        while (first != null) {
            first = first.getNext();
            second = second.getNext();
        }

        second.setNext(second.getNext().getNext());

        return dummy.getNext();
    }


    public static void main(String... args) {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> two = new ListNode<>(2);
        ListNode<Integer> three = new ListNode<>(3);
        ListNode<Integer> four = new ListNode<>(4);
        ListNode<Integer> five = new ListNode<>(5);
        head.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(null);
        System.out.println(head.toString());
        System.out.println(onePointerRemove(head,2));
        System.out.println(twoPointerRemove(head,3));

    }


}
