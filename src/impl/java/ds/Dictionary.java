package impl.java.ds;


/**
 * @description: 词典接口
 * @author: zww
 * @date: 2020-04-01
 * @version: V1.0
 */
public interface Dictionary<K,V> {

    int getSize();

    Boolean isEmpty();

    Entry<K,V> find(K key);

    Iterator<Entry<K,V>> findAll(K key);

    Entry<K,V> insert(K key,V value);

    Entry<K,V> remove(K key);

    Iterator<Entry<K,V>> entries();

}
