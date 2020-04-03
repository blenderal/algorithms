package impl.dsa;

/**
 * @description: 默认条目
 * @author: zww
 * @date: 2020-04-01
 * @version: V1.0
 */
public class EntryDefault<K, V> implements Entry<K, V> {
    private K key;
    private V value;

    public EntryDefault() {
    }

    public EntryDefault(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public K setKey(K k) {
        K oldKey = this.key;
        this.key = k;
        return oldKey;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V v) {
        V oldValue = this.value;
        this.value = v;
        return oldValue;
    }
}
