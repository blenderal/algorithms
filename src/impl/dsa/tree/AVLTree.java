package impl.dsa.tree;


/**
 * @description: AVL树
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public class AVLTree<K extends Comparable<K>, V> extends BSTree<K, V> {
    /**
     * 插入节点
     *
     * @param key 键
     * @return 插入关键码对应的节点
     */
    @Override
    public BSTreeNode<K, V> insert(K key, V value) {
        TreeNode<K, V> target = null;
        if (!isEmpty()) {
            target = find(key);
        }
        BSTreeNode<K, V> v = super.insert(key, value);
        if (target != null) {
            return v;
        }
        // 从插入节点的父亲节点开始重新平衡
        AbstractBinaryTreeNode<K, V> z = v.getParent();
        if (z != null) {
            while (true) {
                // 若z节点失去平衡，则通过旋转使之重新平衡
                if (!isBalanced(z)) {
                    z = rotate(tallerChild(tallerChild(z)));
                    // 子树重平衡后的根结点没有父亲节点
                    if (!z.hasParent()) {
                        // 则子树根结点为整个AVL树的根结点
                        root = z;
                    }
                    break;
                }
                if (!z.hasParent()) {
                    root = z;
                    break;
                }
                z = z.getParent();
            }
        }
        return v;
    }

    /**
     * 删除节点
     *
     * @param key 关键码
     * @return 删除掉的节点的父节点
     */
    @Override
    public Boolean remove(K key) {
        if (!super.remove(key)) {
            return false;
        }
        // 从删除节点的父亲开始重新平衡化
        AbstractBinaryTreeNode<K, V> z = removedP;
        while (true) {
            if (!isBalanced(z)) {
                z = rotate(tallerChild(tallerChild(z)));
            }
            if (!z.hasParent()) {
                root = z;
                break;
            }
            z = z.getParent();
        }
        return true;
    }

    /**
     * 判断以v为根节点的子树是否平衡
     *
     * @param v v
     * @return 以v为根节点的子树是否平衡
     */
    private boolean isBalanced(AbstractBinaryTreeNode<K, V> v) {
        if (null == v) {
            return true;
        }
        int lh = v.hasLeftChild() ? v.getLeftChild().getHeight() : -1;
        int rh = v.hasRightChild() ? v.getRightChild().getHeight() : -1;
        int deltaH = lh - rh;
        return deltaH > -2 && deltaH < 2;
    }

    /**
     * 找到节点v更高的子树
     *
     * @param v 节点v
     * @return 更高的子树
     */
    private AbstractBinaryTreeNode<K, V> tallerChild(AbstractBinaryTreeNode<K, V> v) {
        int lh = v.hasLeftChild() ? v.getLeftChild().getHeight() : -1;
        int rh = v.hasRightChild() ? v.getRightChild().getHeight() : -1;
        if (lh > rh) {
            return v.getLeftChild();
        }
        if (lh < rh) {
            return v.getRightChild();
        }
        if (v.isLeftChild()) {
            return v.getLeftChild();
        } else {
            return v.getRightChild();
        }
    }

}
