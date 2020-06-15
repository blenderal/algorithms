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
        System.out.println();
        UniPrint.printTree(tree);

        for (int i = 0; i < 49; i++) {
            int key = new Random().nextInt(49);
            System.out.println("delete:" + key);
            tree.remove(key);
            UniPrint.printTree(tree);
        }

//        int[] number = new int[]{41,21,9,37,3,35,33,46,2,39,41,7,31,12,2,33,0,3,47,39,2,28,26,5,19,12,31,43,25,8,29,2,30,41,8,3,25,5,35,31,20,31,19,14,25,45,29,8,7};
//        for (int i = 0; i < number.length; i++) {
//            tree.insert(number[i],number[i]);
//
//        }
//        int[] nu = new int[]{43,10,40,24,22,47,48,46,11,41,23,30,22,3,31,48,35,19,12,5,33,27,47,33,37,41,47,40,36,11,14,46,6,16,37,33,31,33,9,16,9,25,15,12,43,42,40,30,39};
//        for (int i = 0; i < nu.length; i++) {
//            if(nu[i]==35){
//                System.out.println();
//            }
//            tree.remove(nu[i]);
//            UniPrint.printTree(tree);
//        }
    }

}

