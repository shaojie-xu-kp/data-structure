package tree.java;

import java.util.Objects;

import static tree.java.Color.BLACK;
import static tree.java.Color.RED;

/**
 * properties or RBT:
 *
 * 1. each node is neither black or red
 * 2. root is always black
 * 3. no two adjacent red node
 * 4. every path from root to null node has same number of black nodes
 *
 * operations to maintain its properties
 * 1. color flipping in case aunt is red
 * 2. rotation in case aunt is black
 * here null node is considered as black
 *
 * Below is the iterative version of implementation
 *
 *
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private RBNode<K, V> root;

    public static void main(String[] args) {

        RedBlackTree<Integer, String> tree = new RedBlackTree();
        tree.insert(1,"test");
        tree.insert(2,"test");
        tree.insert(3,"test");
        tree.insert(4,"test");
        tree.insert(5,"test");
        tree.insert(6,"test");


        System.out.print("print pre order : ");
        tree.printPreOrder(tree.root);
        System.out.println();
        System.out.print("print in order : ");
        tree.printInOrder(tree.root);



    }

    public void printInOrder(RBNode node){
        if (Objects.nonNull(node)) {
            printInOrder(node.left);
            System.out.print(node);
            printInOrder(node.right);
        }
    }

    public void printPreOrder(RBNode node){
        if (Objects.nonNull(node)) {
            System.out.print(node);
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }


    public V insert(K key, V value) {

        if (Objects.isNull(key))
            throw new NullPointerException();

        RBNode<K, V> t = root;
        if(Objects.isNull(t)) {
            root = new RBNode<>(key, value);
            return value;
        }

        int cmp;
        RBNode<K, V> parent;
        do {
            parent = t;
            cmp = key.compareTo(t.key);
            if (cmp < 0)
                t = t.left;
            else if (cmp > 0)
                t = t.right;
            else
                return t.value;
        } while (t != null);

        RBNode<K, V> newNode = new RBNode<>(key, value);
        newNode.parent = parent;
        if (cmp < 0)
            parent.left = newNode;
        else
            parent.right = newNode;

        fixAfterInsertion(newNode);

        return value;
    }

    /** From CLR */
    private void fixAfterInsertion(RBNode<K,V> x) {

        x.color = RED;

        while (x != null && x != root && x.parent.color.equals(RED)) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                RBNode<K,V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                RBNode<K,V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }


    private static Color colorOf(RBNode p) {
        return (p == null ?  Color.BLACK : p.color);
    }

    private static <K extends Comparable<K>, V> RBNode<K,V> parentOf(RBNode<K,V> p) {
        return (p == null ? null: p.parent);
    }

    private static <K extends Comparable<K>,V> void setColor(RBNode<K,V> p, Color color) {
        if (p != null)
            p.color = color;
    }

    private static <K extends Comparable<K>,V> RBNode<K,V> leftOf(RBNode<K,V> p) {
        return (p == null) ? null: p.left;
    }

    private static <K extends Comparable<K>,V> RBNode<K,V> rightOf(RBNode<K,V> p) {
        return (p == null) ? null: p.right;
    }

    private void rotateLeft(RBNode<K,V> p) {
        if (p != null) {
            RBNode<K,V> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    private void rotateRight(RBNode<K,V> p) {
        if (p != null) {
            RBNode<K,V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }


}


class RBNode <K extends Comparable<K>, V> {

    protected K key;
    protected V value;
    protected RBNode left, right, parent;
    protected Color color;

    public RBNode (K key, V value) {
        this.key = key;
        this.value = value;
        this.color = RED;
    }

    public String toString() {
        return String.format("[%s - %s : %s]", key, value, color);
    }

}

enum Color {
    RED,
    BLACK
}