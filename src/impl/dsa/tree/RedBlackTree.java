package impl.dsa.tree;

import impl.dsa.Entry;
import impl.dsa.EntryDefault;
import impl.dsa.RBColor;

/**
 * @description: 红黑树
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public class RedBlackTree<K extends Comparable<K>, V> extends BSTree<K, V> {
    /**
     * 插入关键码
     *
     * @param key   关键码
     * @param value 值
     * @return 插入关键码对应的节点
     */
    @Override
    public TreeNode<K, V> insert(K key, V value) {
        Entry<K, V> entry = new EntryDefault<>(key, value);
        RedBlackTreeNode<K, V> v;
        if (isEmpty()) {
            root = new RedBlackTreeNode<>(entry, null, null, null, false);
            v = (RedBlackTreeNode<K, V>) root;
        } else {
            boolean asLChild;
            AbstractBinaryTreeNode<K, V> p = root;
            while (true) {
                p = binSearch(p, key);
                int compare = comparator.compare(key, p.getKey());
                // key小于目标节点
                if (compare < 0) {
                    asLChild = true;
                    break;
                    // key大于目标节点
                } else if (compare > 0) {
                    asLChild = false;
                    break;
                    // key等于目标节点
                } else {
                    // 替换旧的值
                    p.setValue(value);
                    return p;
                }
            }
            v = new RedBlackTreeNode<>(entry, null, null, p, asLChild);
        }
        solveDoubleRed(v);
        return v;
    }


    /**
     * 删除关键码
     *
     * @param key 关键码
     * @return 删除关键码对应的节点的父节点
     */
    @Override
    public TreeNode<K, V> remove(K key) {
        return null;
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
    private void solveDoubleRed(RedBlackTreeNode<K, V> v) {
        if (v.isRoot()) {
            v.setRbColor(RBColor.RB_BLACK);
            v.setBlackHeight(v.getBlackHeight() + 1);
            return;
        }
        RedBlackTreeNode<K, V> p = (RedBlackTreeNode<K, V>) v.getParent();
        if (p.isBlack()) {
            return;
        }
        RedBlackTreeNode<K, V> g = (RedBlackTreeNode<K, V>) p.getParent();
        RedBlackTreeNode<K, V> u = (RedBlackTreeNode<K, V>) (p.isLeftChild() ? g.getRightChild() : g.getLeftChild());
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
            RedBlackTreeNode<K, V> r = (RedBlackTreeNode<K, V>) rotate(v);
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

}
