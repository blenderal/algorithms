package impl.dsa.tree.test;

import impl.dsa.tree.RBTree;

import java.util.Random;

/**
 * @description:
 * @author: zww
 * @date: 2020/6/9
 * @version: V1.0
 */
public class RedBlackTreeTest {
    public static void main(String[] args) {
        RBTree<Integer, Integer> tree = new RBTree<>();
        for (int i = 0; i < 49; i++) {
            int key = new Random().nextInt(49);
            System.out.println("insert:" + key);
            tree.insert(key, key);
//            UniPrint.printTree(tree);
        }
        System.out.println();
        UniPrint.printTree(tree);
        for (int i = 0; i < 49; i++) {
            int key = new Random().nextInt(49);
//            System.out.println("delete:" + key);
//            System.out.println(tree.remove(key));
//            UniPrint.printTree(tree);
        }
    }

}

