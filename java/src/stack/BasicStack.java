package stack;

/**
 * Created by shaojiexu on 12/3/17.
 */
public class BasicStack<X> implements StackInterface<X> {
    private X [] data;
    private int stackPointer;

    public BasicStack() {
        this.data = (X[]) new Object[1000];
        stackPointer= 0;
    }

    // O(1)
    public void push(X newItem) {
        if (stackPointer == 1001) {
            throw new IllegalStateException("stack is full");
        }

        data[stackPointer++] = newItem;
    }

    // O(1)
    public X pop() {
        if (stackPointer == 0) {
            throw new IllegalStateException("No more items on the stack");
        }
        return data[--stackPointer];
    }

    // O(N)
    public boolean contains(X item) {
        boolean found = false;
        for(int i = 0; i < stackPointer; i++) {
            if(data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    // O(N)
    public X access(X item) {
        while(stackPointer > 0) {
            X matchItem = pop();
            if (item.equals(matchItem)) {
                return matchItem;
            }
        }
        return null;
    }

    // O(1)
    public int size() {
        return stackPointer;
    }
}
