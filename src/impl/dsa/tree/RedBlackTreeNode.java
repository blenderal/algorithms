package impl.dsa.tree;

import impl.dsa.Entry;
import impl.dsa.RBColor;

/**
 * @description: 红黑树节点
 * @author: zww
 * @date: 2020/6/4
 * @version: V1.0
 */
public class RedBlackTreeNode<K extends Comparable<K>, V> extends BSTreeNode<K, V> {
    /**
     * 节点颜色
     */
    private RBColor rbColor;

    /**
     * 黑高度
     */
    protected int blackHeight;

    public RedBlackTreeNode(Entry<K, V> entry, RBColor rbColor) {
        super(entry, null, null, null, false);
        this.blackHeight = -1;
        this.rbColor = RBColor.RB_RED;
    }

    public RedBlackTreeNode(Entry<K, V> entry, AbstractBinaryTreeNode<K, V> lChild, AbstractBinaryTreeNode<K, V> rChild, AbstractBinaryTreeNode<K, V> parent, boolean asLChild) {
        super(entry, lChild, rChild, parent, asLChild);
        this.blackHeight = 0;
        this.rbColor = RBColor.RB_RED;
    }

    public RBColor getRbColor() {
        return rbColor;
    }

    public void setRbColor(RBColor rbColor) {
        this.rbColor = rbColor;
    }

    public int getBlackHeight() {
        return blackHeight;
    }

    public void setBlackHeight(int blackHeight) {
        this.blackHeight = blackHeight;
    }

    /**
     * 是否是黑节点
     *
     * @return 是否是黑节点
     */
    public boolean isBlack() {
        return getRbColor().equals(RBColor.RB_BLACK);
    }

    /**
     * 更新节点黑高度
     */
    public void updateBlackHeight() {
        int bh = Math.max(
                hasLeftChild() ? ((RedBlackTreeNode<K, V>) getLeftChild()).getBlackHeight() : 0,
                hasRightChild() ? ((RedBlackTreeNode<K, V>) getRightChild()).getBlackHeight() : 0);
        this.blackHeight = isBlack() ? bh + 1 : bh;
    }


    @Override
    public void updateHeight() {
        height = 0;
        if (hasLeftChild()) {
            height = Math.max((1 + lChild.getHeight()), height);
        }
        if (hasRightChild()) {
            height = Math.max((1 + rChild.getHeight()), height);
        }
        updateBlackHeight();
        if (hasParent()) {
            parent.updateHeight();
        }
    }

}
