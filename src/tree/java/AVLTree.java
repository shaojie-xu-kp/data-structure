package tree.java;

import java.util.Objects;

public class AVLTree {

    private AVLNode root;

    public int getBalanceFactor(AVLNode node){
        if(Objects.isNull(node)) return 0;
        return getNodeHeight(node.left) - getNodeHeight(node.right);
    }

    public int max(int v1, int v2) {
        return v1 > v2 ? v1 : v2;
    }

    public int getNodeHeight(AVLNode node){
        if (Objects.isNull(node)) return 0;
        return node.height;
    }

    public AVLNode rightRotate (AVLNode node) {
        AVLNode returnedNode = node.left;
        AVLNode x = returnedNode.right;

        returnedNode.right = node;
        node.left = x;

        node.height = (max(getNodeHeight(node.left), getNodeHeight(node.right))) + 1;
        returnedNode.height = (max(getNodeHeight(returnedNode.left), getNodeHeight(returnedNode.right))) + 1;

        return returnedNode;
    }

    public AVLNode leftRotate (AVLNode node) {
        AVLNode returnedNode = node.right;
        AVLNode x = returnedNode.left;

        returnedNode.left = node;
        node.right = x;

        node.height = (max(getNodeHeight(node.left), getNodeHeight(node.right))) + 1;
        returnedNode.height = (max(getNodeHeight(returnedNode.left), getNodeHeight(returnedNode.right))) + 1;

        return returnedNode;
    }

    public AVLNode insert(AVLNode node, int value) {

        if (Objects.isNull(node))
            return new AVLNode(value);

        if(node.key < value)
            node.right = insert(node.right, value);
        else if(node.key > value)
            node.left = insert(node.left, value);
        else
            return node;

        node.height = max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);

        // right rotate
        if(balanceFactor > 1 && node.left.key > value) {
            return rightRotate(node);
        }

        // left right rotate
        if (balanceFactor > 1 && node.left.key < value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // left rotate
        if(balanceFactor < -1 && node.right.key < value) {
            return leftRotate(node);
        }

        // right left rotate
        if (balanceFactor < -1 && node.right.key > value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void printInOrder(AVLNode node){
        if (Objects.nonNull(node)) {
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }

    public void printPreOrder(AVLNode node){
        if (Objects.nonNull(node)) {
            System.out.print(node.key + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    public static void main(String... args) {
        AVLTree tree = new AVLTree();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 21);
        tree.root = tree.insert(tree.root, 2);
        tree.root = tree.insert(tree.root, 55);

        System.out.print("print pre order : ");
        tree.printPreOrder(tree.root);
        System.out.println();
        System.out.print("print in order : ");
        tree.printInOrder(tree.root);
    }



}


class AVLNode {

    protected int key;
    protected int height;
    protected AVLNode left;
    protected AVLNode right;

    public AVLNode (int k) {
        this.key = k;
        this.height = 1;
    }

}