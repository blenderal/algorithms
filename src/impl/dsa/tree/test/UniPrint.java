package impl.dsa.tree.test;

import impl.dsa.Iterator;
import impl.dsa.tree.*;

import java.util.HashMap;

/**
 * @description:
 * @author: zww
 * @date: 2020/6/10
 * @version: V1.0
 */
public class UniPrint {

    public static <K extends Comparable<K>, V> void printTree(AbstractBSTree<K, V> tree) {
        if (tree.isEmpty()) {
            return;
        }
        int length = getMaxLength(tree);
        String apd = getApd(length);
        String empty = getEmpty(length);
        Object[][] table = getKeyIndexTable(tree, apd);
        for (Object[] objects : table) {
            for (Object object : objects) {
                if (object == null) {
                    System.out.print(empty);
                    continue;
                }
                if (object instanceof String) {
                    System.out.print(object);
                    continue;
                }
                AbstractBinaryTreeNode<K, V> bsNode = (AbstractBinaryTreeNode<K, V>) object;
                StringBuilder key = new StringBuilder(bsNode.getKey().toString());
                int keyLength = key.length();
                for (int i = 0; i < length - keyLength; i++) {
                    key.insert(0, "0");
                }
                System.out.print(key);
            }
            System.out.println();
        }
    }


    public static <K extends Comparable<K>, V> void printTree(RedBlackTree<K, V> tree) {
        if (tree.isEmpty()) {
            return;
        }
        RedBlackTreeNode<K, V> max = (RedBlackTreeNode<K, V>) tree.findMax();
        int length = max.getKey().toString().length();
        String apd = getApd(length);
        String empty = getEmpty(length);
        Object[][] table = getKeyIndexTable(tree, apd);
        for (Object[] objects : table) {
            for (Object object : objects) {
                if (object == null) {
                    System.out.print(empty);
                    continue;
                }
                if (object instanceof String) {
                    System.out.print(object);
                    continue;
                }
                RedBlackTreeNode<K, V> rbNode = (RedBlackTreeNode<K, V>) object;
                StringBuilder key = new StringBuilder(rbNode.getKey().toString());
                int keyLength = key.length();
                for (int i = 0; i < length - keyLength; i++) {
                    key.insert(0, "0");
                }
                System.out.print("\033[" + (rbNode.isBlack() ? 30 : 31) + ";1m" + key + "\033[0m");
            }
            System.out.println();
        }
    }

    /**
     * 获取索引在打印时的位置
     * @param tree 树
     * @param apd 补全的符号位
     * @param <K> K
     * @param <V> V
     * @return 索引在打印时的位置表
     */
    public static <K extends Comparable<K>, V> Object[][] getKeyIndexTable(AbstractBinaryTree<K, V> tree, String apd) {
        AbstractBinaryTreeNode<K, V> root = tree.getRoot();
        int height = root.getHeight();
        Object[][] table = new Object[height + 1][(int) Math.pow(2, height + 1) - 1];
        Iterator<TreeNode<K, V>> iterator = tree.levelorder();
        HashMap<K, Integer> map = new HashMap<>(tree.getSize());
        while (iterator.hasNext()) {
            AbstractBinaryTreeNode<K, V> node = (AbstractBinaryTreeNode<K, V>) iterator.getNext();
            int h = height - node.getDepth();
            int parentPos = node.hasParent() ? map.get(node.getParent().getKey()) : 1;
            int currentPos = node.hasParent() ? (node.isLeftChild() ? 2 * parentPos - 1 : 2 * parentPos) : parentPos;
            int currentIndex = (int) (Math.pow(2, h) - 1 + (currentPos - 1) * (Math.pow(2, h + 1)));
            table[node.getDepth()][currentIndex] = node;
            if (node.hasLeftChild()) {
                int leftChildPos = currentPos * 2 - 1;
                int leftChildIndex = (int) (Math.pow(2, h - 1) - 1 + (leftChildPos - 1) * (Math.pow(2, h)));
                table[node.getDepth()][leftChildIndex] = "┌" + apd;
                for (int i = leftChildIndex + 1; i < currentIndex; i++) {
                    table[node.getDepth()][i] = "-" + apd;
                }
            }
            if (node.hasRightChild()) {
                int rightChildPos = currentPos * 2;
                int rightChildIndex = (int) (Math.pow(2, h - 1) - 1 + (rightChildPos - 1) * (Math.pow(2, h)));
                table[node.getDepth()][rightChildIndex] = apd + "┐";
                for (int i = currentIndex + 1; i < rightChildIndex; i++) {
                    table[node.getDepth()][i] = "-" + apd;
                }
            }
            map.put(node.getKey(), currentPos);
        }
        return table;
    }

    /**
     * 获取补全的符号
     * @param length 最长key长度
     * @return 补全的符号
     */
    private static String getApd(int length) {
        StringBuilder apd = new StringBuilder();
        for (int i = 0; i < length - 1; i++) {
            apd.append("-");
        }
        return apd.toString();
    }

    /**
     * 获取最长的key长度
     * @param tree 树
     * @param <K> K
     * @param <V> V
     * @return 最长的key长度
     */
    private static <K extends Comparable<K>, V> int getMaxLength(AbstractBSTree<K, V> tree) {
        AbstractBinaryTreeNode<K, V> max = tree.findMax();
        return max.getKey().toString().length();
    }

    /**
     * 获取空串
     * @param length 最长key长度
     * @return 空串
     */
    private static String getEmpty(int length) {
        StringBuilder empty = new StringBuilder();
        for (int i = 0; i < length; i++) {
            empty.append(" ");
        }
        return empty.toString();
    }

}
