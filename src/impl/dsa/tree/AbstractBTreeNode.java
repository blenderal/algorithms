package impl.dsa.tree;

import impl.dsa.ArrayVector;
import impl.dsa.Comparator;
import impl.dsa.ComparatorDefault;
import impl.dsa.Vector;

/**
 * @description: 抽象B树节点类
 * @author: zww
 * @date: 2020/4/24
 * @version: V1.0
 */
public abstract class AbstractBTreeNode<K extends Comparable<K>> {
    /**
     * 阶数
     */
    protected final int order;

    /**
     * 索引域
     */
    protected Vector<K> keys;

    /**
     * 孩子节点
     */
    protected Vector<AbstractBTreeNode<K>> children;

    /**
     * 父亲节点
     */
    protected AbstractBTreeNode<K> parent;

    /**
     * 比较器
     */
    private final Comparator<K> comparator = new ComparatorDefault<>();


    public AbstractBTreeNode(int order) {
        if (order < 3) {
            throw new IllegalArgumentException("order must > 2");
        }
        this.order = order;
        this.keys = new ArrayVector<>();
        this.children = new ArrayVector<>();
        children.insertAtRank(0, null);
    }

    /**
     * 节点是否已满
     *
     * @return 节点是否上溢
     */
    abstract boolean isOverflow();

    /**
     * 节点是否下溢
     *
     * @return 节点是否下溢
     */
    abstract boolean isUnderflow();


    /**
     * 节点分裂
     * @return 父亲节点
     */
    abstract AbstractBTreeNode<K> split();

    /**
     * 节点合并
     * @return 父亲节点
     */
    abstract AbstractBTreeNode<K> merge();


    /**
     * 获取索引
     *
     * @return 索引
     */
    protected Vector<K> getKeys() {
        return keys;
    }

    /**
     * 获取索引规模
     * @return 索引规模
     */
    protected int getKeySize(){
        return getKeys().getSize();
    }

    /**
     * 节点索引值是否为空
     * @return 节点索引值是否为空
     */
    protected boolean isKeyEmpty(){
        return getKeySize() == 0;
    }

    /**
     * 获取孩子指针向量
     * @return 孩子指针向量
     */
    protected Vector<AbstractBTreeNode<K>> getChildren(){
        return children;
    }

    /**
     * 获取孩子节点规模
     * @return 孩子节点规模
     */
    protected int getChildrenSize(){
        return getChildren().getSize();
    }

    /**
     * 获取对应秩的孩子节点
     *
     * @param rank 秩
     * @return 秩对应的孩子节点
     */
    protected AbstractBTreeNode<K> getChild(int rank) {
        return getChildren().getRank(rank);
    }

    /**
     * 设置父亲节点
     * @param p 父亲节点
     */
    protected void setParent(AbstractBTreeNode<K> p) {
        this.parent = p;
    }

    /**
     * 获取父亲节点
     * @return 父亲节点
     */
    protected AbstractBTreeNode<K> getParent(){
        return parent;
    }

    /**
     * 是否是根结点
     * @return 是否是根结点
     */
    protected boolean isRoot(){
        return getParent() == null;
    }

    /**
     * 是否是叶节点
     */
    protected boolean isLeaf() {
        return children.getRank(0) == null;
    }

    /**
     * 返回对应秩的词条
     *
     * @param rank 秩
     * @return 对应词条
     */
    protected K getKey(int rank) {
        if (rank > -1 && rank < keys.getSize()) {
            return this.keys.getRank(rank);
        }
        return null;
    }

    /**
     * 插入索引值
     *
     * @param rank 插入的位置
     * @param key 索引
     * @return 插入的索引值
     */
    protected K insertKeyAt(int rank, K key) {
        keys.insertAtRank(rank , key);
        return key;
    }

    /**
     * 删除索引值
     *
     * @param r 索引
     * @return 删除的索引值
     */
    protected K removeKeyAt(int r) {
        return keys.removeAtRank(r);
    }


    /**
     * 插入孩子节点
     * @param rank 待插入的位置
     * @param child 孩子节点
     * @return 插入的孩子节点
     */
    protected AbstractBTreeNode<K> insertChildAt(int rank, AbstractBTreeNode<K> child){
        return children.insertAtRank(rank,child);
    }

    /**
     * 删除孩子节点
     * @param rank 待删除孩子的秩
     * @return 删除的孩子节点
     */
    protected AbstractBTreeNode<K> removeChildAt(int rank) {
        return children.removeAtRank(rank);
    }

    /**
     * 替换索引值
     * @param rank 待替换索引的位置
     * @param key 索引
     * @return 旧的索引值
     */
    protected K replaceKeyAt(int rank, K key){
        return keys.replaceAtRank(rank,key);
    }

    /**
     * 替换孩子节点
     * @param rank 待替换孩子节点的位置
     * @param child 替换后的新孩子节点
     * @return 旧孩子节点
     */
    protected AbstractBTreeNode<K> replaceChildAt(int rank, AbstractBTreeNode<K> child){
        return children.replaceAtRank(rank,child);
    }


    /**
     * 在当前节点中，找到不大于key的最大关键码
     *
     * @param key 查找关键码
     * @return 不大于key的最大关键码
     */
    protected int search(K key) {
        int n = keys.getSize();
        for (int i = 0; i < n; i++) {
            int c = comparator.compare(key, keys.getRank(i));
            if (c <= 0) {
                if (c < 0) {
                    return i - 1;
                } else {
                    return i;
                }
            }
        }
        return n - 1;
    }


}
