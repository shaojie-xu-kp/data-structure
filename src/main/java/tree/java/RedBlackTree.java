package tree.java;

import java.util.Objects;

import static tree.java.Color.BLACK;
import static tree.java.Color.RED;

/**
 * properties of RBT:
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
 * Below is an iterative version of implementation, you will find the similarities
 * to the java.util.TreeMap implementation of JDK source
 *
 *
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private RBNode<K, V> root;

    public static void main(String[] args) {

        RedBlackTree<Integer, String> tree = new RedBlackTree();
        tree.insert(10,"test");
        tree.insert(20,"test");
        tree.insert(30,"test");
        tree.insert(40,"test");
        tree.insert(50,"test");
        tree.insert(51,"test");
        tree.insert(48,"test");
        tree.insert(50,"test");
        tree.insert(25,"test");
        tree.insert(12,"test");
        tree.insert(21,"test");
        tree.insert(2,"test");
        tree.insert(55,"test");
        tree.insert(60,"test");
        tree.insert(90,"test");
        tree.insert(61,"test");

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

    /**
     * fix up the whole tree after each insertion starting from the newly inserted node
     * first check if there are adjacent RED nodes
     * if so, rule is broken and two things should be done:
     * 1. color flipping if aunt is RED
     *    set parent to BLACK
     *    set aunt to BLACK
     *    set grandparent to RED
     * 2. rotation if aunt is BLACK
     *    2.1 left-left   case -> right rotate grandparent
     *    2.2 left-right  case -> left rotate parent, then right rotate grandparent
     *    2.3 right-left  case -> right rotate parent, then left rotate grandparent
     *    2.4 right-right case -> left rotate grandparent
     *
     * at the end, always set root to BLACK
     *
     * @param x
     */
    private void fixAfterInsertion(RBNode<K,V> x) {

        x.color = RED;

        // while there is adjacent RED node
        while (x != null && x != root && x.parent.color.equals(RED)) {
            // if your parent is the left child of the grandparent node
            // then we will have either left-left case or left-right case
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                // the aunt of the newly inserted node
                RBNode<K,V> y = rightOf(parentOf(parentOf(x)));
                // if it is RED, meaning we have to do a color flipping
                if (colorOf(y) == RED) {
                    // set color of parent to black
                    setColor(parentOf(x), BLACK);
                    // set the color of aunt to black
                    setColor(y, BLACK);
                    // set grandparent to red
                    setColor(parentOf(parentOf(x)), RED);
                    // move on to check the grandparent
                    x = parentOf(parentOf(x));
                    // if the aunt is not RED, then BLACK, meaning we do a rotation
                } else {
                    // check if the current node is a right child
                    // if so, since we are at the left of the unbalance tree, it is the case left-right case
                    // we do a left rotation of the parent node
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    // it is the left-left case, we only do a right rotation
                    // before doing a rotate, fix the color first
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else { // if your parent is the right child of the grandparent node, we will have either right-left case, or right-right case
                // y is the aunt
                RBNode<K,V> y = leftOf(parentOf(parentOf(x)));
                // if aunt is RED, color flipping
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    // right-left case, do right rotation of the parent node
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    // right-right case, simply a left rotation will do the magic
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

    /**
     * left rotate of node
     * bring up the right child of the p to one level up by linking the parent of p to parent of p's right child
     * link the left child of the right child to the right child of p
     * and fix up the parent linkages
     * @param p node to be left rotated
     */
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
            if (l.right != null)
                l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else
                p.parent.left = l;
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