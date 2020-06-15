package impl.dsa.tree;

import impl.dsa.Entry;

/**
 * @description: 二叉搜索树节点
 * @author: zww
 * @date: 2020/6/3
 * @version: V1.0
 */
public class BSTreeNode<K extends Comparable<K>,V> extends AbstractBinaryTreeNode<K,V>{

    public BSTreeNode(Entry<K,V> entry, AbstractBinaryTreeNode<K,V> lChild, AbstractBinaryTreeNode<K,V> rChild, AbstractBinaryTreeNode<K,V> parent, boolean asLChild) {
        super(entry, lChild, rChild, parent, asLChild);
        size = 1;
        height = 0;
        depth = 0;
        this.parent = null;
        this.lChild = null;
        this.rChild = null;
        if (parent != null) {
            if (asLChild) {
                parent.insertAsLeftChild(this);
            } else {
                parent.insertAsRightChild(this);
            }
        }
        this.entry = entry;
        if (lChild != null) {
            insertAsLeftChild(lChild);
        }
        if (rChild != null) {
            insertAsRightChild(rChild);
        }
    }

    @Override
    public BSTreeNode<K, V> getParent() {
        return (BSTreeNode<K, V>)super.getParent();
    }

    @Override
    public BSTreeNode<K, V> getLeftChild() {
        return (BSTreeNode<K, V>)super.getLeftChild();
    }

    @Override
    public BSTreeNode<K, V> getRightChild() {
        return (BSTreeNode<K, V>)super.getRightChild();
    }

    /**
     * 获取该节点前继节点
     *
     * @return 该节点前继节点
     */
    @Override
    public BSTreeNode<K, V> getPrev() {
        return (BSTreeNode<K, V>)super.getPrev();
    }

    /**
     * 中序遍历意义下当前节点的直接后继
     *
     * @return 直接后继节点位置
     */
    @Override
    public BSTreeNode<K, V> getSucc() {
        return (BSTreeNode<K, V>)super.getSucc();
    }
}
