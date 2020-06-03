package impl.dsa.tree;

/**
 * @description: 二叉搜索树节点
 * @author: zww
 * @date: 2020/6/3
 * @version: V1.0
 */
public class BSTreeNode<K extends Comparable<K>> extends AbstractBinaryTreeNode<K>{

    public BSTreeNode(K key, AbstractBinaryTreeNode<K> lChild, AbstractBinaryTreeNode<K> rChild, AbstractBinaryTreeNode<K> parent, boolean asLChild) {
        super(key, lChild, rChild, parent, asLChild);
    }
}
