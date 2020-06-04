package impl.dsa.tree;

/**
 * @description: 二叉搜索树节点
 * @author: zww
 * @date: 2020/6/3
 * @version: V1.0
 */
public class BSTreeNode<K extends Comparable<K>> extends AbstractBinaryTreeNode<K>{

    public BSTreeNode(K key, AbstractBinaryTreeNode<K> lChild, AbstractBinaryTreeNode<K> rChild, AbstractBinaryTreeNode<K> parent, boolean asLChild) {
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

        this.key = key;
        if (lChild != null) {
            insertAsLeftChild(lChild);
        }
        if (rChild != null) {
            insertAsRightChild(rChild);
        }
    }
}
