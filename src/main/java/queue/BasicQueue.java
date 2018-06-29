package queue;

/**
 * Created by shaojiexu on 12/3/17.
 */
public class BasicQueue<T> {
    private T[] data;
    private int front;
    private int end;

    public BasicQueue() {
        this(1000);
    }

    public BasicQueue(int size) {
        this.front = -1;
        this.end = -1;
        data = (T[]) new Object[size];
    }

    public int size() {
        if(front == -1 && end == -1) {
            return 0;
        }
        else
        {
            return end - front + 1;
        }
    }

    public void enQueue(T item) {
        if((end + 1) % data.length == front) {
            throw new IllegalStateException("Queue is full");
        } else if (size() == 0) {
            front ++;
            end ++;
            data[end] = item;
        } else {
            data[++end] = item;
        }
    }

    public T deQueue() {

        T item = null;

        if(size() == 0) {
            throw new IllegalStateException("Queue is empty");
        } else if (front == end) {
            item = data[front];
            data[front] = null;
            front = -1;
            end = -1;
        } else {
            item = data[front];
            data[front] = null;
            front ++;
        }

        return item;
    }

    public boolean contains(T item) {

        boolean found = false;

        if (size() == 0) {
            return found;
        }

        for(int i = front; i < end; i++) {
            if (data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public T access (T item) {
        while(front <= end) {
            T matchedItem = deQueue();
            if (item.equals(matchedItem))
            {
                return matchedItem;
            }
        }
        return null;
    }

    public T access (int position) {
        if(size() == 0 || position > size()) {
            throw new IllegalStateException("position does not exist");
        }

        int trueIndex = 0;
        for (int i = front; i < end; i++) {
            if(trueIndex == position) {
                return data[trueIndex];
            }
            trueIndex ++;
        }

        return null;
    }
}
