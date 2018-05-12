package tree.java;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class BST {

    public static void main(String... args) {
        int[] numbers = IntStream.generate(() -> new Random().nextInt(20)).limit(20).toArray();
        BSTNode<Integer> root = new BSTNode<>(numbers[0]);
        for(int i = 1; i < numbers.length; i++) {
            root.insert(numbers[i]);
        }

        root.printInOrder();

        System.out.println(root.contains(0));
    }


}


class BSTNode<T extends Comparable<T>> {

    private BSTNode left;
    private BSTNode right;
    private T value;

    public BSTNode(T data) {
        this.value = data;
    }

    public void insert (T data) {
        if (value.compareTo(data) >= 0) {
            if (Objects.isNull(left)) {
                left = new BSTNode(data);
            } else {
                left.insert(data);
            }
        } else {
            if (Objects.isNull(right)) {
                right = new BSTNode(data);
            } else {
                right.insert(data);
            }
        }
    }

    public boolean contains (T data) {
        if(Objects.equals(value, data)) return true;
        if(value.compareTo(data) > 0) {
            if(Objects.isNull(left)) return false;
            else left.contains(data);
        } else {
            if (Objects.isNull(right)) return false;
            else right.contains(data);
        }
        return false;
    }

    public void printInOrder() {
        if (Objects.nonNull(left)) left.printInOrder();
        System.out.println(value);
        if (Objects.nonNull(right)) right.printInOrder();
    }


}