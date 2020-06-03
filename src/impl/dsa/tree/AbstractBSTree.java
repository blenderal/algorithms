package impl.dsa.tree;

import impl.dsa.*;

/**
 * @description: 二叉搜索树抽象类
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public abstract class AbstractBSTree<K extends Comparable<K>> extends AbstractBinaryTree<K> {
    /**
     * 比较器
     */
    protected final Comparator<K> comparator = new ComparatorDefault<>();

    /**
     * 关键码查找
     *
     * @param key 关键码
     * @return 关键码对应的节点
     */
    @Override
    TreeNode<K> find(K key) {
        if (isEmpty()) {
            return null;
        }
        AbstractBinaryTreeNode<K> node = binSearch(root, key);
        return comparator.compare(key, node.getElement()) == 0 ? node : null;
    }

    /**
     * 查找所有关键码
     *
     * @param key 关键码
     * @return 关键码对应的节点迭代器
     */
    @Override
    Iterator<TreeNode<K>> findAll(K key) {
        List<TreeNode<K>> list = new LinkedList<>();
        findAllNodes(key, root, list);
        return list.elements();
    }


    /**
     * 二分查找
     *
     * @param root 根结点
     * @param key  关键码
     * @return 关键码对应的节点
     */
    protected AbstractBinaryTreeNode<K> binSearch(AbstractBinaryTreeNode<K> root, K key) {
        AbstractBinaryTreeNode<K> u = root;
        while (true) {
            int compare = comparator.compare(key, u.getElement());
            // key小于当前节点值
            if (compare < 0) {
                if (u.hasLeftChild()) {
                    u = u.getLeftChild();
                } else {
                    return u;
                }
                // key大于当前节点值
            } else if (compare > 0) {
                if (u.hasRightChild()) {
                    u = u.getRightChild();
                } else {
                    return u;
                }

            } else { // 查找命中
                return u;
            }
        }
    }

    /**
     * 查找所有节点
     *
     * @param key  关键码
     * @param node 根结点
     * @param list 存储节点的列表
     */
    private void findAllNodes(K key, AbstractBinaryTreeNode<K> node, List<TreeNode<K>> list) {
        // 递归基
        if (node == null) {
            return;
        }
        int compare = comparator.compare(key, node.getElement());
        if (compare <= 0) {
            findAllNodes(key, node.getLeftChild(), list);
        }
        if (compare == 0) {
            list.insertLast(node);
        }
        if (compare >= 0) {
            findAllNodes(key, node.getRightChild(), list);
        }

    }
}
