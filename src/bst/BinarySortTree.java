package bst;

/**
 * Created by bigbilii on 2017/6/12.
 *
 */
public class BinarySortTree {
    private Node root = null; //根

    public BinarySortTree(int[] list) {
        for (int i : list) {
            insertNode(i);
        }
    }

    public int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getN();
    }

    public void insertNode(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.getValue()) {
           node.setLeft(insertNode(node.getLeft(),key));
        }else if (key > node.getValue()) {
            node.setRight(insertNode(node.getRight(),key));
        }else {
            node.setValue(key);
        }
        node.setN(size(node.getRight()) + size(node.getLeft()) + 1);
        return node;
    }

    public void middleList() {
        middleList(root);
    }

    private void middleList(Node node) {
        if (node == null) {
            return;
        }
        middleList(node.getLeft());
        System.out.print(node.getValue() + " ");
        middleList(node.getRight());
    }

    private int als(Node node, int level) {
        if (node == null) {
            return 0;
        }
        int num = level;
//        int num = getChildNum(node) * (level + 1);
        num += als(node.getLeft(),level + 1);
        num += als(node.getRight(),level + 1);
        return num;
    }

    public double als() {
        return (als(root, 1) * 1.0) / (root.getN() * 1.0);
    }

    private Node min(Node node) {
        if (node.getLeft() == null) {
            return node;
        }else {
            return min(node.getLeft());
        }
    }

    private Node deleteMin(Node node) {
        if(node.getLeft() == null){
            return node.getRight(); //删除根节点,这时返回的是新的二叉查找树的根节点
        }
        node.setLeft(deleteMin(node.getLeft()));
        node.setN(size(node.getRight()) + size(node.getLeft()) + 1);
        return node;
    }

    public void delete(int key) {
        int startSize = root.getN();
        delete(root,key);
        int endSize = root.getN();
        if (startSize != endSize) {
            middleList();
        }else {
            System.out.println("无" + key + "值");
        }
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.getValue()) {
            node.setLeft(delete(node.getLeft(),key));
        }else if (key > node.getValue()) {
            node.setRight(delete(node.getRight(),key));
        }else { //删除node节点
            if (node.getRight() == null) {
                return node.getLeft();
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            Node t = node;
            node = min(t.getRight());
            node.setRight(deleteMin(t.getRight()));
            node.setLeft(t.getLeft());
        }
        node.setN(size(node.getRight()) + size(node.getLeft()) + 1);
        return node;
    }

    public int getDepth() {
        return getDepth(root);
    }

    private int getDepth(Node node) {
        int m, n;
        if (node == null) {
            return 0;
        }
        m = getDepth(node.getLeft());
        n = getDepth(node.getRight());
        return (m > n ? m : n) + 1;
    }
}
