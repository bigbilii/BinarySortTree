package avl;

/**
 * Created by bigbilii on 2017/6/13.
 *
 */
public class Node {
    private int value;
    private Node left;
    private Node right;
    private Node parent;
    private int blance;


    public Node() {
    }

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
        this.left = null;
        this.right = null;
        this.blance = 0;
    }

    public void addBlance() {
        blance++;
    }

    public void minusBlance() {
        blance--;
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getBlance() {
        return blance;
    }

    public void setBlance(int blance) {
        this.blance = blance;
    }
}
