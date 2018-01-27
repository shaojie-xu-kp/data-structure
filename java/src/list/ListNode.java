package list;

/**
 * Created by shaojie.xu on 27/01/2018.
 */
public class ListNode<T> {

    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        ListNode<T> node = this;
        while(node != null){
            stringBuffer.append(node.data);
            node = node.next;
            if(node != null)
                stringBuffer.append("->");
        }

        return stringBuffer.toString();
    }
}
