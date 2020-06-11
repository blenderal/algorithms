package impl.dsa.tree.test;

import impl.dsa.tree.RedBlackTree;

import java.util.Random;

/**
 * @description:
 * @author: zww
 * @date: 2020/6/9
 * @version: V1.0
 */
public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
///        tree.insert(6, 6);
//        tree.insert(9, 9);
//        tree.insert(7, 7);
//        tree.insert(4, 4);
//        tree.insert(5, 5);
//        tree.insert(8, 8);
//        tree.insert(1, 1);
//        tree.insert(2, 2);
//        tree.insert(3, 3);
//        tree.insert(10, 10);
//        tree.insert(12, 12);
//        tree.insert(15, 15);
//        tree.insert(18, 18);
//        UniPrint.print2(tree);
//        tree.insert(30, 30);
//        UniPrint.print2(tree);
//        tree.insert(29, 29);
//        UniPrint.print2(tree);
//        tree.insert(17, 17);
//        UniPrint.print3(tree);


        for (int i = 0; i < 49; i++) {
            int key = new Random().nextInt(49);
            tree.insert(key, key);
        }
        UniPrint.printTree(tree);
        System.out.println(tree.find(26).getValue());
    }

}

