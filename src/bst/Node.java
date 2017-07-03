package bst;

/**
 * Created by bigbilii on 2017/6/12.
 *
 */
public class Node {
    private int value;
    private Node left;
    private Node right;
    private int N;

    public Node(int value) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.N = 1;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

}
