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
}
