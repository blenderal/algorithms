package impl.dsa.oldversion;





/**
 * @description: 平衡二叉搜索树AVL树
 * 基于二叉搜索树扩充
 * @author: zww
 * @date: 2020/4/3
 * @version: V1.0
 */
public class AVLTree<K extends Comparable<K>, V> extends BSTree<K, V> implements Dictionary<K, V> {
    public AVLTree() {
        super();
    }

    public AVLTree(BinTreePosition<Entry<K, V>> r) {
        super(r);
    }

    public AVLTree(BinTreePosition<Entry<K, V>> r, Comparator<K> comparator) {
        super(r, comparator);
    }

    /**
     * 插入节点
     *
     * @param key   键
     * @param value 值
     * @return 节点存放词条
     */
    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> e = super.insert(key, value);
        // 从插入节点的父亲节点开始重新平衡
        BinTreePosition<Entry<K, V>> z = lastV.getParent();
        if (z != null) {
            while (true) {
                // 若z节点失去平衡，则通过旋转使之重新平衡
                if (!isBalanced(z)) {
                    z = rotate(z);
                    // 子树重平衡后的根结点没有父亲节点
                    if(!z.hasParent()){
                        // 则子树根结点为整个AVL树的根结点
                        root = z;
                    }
                    break;
                }
                if (!z.hasParent()) {
                    root = z;
                    break;
                }
                z = z.getParent();
            }
        }
        return e;
    }


    @Override
    public Entry<K, V> remove(K key) {
        Entry<K, V> e = super.remove(key);
        // 从删除节点的父亲开始重新平衡化
        BinTreePosition<Entry<K, V>> z = lastV;
        if (e != null) {
            while (true){
                if(!isBalanced(z)){
                    z = rotate(z);
                }
                if(!z.hasParent()){
                    root = z;
                    break;
                }
                z = z.getParent();
            }
        }
        return e;
    }


    /*------------------------------------------- private method -------------------------------------------**/

    /**
     * 通过旋转，使节点z的平衡因子的绝对值不超过1（支持AVL树）
     *
     * @param z
     * @return 返回新的子树根
     */
    private BinTreePosition<Entry<K, V>> rotate(BinTreePosition<Entry<K, V>> z) {
        BinTreePosition<Entry<K, V>> y = tallerChild(z);
        BinTreePosition<Entry<K, V>> x = tallerChild(y);
        boolean cType = z.isLeftChild();
        BinTreePosition<Entry<K, V>> p = z.getParent();
        //自左向右，三个节点
        BinTreePosition<Entry<K, V>> a, b, c;
        //自左向右，四棵子树
        BinTreePosition<Entry<K, V>> t0, t1, t2, t3;
        // 分四种情况
        if (y.isLeftChild()) {
            c = z;
            t3 = z.getRightChild();
            if (x.isLeftChild()) {
                b = y;
                a = x;
                t0 = x.getLeftChild();
                t1 = x.getRightChild();
                t2 = y.getRightChild();
            } else {
                a = y;
                b = x;
                t0 = y.getLeftChild();
                t1 = x.getLeftChild();
                t2 = x.getRightChild();
            }
        } else {
            a = z;
            t0 = z.getLeftChild();
            if (x.isRightChild()) {
                b = y;
                c = x;
                t1 = y.getLeftChild();
                t2 = x.getLeftChild();
                t3 = x.getRightChild();
            } else {
                b = x;
                c = y;
                t1 = x.getLeftChild();
                t2 = x.getRightChild();
                t3 = y.getRightChild();
            }
        }
        // 3+4重构
        connect34(a, b, c, t0, t1, t2, t3);
        if (p != null) {
            if (cType) {
                p.insertAsLeftChild(b);
            } else {
                p.insertAsRightChild(b);
            }
        }
        return b;
    }

    /**
     * 3+4重构
     *
     * @param a
     * @param b
     * @param c
     * @param t0
     * @param t1
     * @param t2
     * @param t3
     */
    private void connect34(BinTreePosition<Entry<K, V>> a, BinTreePosition<Entry<K, V>> b, BinTreePosition<Entry<K, V>> c, BinTreePosition<Entry<K, V>> t0, BinTreePosition<Entry<K, V>> t1, BinTreePosition<Entry<K, V>> t2, BinTreePosition<Entry<K, V>> t3) {
        a.secede();
        b.secede();
        c.secede();
        if (t0 != null) {
            t0.secede();
        }
        if (t1 != null) {
            t1.secede();
        }
        if (t2 != null) {
            t2.secede();
        }
        if (t3 != null) {
            t3.secede();
        }
        a.insertAsLeftChild(t0);
        a.insertAsRightChild(t1);
        b.insertAsLeftChild(a);
        b.insertAsRightChild(c);
        c.insertAsLeftChild(t2);
        c.insertAsRightChild(t3);
    }

    /**
     * 找到节点v更高的子树
     *
     * @param v
     * @return
     */
    private BinTreePosition<Entry<K, V>> tallerChild(BinTreePosition<Entry<K, V>> v) {
        int lh = v.hasLeftChild() ? v.getLeftChild().getHeight() : -1;
        int rh = v.hasRightChild() ? v.getRightChild().getHeight() : -1;
        if (lh > rh) {
            return v.getLeftChild();
        }
        if (lh < rh) {
            return v.getRightChild();
        }
        if (v.isLeftChild()) {
            return v.getLeftChild();
        } else {
            return v.getRightChild();
        }
    }

    /**
     * 判断以v为根节点的子树是否平衡
     *
     * @param v
     * @return
     */
    private boolean isBalanced(BinTreePosition<Entry<K, V>> v) {
        if (null == v) {
            return true;
        }
        int lh = v.hasLeftChild() ? v.getLeftChild().getHeight() : -1;
        int rh = v.hasRightChild() ? v.getRightChild().getHeight() : -1;
        int deltaH = lh - rh;
        return deltaH > -2 && deltaH < 2;
    }
}
