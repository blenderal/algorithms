package impl.dsa.tree;

/**
 * @description: 红黑树
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public class RedBlackTree<K extends Comparable<K>> extends BSTree<K>{
    /**
     * 关键码查找
     *
     * @param key 关键码
     * @return 关键码对应的节点
     */
    @Override
    public TreeNode<K> find(K key) {
        return super.find(key);
    }

    /**
     * 插入关键码
     *
     * @param key 关键码
     * @return 插入关键码对应的节点
     */
    @Override
    public TreeNode<K> insert(K key) {
        return super.insert(key);
    }

    /**
     * 删除关键码
     *
     * @param key 关键码
     * @return 删除关键码对应的节点的父节点
     */
    @Override
    public TreeNode<K> remove(K key) {
        return super.remove(key);
    }
}
