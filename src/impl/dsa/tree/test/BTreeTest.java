package impl.dsa.tree.test;


import impl.dsa.tree.BTree;

/**
 * @description:
 * @author: zww
 * @date: 2020/4/28
 * @version: V1.0
 */
public class BTreeTest {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(5);
        for(int i=0;i<60;i++){
            bTree.insert(i);
            System.out.println(bTree);
        }
        for(int i=59;i>=0;i--){
            bTree.remove(i);
            System.out.println(bTree);
        }
    }
}
