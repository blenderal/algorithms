package impl.dsa;

/**
 * @description: B树接口
 * @author: zww
 * @date: 2020/4/17
 * @version: V1.0
 */
public interface B_Tree<K,V> extends Tree<Vector<Entry<K,V>>>{
    /**
     * 获取B树阶数
     * @return B树阶数
     */
    int getOrder();

    /**
     * 搜索关键码对应的词条
     * @param key 关键码
     * @return 关键码对应的词条
     */
    Entry<K,V> search(K key);

    /**
     * 插入词条
     * @param key 关键码
     * @param value 值
     * @return 插入的词条
     */
    Entry<K,V> insert(K key,V value);

    /**
     * 删除关键码对应的词条
     * @param key 关键码
     * @return 删除的词条
     */
    Entry<K,V> remove(K key);

}
