package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaojiexu on 12/3/17.
 */
public class ListStack<X> implements StackInterface<X> {

    private List<X> data;


    public ListStack() {
        this.data = new ArrayList<X>();
    }

    @Override
    public void push(X newItem) {
        data.add(newItem);
    }

    @Override
    public X pop() {

        if (data.size() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        int index = data.size()-1;
        X popItem = data.get(index);
        data.remove(index);
        return popItem;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public X access(X item) {

        while(data.size() > 0) {
            X matchItem = pop();
            if (item.equals(matchItem)) {
                return matchItem;
            }
        }

        return null;
    }

    @Override
    public boolean contains(X item) {
        return data.contains(item);
    }
}
