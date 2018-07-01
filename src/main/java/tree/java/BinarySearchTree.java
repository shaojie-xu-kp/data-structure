package tree.java;

import java.util.*;

public class BinarySearchTree {

    public static void main(String... args) {
        BSTNode<Integer> root = new BSTNode<>(30);
        root.insert(20);
        root.insert(25);
        root.insert(10);
        root.insert(2);
        root.insert(12);
        root.insert(35);
        root.insert(32);
        root.insert(31);
        root.insert(33);
        root.insert(50);
        root.insert(40);
        root.insert(55);
        root.inOrderTraversalRec();
        System.out.print('\n');
        System.out.println(root.contains(11));
        System.out.print('\n');
        root.remove(35);
        System.out.println("\npre order traversal : ");
        root.preOrderTravesalRec();
        System.out.println("\npre order traversal : ");
        root.preOrderTraversal();
        System.out.println("\nin order traversal : ");
        root.inOrderTraversal();
        System.out.println("\nlevel order traversal : ");
        root.levelOrderTraversal();


    }

}


class BSTNode<T extends Comparable<T>> {

    private BSTNode left, right;
    private T value;

    public BSTNode(T data) {
        this.value = data;
    }

    public void insert (T data) {
        if (value.compareTo(data) >= 0) {
            if (Objects.isNull(left)) {
                this.left = new BSTNode(data);
            } else {
                left.insert(data);
            }
        } else {
            if (Objects.isNull(right)) {
                this.right = new BSTNode(data);
            } else {
                right.insert(data);
            }
        }
    }

    public void remove(T data) {
        deleteNode(this, data);
    }

    private BSTNode<T> deleteNode(BSTNode<T> root, T data) {
       if(Objects.isNull(root)) return root;

       if (root.value.compareTo(data) > 0) {
           root.left = deleteNode(root.left, data);
       } else if (root.value.compareTo(data) < 0) {
           root.right = deleteNode(root.right, data);
       } else {
           if (Objects.isNull(root.left)) {
               return root.right;
           } else if (Objects.isNull(root.right)) {
               return root.left;
           }

           BSTNode<T> rightSuccessor = rightSuccessor(root);
           root.value = rightSuccessor.value;
           root.right = deleteNode(root.right, root.value);
       }

       return root;
    }

    private BSTNode<T> rightSuccessor(BSTNode<T> node) {
        if (node == null)
            return null;
        else if (node.right != null) {
            BSTNode<T> p = node.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else {
            return null;
        }
    }


    public BSTNode<T> getNode(T data) {
        if(Objects.equals(value, data)) return this;
        if(value.compareTo(data) > 0) {
            if(Objects.isNull(left)) return null;
            else {
                return left.getNode(data);
            }
        } else {
            if (Objects.isNull(right)) return null;
            else {
                return right.getNode(data);
            }
        }
    }

    public boolean contains (T data) {
        if(Objects.equals(value, data))
            return true;
        if(value.compareTo(data) > 0) {
            if(Objects.isNull(left))
                return false;
            else {
                return left.contains(data);
            }
        } else {
            if (Objects.isNull(right))
                return false;
            else {
                return right.contains(data);
            }
        }
    }


    public void inOrderTraversalRec() {
        if (Objects.nonNull(left)) left.inOrderTraversalRec();
        System.out.print(value + " ");
        if (Objects.nonNull(right)) right.inOrderTraversalRec();
    }

    public void preOrderTravesalRec() {
        System.out.print(value + " ");
        if (Objects.nonNull(left)) left.preOrderTravesalRec();
        if (Objects.nonNull(right)) right.preOrderTravesalRec();
    }


    public void preOrderTraversal() {

        if (Objects.isNull(this)) return;

        Deque<BSTNode> printStack = new ArrayDeque<BSTNode>();
        printStack.push(this);

        while(!printStack.isEmpty()) {

            BSTNode node = printStack.pop();
            System.out.print(node.value + " ");

            if(Objects.nonNull(node.right))
                printStack.push(node.right);
            if(Objects.nonNull(node.left))
                printStack.push(node.left);

        }
    }

    void levelOrderTraversal() {

        if (Objects.isNull(this)) return;

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            BSTNode node = queue.poll();
            System.out.print(node.value + " ");

            if(Objects.nonNull(node.left))
                queue.add(node.left);

            if(Objects.nonNull(node.right))
                queue.add(node.right);

        }
    }

    public void inOrderTraversal() {

        Deque<BSTNode> nodes = new ArrayDeque<>();
        BSTNode current = this;

        while (!nodes.isEmpty() || current != null) {

            if (current != null) {
                nodes.push(current);
                current = current.left;
            } else {
                BSTNode node = nodes.pop();
                System.out.printf("%s ", node.value);
                current = node.right;
            }

        }


    }



}