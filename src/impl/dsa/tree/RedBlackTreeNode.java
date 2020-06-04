package impl.dsa.tree;

import impl.dsa.RBColor;

/**
 * @description: 红黑树节点
 * @author: zww
 * @date: 2020/6/4
 * @version: V1.0
 */
public class RedBlackTreeNode<K extends Comparable<K>> extends BSTreeNode<K>{
    /**
     * 节点颜色
     */
    private RBColor rbColor;

    public RedBlackTreeNode(K key) {
        super(key, null, null, null, false);
        this.rbColor = RBColor.RB_RED;
    }

    public RedBlackTreeNode(K key, AbstractBinaryTreeNode<K> lChild, AbstractBinaryTreeNode<K> rChild, AbstractBinaryTreeNode<K> parent, boolean asLChild) {
        super(key, lChild, rChild, parent, asLChild);
        this.rbColor = RBColor.RB_RED;
    }
}
