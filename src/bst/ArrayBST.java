package bst;

/**
 * Created by bigbilii on 2017/6/14.
 *
 */
public class ArrayBST {
    int[] tree;
    int size;

    public ArrayBST(int[] list) {
        tree = new int[1024];
        int count;
        tree[1] = list[0];
        size = 1;
        for (int i = 1; i < list.length; i++) {
            count = 1;
            int key = list[i];
            while(tree[count] != 0) {
                if (key < tree[count]) {
                    count = count * 2;
                }else {
                    count = count * 2 + 1;
                }
            }
            tree[count] = key;
            size ++;
        }
    }

    public void middleList() {
        middleList(1);
    }

    private void middleList(int count) {
//        int count = 1;
//        while(tree[count * 2] != 0) {
//            count = count * 2;
//        }
        if (tree[count] == 0) {
            return;
        }

        middleList(count * 2);
        System.out.print(tree[count] + " ");
        middleList(count * 2 + 1);
    }

    public double als() {
        return (als(1, 1) * 1.0) / (size * 1.0);
    }

    private int als(int count, int level) {
        if (tree[count] == 0) {
            return 0;
        }
        int num = level;
        num += als(count * 2, level + 1);
        num += als(count * 2 + 1, level + 1);
        return num;
    }
}
