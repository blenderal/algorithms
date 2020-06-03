package impl.dsa.oldversion;


/**
 * @description:
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public class RedBlackTreeNode<K extends Comparable<K>,V> extends BSTreeNode<K,V> {
    /**
     *  节点颜色
     */
    private RBColor rbColor;

    public boolean isBlack(){
        return rbColor.equals(RBColor.RB_BLACK);
    }

    public RedBlackTreeNode(Entry<K, V> data, BinTreePosition<Entry<K, V>> lChild, BinTreePosition<Entry<K, V>> rChild, BinTreePosition<Entry<K, V>> parent, boolean asLChild, RBColor rbColor) {
        super(data, lChild, rChild, parent, asLChild);
        this.rbColor = rbColor;
    }

    /**
     * 重写高度更新方法 红黑树高度定义为黑高度
     */
    @Override
    public void updateHeight() {
        height = isBlack() ? 1 : 0;
        if (hasLeftChild()) {
            height = Math.max((height + lChild.getHeight()), height);
        }
        if (hasRightChild()) {
            height = Math.max((height + rChild.getHeight()), height);
        }
        if (hasParent()) {
            parent.updateHeight();
        }
    }
}
