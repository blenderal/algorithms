package impl.dsa.tree;

import impl.dsa.Entry;

/**
 * @description: 红黑树节点
 * @author: zww
 * @date: 2020/6/4
 * @version: V1.0
 */
public class RBTreeNode<K extends Comparable<K>, V> extends BSTreeNode<K, V> {
    /**
     * 节点颜色
     */
    private RBColor rbColor;

    /**
     * 黑高度
     */
    protected int blackHeight;

    public RBTreeNode(Entry<K, V> entry, AbstractBinaryTreeNode<K, V> lChild, AbstractBinaryTreeNode<K, V> rChild, AbstractBinaryTreeNode<K, V> parent, boolean asLChild) {
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
                hasLeftChild() ? (getLeftChild()).getBlackHeight() : 0,
                hasRightChild() ? (getRightChild()).getBlackHeight() : 0);
        this.blackHeight = isBlack() ? bh + 1 : bh;
    }


    @Override
    public void updateHeight() {
        height = 0;
        if (hasLeftChild()) {
            height = Math.max((1 + getLeftChild().getHeight()), height);
        }
        if (hasRightChild()) {
            height = Math.max((1 + getRightChild().getHeight()), height);
        }
        updateBlackHeight();
        if (hasParent()) {
            parent.updateHeight();
        }
    }

    /**
     * 黑高度是否平衡
     *
     * @return 黑高度是否平衡
     */
    public boolean isBalanced() {
        if (hasLeftChild() && (getLeftChild()).getBlackHeight() + (isBlack() ? 1 : 0) != blackHeight) {
            return false;
        }
        if (hasRightChild() && (getRightChild()).getBlackHeight() + (isBlack() ? 1 : 0) != blackHeight) {
            return false;
        }
        return (isBlack() ? 1 : 0) == blackHeight;
    }

    @Override
    public RBTreeNode<K, V> getParent() {
        return (RBTreeNode<K, V>) super.getParent();
    }

    @Override
    public RBTreeNode<K, V> getLeftChild() {
        return (RBTreeNode<K, V>) super.getLeftChild();
    }

    @Override
    public RBTreeNode<K, V> getRightChild() {
        return (RBTreeNode<K, V>) super.getRightChild();
    }

    /**
     * 获取该节点前继节点
     *
     * @return 该节点前继节点
     */
    @Override
    public RBTreeNode<K, V> getPrev() {
        return (RBTreeNode<K, V>) super.getPrev();
    }

    /**
     * 中序遍历意义下当前节点的直接后继
     *
     * @return 直接后继节点位置
     */
    @Override
    public RBTreeNode<K, V> getSucc() {
        return (RBTreeNode<K, V>) super.getSucc();
    }
}
