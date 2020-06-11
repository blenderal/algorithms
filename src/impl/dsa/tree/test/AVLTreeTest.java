package impl.dsa.tree.test;

import impl.dsa.tree.AVLTree;

import java.util.Random;

/**
 * @description:
 * @author: zww
 * @date: 2020/6/11
 * @version: V1.0
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        for (int i = 0; i < 49; i++) {
            tree.insert(new Random().nextInt(49), 1);
        }
        UniPrint.printTree(tree);
        for (int i = 0; i < 49; i++) {
            int key = new Random().nextInt(49);
            tree.remove(key);
            System.out.println("删除" + key);
            UniPrint.printTree(tree);
        }
    }
}
