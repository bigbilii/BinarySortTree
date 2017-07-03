package avl;


/**
 * Created by bigbilii on 2017/6/13.
 *
 */
public class AVLTree {

    private static final int LH = 1; //左高
    private static final int RH = -1; //右高
    private static final int EH = 0; //等高

    Node root = null;

    int size = 0;

    public int getSize() {
        return size;
    }

    public AVLTree(int[] list) {
        for (int i : list) {
            insertNode(i);
        }
    }

    private int compare(int a, int b) {
        return a - b;
    }

    public void insertNode(int key) {
        Node p = root;
        if (p == null) { //树为空
            root = new Node(key,null);
            size = 1;
            return;
        }

        int cmp;
        Node parent;

        do{ //找到插入点
            parent = p;
            cmp = compare(key, p.getValue());
            if (cmp < 0) {
                p = p.getLeft();
            }else if (cmp > 0) {
                p = p.getRight();
            }else {
                p.setValue(key);
                return;
            }
        }while(p != null);

        Node newNode = new Node(key, parent);
        if (cmp < 0) {
            parent.setLeft(newNode);
        }else {
            parent.setRight(newNode);
        }

        //从下往上回溯，找到不平衡点
        while (parent != null) {
            cmp = compare(key, parent.getValue()); //找到插入在左边还是右边
            if (cmp < 0) {
                parent.addBlance();
            }else {
                parent.minusBlance();
            }

            if (parent.getBlance() == 0) {
                break;
            }

            if (Math.abs(parent.getBlance()) == 2) { //最小不平衡节点
                fixAVLTree(parent);
                break; //不再回溯
            }
            parent = parent.getParent();
        }
        size++;
    }

    private void fixAVLTree(Node node) {
        if (node.getBlance() == 2) { //左子树高于右子树
            leftBalance(node);
        }
        if (node.getBlance() == -2) { //右子树高于左子树
            rightBalance(node);
        }
    }

    private void leftBalance(Node node) {
        Node l = node.getLeft();
        switch (l.getBlance()) {
            case LH : //左左
                node.setBlance(0);
                l.setBlance(0);
                rotateRight(node); //右旋转
                break;
            case RH : //左右
                Node lr = l.getRight();
                switch (lr.getBlance()) {
                    case RH :
                        node.setBlance(0);
                        l.setBlance(1);
                        break;
                    case LH :
                        node.setBlance(-1);
                        l.setBlance(0);
                        break;
                    case EH :
                        node.setBlance(0);
                        l.setBlance(0);
                        break;
                }
                lr.setBlance(0);
                rotateLeft(node.getLeft());
                rotateRight(node);
        }
    }

    private void rightBalance(Node node) {
        Node r = node.getRight();
        switch (r.getBlance()) {
            case LH : //右左
                Node rl = r.getLeft();
                switch (rl.getBlance()) {
                    case RH :
                        node.setBlance(1);
                        r.setBlance(0);
                        break;
                    case LH :
                        node.setBlance(-    1);
                        r.setBlance(0);
                        break;
                    case EH :
                        node.setBlance(0);
                        r.setBlance(0);
                        break;
                }
                rl.setBlance(0);
                rotateRight(node.getRight());
                rotateLeft(node);
                break;
            case RH : //右右
                node.setBlance(0);
                r.setBlance(0);
                rotateLeft(node);
                break;

        }
    }

    private void rotateLeft(Node node) { //左旋转
        if(node != null) {
            Node r = node.getRight(); //获取node的右子树
            node.setRight(r.getLeft()); //把r的左节点嫁接到node的右节点
            if (r.getLeft() != null) { //如果r的左节点不为空，把r的左节点的父节点设为node
                r.getLeft().setParent(node);
            }
            r.setParent(node.getParent());//r的父节点设为node的父节点

            if (node.getParent() == null) { //如果node是根节点
                root = r;
            }else if (node.getParent().getLeft() == node) { //如果node是左子节点
                node.getParent().setLeft(r);
            }else { //如果node是右子节点
                node.getParent().setRight(r);
            }

            r.setLeft(node); //node变为r的左子树
            node.setParent(r); //node的父节点设为r
        }
    }

    private void rotateRight(Node node){ //右旋转
        if(node != null){
            Node l = node.getLeft();
            node.setLeft(l.getRight());
            if (l.getRight() != null) {
                l.getRight().setParent(node);
            }
            l.setParent(node.getParent());
            if (node.getParent() == null) {
                root = l;
            }else if (node.getParent().getRight() == node) {
                node.getParent().setRight(l);
            }else {
                node.getParent().setLeft(l);
            }
            l.setRight(node);
            node.setParent(l);
        }
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

    private int als(Node node, int cs) {
        if (node == null) {
            return 0;
        }
        int num = cs;
//        int num = getChildNum(node) * (cs + 1);
        num += als(node.getLeft(),cs + 1);
        num += als(node.getRight(),cs + 1);
        return num;
    }

    public double als() {
        return (als(root, 1) * 1.0) / (size * 1.0);
    }

    public void fistList() {
        fistList(root);
    }

    private void fistList(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + " ");
        fistList(node.getLeft());
        fistList(node.getRight());
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
