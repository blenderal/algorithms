package impl.dsa;

/**
 * @description: B树节点
 * @author: zww
 * @date: 2020/4/13
 * @version: V1.0
 */
public class BTreeNode<K extends Comparable<K>, V> implements BTreePosition<Vector<Entry<K, V>>> {


    /**
     * 父亲节点
     */
    private BTreePosition<Vector<Entry<K, V>>> parent;
    /**
     * 数据域
     */
    private Vector<Entry<K, V>> data;
    /**
     * 孩子节点
     */
    private Vector<BTreePosition<Vector<Entry<K, V>>>> child;

    public BTreeNode() {
        parent = null;
        data = new ArrayVector<>();
        child = new ArrayVector<>();
        child.insertAtRank(0, null);
    }

    public BTreeNode(Entry<K, V> r, BTreePosition<Vector<Entry<K, V>>> lc, BTreePosition<Vector<Entry<K, V>>> rc) {
        this.parent = null;
        this.data = new ArrayVector<>();
        data.insertAtRank(0, r);
        this.child = new ArrayVector<>();
        if (lc != null) {
            child.insertAtRank(0, lc);
            lc.setParent(this);
        }
        if (rc != null) {
            child.insertAtRank(1, rc);
            rc.setParent(this);
        }
    }


    @Override
    public BTreePosition<Vector<Entry<K, V>>> getParent() {
        return parent;
    }

    @Override
    public void setParent(BTreePosition<Vector<Entry<K, V>>> p) {
        this.parent = p;
    }


    @Override
    public Vector<Entry<K, V>> getElement() {
        return data;
    }

    @Override
    public Vector<Entry<K, V>> setElement(Vector<Entry<K, V>> data) {
        Vector<Entry<K, V>> bak = getElement();
        this.data = data;
        return bak;
    }

    @Override
    public Boolean hasParent() {
        return parent != null;
    }


    @Override
    public Vector<BTreePosition<Vector<Entry<K, V>>>> getChild() {
        return child;
    }
}
