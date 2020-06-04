package impl.dsa.tree;


/**
 * @description: 二叉搜索树
 * @author: zww
 * @date: 2020/6/3
 * @version: V1.0
 */
public class BSTree<K extends Comparable<K>> extends AbstractBSTree<K> {

    /**
     * 插入关键码
     *
     * @param key 关键码
     * @return 插入关键码对应的节点
     */
    @Override
    public TreeNode<K> insert(K key) {
        TreeNode<K> node;
        if (isEmpty()) {
            root = new BSTreeNode<>(key, null, null, null, true);
            node = root;
        } else {
            boolean asLChild;
            AbstractBinaryTreeNode<K> p = root;
            while (true) {
                p = binSearch(p, key);
                int compare = comparator.compare(key, p.getElement());
                // key小于目标节点
                if (compare < 0) {
                    asLChild = true;
                    break;
                    // key大于目标节点
                } else if (compare > 0) {
                    asLChild = false;
                    break;
                    // key等于目标节点
                } else{
                    // 替换旧的值
                    p.setElement(key);
                    return p;
                }
            }
            node = new BSTreeNode<>(key, null, null, p, asLChild);
        }
        return node;
    }

    /**
     * 删除关键码
     *
     * @param key 关键码
     * @return 删除关键码对应的节点的父节点
     */
    @Override
    public TreeNode<K> remove(K key) {
        AbstractBinaryTreeNode<K> p;
        if (isEmpty()) {
            return null;
        }
        // 待删除的节点
        AbstractBinaryTreeNode<K> v = binSearch(root, key);
        // 查找不成功
        if (comparator.compare(key, v.getElement()) != 0) {
            return null;
        }
        // 若v的左子树存在
        if (v.hasLeftChild()) {
            // 找到v的前驱节点
            AbstractBinaryTreeNode<K> w = (AbstractBinaryTreeNode<K>) v.getPrev();
            // 调换两者的数据
            w.setElement(v.setElement(w.getElement()));
            // 将待删除节点指向w节点
            v = w;
        }
        // 至此待删除的v节点至多只有一个孩子
        // 删除v节点用其孩子节点取代之
        p = v.getParent();
        AbstractBinaryTreeNode<K> c = v.hasLeftChild() ? v.getLeftChild() : v.getRightChild();
        // 若v为根节点
        if (p == null) {
            if (c != null) {
                c.secede();
            }
            root = c;
        } else {
            // 如果待删除节点是其父亲节点的左孩子
            if (v.isLeftChild()) {
                // 脱离待删除节点与其父亲节点的关系
                v.secede();
                // 将待删除节点的孩子节点作为其祖父节点的孩子
                p.insertAsLeftChild(c);
            } else {
                // 脱离待删除节点与其父亲节点的关系
                v.secede();
                // 将待删除节点的孩子节点作为其祖父节点的孩子
                p.insertAsRightChild(c);
            }
        }
        return p;
    }
}
