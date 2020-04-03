package impl.dsa;

/**
 * @description: 基于链表实现的二叉搜索树节点
 * @author: zww
 * @date: 2020-04-01
 * @version: V1.0
 */
public class  BSTreeNode<K,V> extends BinTreeNode<Entry<K,V>> implements BinTreePosition<Entry<K,V>>,Entry<K,V>{


    public BSTreeNode() {
    }

    public BSTreeNode(Entry<K, V> data, BinTreePosition<Entry<K, V>> lChild, BinTreePosition<Entry<K, V>> rChild, BinTreePosition<Entry<K, V>> parent, boolean asLChild) {
        super(data, lChild, rChild, parent, asLChild);
    }

    @Override
    public K getKey() {
        return getElement().getKey();
    }

    @Override
    public K setKey(K k) {
        return getElement().setKey(k);
    }

    @Override
    public V getValue() {
        return getElement().getValue();
    }

    @Override
    public V setValue(V v) {
        return getElement().setValue(v);
    }
}
