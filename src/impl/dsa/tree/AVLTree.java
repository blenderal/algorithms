package impl.dsa.tree;

/**
 * @description: AVL树
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public class AVLTree<K extends Comparable<K>> extends BSTree<K> {
    /**
     * 插入节点
     *
     * @param key 键
     * @return 插入关键码对应的节点
     */
    @Override
    public TreeNode<K> insert(K key) {
        AbstractBinaryTreeNode<K> target = binSearch(root,key);
        AbstractBinaryTreeNode<K> e = (AbstractBinaryTreeNode<K>) super.insert(key);
        if(target.getElement().equals(key) ){
            return e;
        }
        // 从插入节点的父亲节点开始重新平衡
        AbstractBinaryTreeNode<K> z = e.getParent();
        if (z != null) {
            while (true) {
                // 若z节点失去平衡，则通过旋转使之重新平衡
                if (!isBalanced(z)) {
                    z = rotate(z);
                    // 子树重平衡后的根结点没有父亲节点
                    if (!z.hasParent()) {
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

    /**
     * 删除节点
     *
     * @param key 关键码
     * @return 删除掉的节点的父节点
     */
    @Override
    public TreeNode<K> remove(K key) {
        AbstractBinaryTreeNode<K> e = (AbstractBinaryTreeNode<K>) super.remove(key);
        // 从删除节点的父亲开始重新平衡化
        AbstractBinaryTreeNode<K> z = e;
        if (e != null) {
            while (true) {
                if (!isBalanced(z)) {
                    z = rotate(z);
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

    /**
     * 判断以v为根节点的子树是否平衡
     *
     * @param v
     * @return
     */
    private boolean isBalanced(AbstractBinaryTreeNode<K> v) {
        if (null == v) {
            return true;
        }
        int lh = v.hasLeftChild() ? v.getLeftChild().getHeight() : -1;
        int rh = v.hasRightChild() ? v.getRightChild().getHeight() : -1;
        int deltaH = lh - rh;
        return deltaH > -2 && deltaH < 2;
    }

    /**
     * 通过旋转，使节点z的平衡因子的绝对值不超过1（支持AVL树）
     *
     * @param z
     * @return 返回新的子树根
     */
    private AbstractBinaryTreeNode<K> rotate(AbstractBinaryTreeNode<K> z) {
        AbstractBinaryTreeNode<K> y = tallerChild(z);
        AbstractBinaryTreeNode<K> x = tallerChild(y);
        boolean cType = z.isLeftChild();
        AbstractBinaryTreeNode<K> p = z.getParent();
        //自左向右，三个节点
        AbstractBinaryTreeNode<K> a, b, c;
        //自左向右，四棵子树
        AbstractBinaryTreeNode<K> t0, t1, t2, t3;
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
     * @param a  a节点
     * @param b  b节点
     * @param c  c节点
     * @param t0 t0子树根结点
     * @param t1 t1子树根结点
     * @param t2 t2子树根结点
     * @param t3 t3子树根结点
     */
    private void connect34(AbstractBinaryTreeNode<K> a, AbstractBinaryTreeNode<K> b, AbstractBinaryTreeNode<K> c,
                           AbstractBinaryTreeNode<K> t0, AbstractBinaryTreeNode<K> t1, AbstractBinaryTreeNode<K> t2, AbstractBinaryTreeNode<K> t3) {
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
     * @param v 节点v
     * @return 更高的子树
     */
    private AbstractBinaryTreeNode<K> tallerChild(AbstractBinaryTreeNode<K> v) {
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

}
