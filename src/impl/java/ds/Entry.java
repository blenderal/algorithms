package impl.java.ds;

/**
 * @description: 条目接口
 * @author: zww
 * @date: 2020-04-01
 * @version: V1.0
 */
public interface Entry<K,V> {

    K getKey();

    K setKey(K k);

    V getValue();

    V setValue(V v);
}
