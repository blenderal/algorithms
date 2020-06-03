package impl.dsa.tree;

/**
 * @description: B树相关接口
 * @author: zww
 * @date: 2020/4/27
 * @version: V1.0
 */
public abstract class AbstractBTree<K extends Comparable<K>> {
    /**
     * B树阶数
     */
    protected int order;
    /**
     * B树索引规模
     */
    protected int size;

    protected AbstractBTreeNode<K> root;

    public AbstractBTree(int order) {
        this.order = order;
        this.size = 0;
        root = new BTreeNode<>(order);
    }

    protected boolean isEmpty(){
        return root.getKeys().isEmpty();
    }

    /**
     * 查找索引
     * @param key 索引
     * @return 索引
     */
    abstract K search(K key);

    /**
     * 插入索引
     * @param key 索引
     * @return 插入的索引
     */
    abstract K insert(K key);

    /**
     * 删除关键码对应的词条
     * @param key 关键码
     * @return 删除的词条
     */
    abstract K remove(K key);

    /**
     * 查找索引对应的节点
     * @param key 索引
     * @return 索引对应的节点
     */
    abstract AbstractBTreeNode<K> find(K key);
}
