package stack;

/**
 * Created by shaojiexu on 12/3/17.
 */
public interface StackInterface<X> {

    void push(X newItem);
    X pop();
    int size();
    X access(X item);
    boolean contains(X item);

}
