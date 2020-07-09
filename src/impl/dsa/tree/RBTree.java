package impl.dsa.tree;

import impl.dsa.Entry;
import impl.dsa.EntryDefault;

/**
 * @description: 红黑树
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public class RBTree<K extends Comparable<K>, V> extends BSTree<K, V> {

    /**
     * 获取根结点
     *
     * @return 根结点
     */
    @Override
    public RBTreeNode<K, V> getRoot() {
        return (RBTreeNode<K, V>) super.getRoot();
    }

    /**
     * 插入关键码
     *
     * @param key   关键码
     * @param value 值
     * @return 插入关键码对应的节点
     */
    @Override
    public RBTreeNode<K, V> insert(K key, V value) {
        Entry<K, V> entry = new EntryDefault<>(key, value);
        RBTreeNode<K, V> v;
        if (isEmpty()) {
            root = new RBTreeNode<>(entry, null, null, null, false);
            v = getRoot();
        } else {
            boolean asLeftChild;
            RBTreeNode<K, V> p = getRoot();
            while (true) {
                p = (RBTreeNode<K, V>) binSearch(p, key);
                int compare = comparator.compare(key, p.getKey());
                // key小于目标节点
                if (compare < 0) {
                    asLeftChild = true;
                    break;
                    // key大于目标节点
                } else if (compare > 0) {
                    asLeftChild = false;
                    break;
                    // key等于目标节点
                } else {
                    // 替换旧的值
                    p.setValue(value);
                    return p;
                }
            }
            v = new RBTreeNode<>(entry, null, null, p, asLeftChild);
        }
        solveDoubleRed(v);
        return v;
    }


    /**
     * 删除关键码
     *
     * @param key 关键码
     * @return 是否删除成功
     */
    @Override
    public Boolean remove(K key) {
        if (!super.remove(key)) {
            return false;
        }
        // 被删除的是根结点
        if (removedP == null) {
            getRoot().setRbColor(RBColor.RB_BLACK);
            getRoot().updateBlackHeight();
            return true;
        }
        if (((RBTreeNode<K, V>) removedP).isBalanced()) {
            return true;
        }
        RBTreeNode<K, V> replacedNode = (RBTreeNode<K, V>) r;
        // 如果替代节点为红节点 则被删除的节点必为黑节点
        if (replacedNode != null && !replacedNode.isBlack()) {
            // 将替代节点变为红色
            replacedNode.setRbColor(RBColor.RB_BLACK);
            replacedNode.updateBlackHeight();
            return true;
        }
        // 至此被删除的节点与替代节点r都为黑
        solveDoubleBlack(replacedNode);
        return true;
    }

    /**
     * 关键码查找
     *
     * @param key 关键码
     * @return 关键码对应的节点
     */
    @Override
    public TreeNode<K, V> find(K key) {
        return super.find(key);
    }

    /**
     * 解决双红缺陷
     *
     * @param v 节点v
     */
    private void solveDoubleRed(RBTreeNode<K, V> v) {
        if (v.isRoot()) {
            v.setRbColor(RBColor.RB_BLACK);
            v.setBlackHeight(v.getBlackHeight() + 1);
            return;
        }
        RBTreeNode<K, V> p = v.getParent();
        if (p.isBlack()) {
            return;
        }
        RBTreeNode<K, V> g = p.getParent();
        RBTreeNode<K, V> u = p.isLeftChild() ? g.getRightChild() : g.getLeftChild();
        // RR-1 u为黑节点
        if (u == null || u.isBlack()) {
            g.setRbColor(RBColor.RB_RED);
            g.updateBlackHeight();
            // p跟u同侧
            if (p.isLeftChild().equals(v.isLeftChild())) {
                p.setRbColor(RBColor.RB_BLACK);
            } else {
                v.setRbColor(RBColor.RB_BLACK);
            }
            // 3+4重构
            RBTreeNode<K, V> r = (RBTreeNode<K, V>) rotate(v);
            if (r.isRoot()) {
                root = r;
            }
            // RR-2 u为红节点
        } else {
            u.setRbColor(RBColor.RB_BLACK);
            u.updateBlackHeight();
            p.setRbColor(RBColor.RB_BLACK);
            p.updateBlackHeight();
            g.setRbColor(RBColor.RB_RED);
            solveDoubleRed(g);
        }
    }

    /**
     * 解决双黑缺陷
     *
     * @param r 节点r
     */
    private void solveDoubleBlack(RBTreeNode<K, V> r) {
        RBTreeNode<K, V> p = r == null ? (RBTreeNode<K, V>) removedP : r.getParent();
        if (p == null) {
            return;
        }
        RBTreeNode<K, V> s = r == p.getRightChild() ? p.getLeftChild() : p.getRightChild();
        if (s.isBlack()) {
            RBTreeNode<K, V> t = null;
            if (s.hasRightChild() && !s.getRightChild().isBlack()) {
                t = s.getRightChild();
            }
            if (s.hasLeftChild() && !s.getLeftChild().isBlack()) {
                t = s.getLeftChild();
            }
            // s有红孩子 BB-1
            if (t != null) {
                // s节点t节点同侧
                if (t.isLeftChild().equals(s.isLeftChild())) {
                    s.setRbColor(p.getRbColor());
                    t.setRbColor(RBColor.RB_BLACK);
                } else {
                    t.setRbColor(p.getRbColor());
                }
                t.updateBlackHeight();
                p.setRbColor(RBColor.RB_BLACK);
                RBTreeNode<K,V> b = (RBTreeNode<K,V>)rotate(t);
                if(b.isRoot()){
                    root = b;
                }
                // s无红孩子
            } else {
                s.setRbColor(RBColor.RB_RED);
                s.updateBlackHeight();
                // s无红孩子节点 且p为红节点 BB-2R
                if (!p.isBlack()) {
                    p.setRbColor(RBColor.RB_BLACK);
                    p.updateBlackHeight();
                    // s无红孩子节点 且p为黑节点 BB-2B
                } else {
                    p.updateBlackHeight();
                    solveDoubleBlack(p);
                }
            }
            // s为红节点 BB-3
        } else {
            s.setRbColor(RBColor.RB_BLACK);
            p.setRbColor(RBColor.RB_RED);
            RBTreeNode<K, V> t = s.isLeftChild() ? s.getLeftChild() : s.getRightChild();
            RBTreeNode<K,V> b = (RBTreeNode<K,V>)rotate(t);
            if(b.isRoot()){
                root = b;
            }
            removedP = p;
            solveDoubleBlack(r);
        }
    }

}
