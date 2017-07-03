package test;

import avl.AVLTree;
import bst.ArrayBST;
import bst.BinarySortTree;
import list.List;

/**
 * Created by bigbilii on 2017/6/13.
 *
 */
public class Mune {
    private static final String FILE = "C:\\Users\\bigbilii\\IdeaProjects\\BinarySortTree\\tree1.txt";
    public static void main(String[] args) {
        int[] list = new List(FILE).getList();
        System.out.println("二叉链表-二叉排序树");
        BinarySortTree bst = new BinarySortTree(list);
        System.out.print("中序遍历：");
        bst.middleList();
        System.out.println();
        System.out.println("平均查找长度：" + bst.als());
        System.out.println("深度：" + bst.getDepth());
        bst.delete(4);
        System.out.println();
        System.out.println("--------------------------------");

        System.out.println("二叉链表-平衡二叉树");
        AVLTree avlt = new AVLTree(list);
        System.out.print("中序遍历：");
        avlt.middleList();
        System.out.println();
        System.out.println("平均查找长度：" + avlt.als());
        System.out.println("深度：" + avlt.getDepth());

        System.out.println("--------------------------------");

        ArrayBST arrayBST = new ArrayBST(list);
        System.out.println("顺序表-二叉排序树");
        System.out.print("中序遍历：");
        arrayBST.middleList();
        System.out.println();
        System.out.println("平均查找长度：" + arrayBST.als());
    }
}
