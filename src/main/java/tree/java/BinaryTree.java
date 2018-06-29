package tree.java;

import java.util.Objects;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;

    public int height(Node<T> root){
        if(Objects.isNull(root))
            return 0;
        else {
            int lHeight = height(root.left);
            int rHeight = height(root.right);

            return lHeight > rHeight ? lHeight + 1 : rHeight + 1;
        }
    }

    /* Get width of a given level */
    int getWidth(Node<T> node, int level)  {
        if (node == null)
            return 0;
        if (level == 1)
            return 1;
        else if (level > 1)
            return getWidth(node.left, level - 1)
                    + getWidth(node.right, level - 1);
        return 0;
    }

    int getMaxWidth(Node node) {
        int maxWidth = 0;
        int width;
        int height = height(node);
        for (int i = 1; i <= height; i++ ) {
            width = getWidth(node, i);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    public static void main(String... args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = new Node<>(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.right = new Node(8);
        tree.root.right.right.left = new Node(6);
        tree.root.right.right.right = new Node(7);
        System.out.println(tree.getMaxWidth(tree.root));
    }


}


class Node<T extends Comparable<T>>
{
    T data;
    Node left, right;
    public Node(T item)
    {
        data = item;
        left = right = null;
    }
}

