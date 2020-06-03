package impl.dsa.oldversion;



/**
 * @description: 红黑树
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public class RedBlackTree<K extends Comparable<K>,V> extends BSTree<K,V> {


    /**
     * 插入节点
     *
     * @param key   键
     * @param value 值
     * @return 节点存放词条
     */
    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K,V> e = new EntryDefault<>(key,value);
        if(isEmpty()){
            RedBlackTreeNode<K,V> root = new RedBlackTreeNode<>(e,null,null,null,false,RBColor.RB_BLACK);

        }
        return super.insert(key, value);
    }

    /**
     * 删除节点
     * @param key
     * @return
     */
    @Override
    public Entry<K, V> remove(K key) {
        return super.remove(key);
    }
}
