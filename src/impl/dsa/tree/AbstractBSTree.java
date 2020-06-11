package impl.dsa.tree;

import impl.dsa.*;

/**
 * @description: 二叉搜索树抽象类
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public abstract class AbstractBSTree<K extends Comparable<K>,V> extends AbstractBinaryTree<K,V> {
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
    public TreeNode<K,V> find(K key) {
        if (isEmpty()) {
            return null;
        }
        AbstractBinaryTreeNode<K,V> node = binSearch(root, key);
        return comparator.compare(key, node.getKey()) == 0 ? node : null;
    }

    /**
     * 二分查找
     *
     * @param root 根结点
     * @param key  关键码
     * @return 关键码对应的节点
     */
    protected AbstractBinaryTreeNode<K,V> binSearch(AbstractBinaryTreeNode<K,V> root, K key) {
        AbstractBinaryTreeNode<K,V> u = root;
        while (true) {
            int compare = comparator.compare(key, u.getKey());
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
     * 查找树中最大节点
     * @return 树中最大节点
     */
    public AbstractBinaryTreeNode<K,V> findMax(){
        if(isEmpty()){
            return null;
        }
        AbstractBinaryTreeNode<K,V> max = root;
        while (max.hasRightChild()){
            max = max.getRightChild();
        }
        return max;
    }

}
