package impl.dsa.tree;



import impl.dsa.Iterator;

/**
 * @description: 二叉树抽象类
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public abstract class AbstractBinaryTree<K,V> implements Tree<K,V>{

    /**
     * 根结点
     */
    protected AbstractBinaryTreeNode<K,V> root;


    /**
     * 获取根结点
     *
     * @return 根结点
     */
    @Override
    public AbstractBinaryTreeNode<K,V> getRoot() {
        return root;
    }

    /**
     * 是否为空
     *
     * @return 是否为空
     */
    @Override
    public Boolean isEmpty() {
        return root == null;
    }

    /**
     * 获取树的规模
     *
     * @return 树的规模
     */
    @Override
    public int getSize() {
        return root == null ? 0 : root.getSize();
    }

    /**
     * 获取树的高度
     *
     * @return 树的高度
     */
    @Override
    public int getHeight() {
        return root == null ? -1 : root.getHeight();
    }

    /**
     * 前序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<TreeNode<K,V>> preorder() {
        return root.elementPreorder();
    }

    /**
     * 中序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<TreeNode<K,V>> inorder() {
        return root.elementInorder();
    }

    /**
     * 后序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<TreeNode<K,V>> postorder() {
        return root.elementPostorder();
    }

    /**
     * 层次遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<TreeNode<K,V>> levelorder() {
        return root.elementLevelorder();
    }

    /**
     * 通过旋转，使节点z的平衡因子的绝对值不超过1（支持AVL树）
     *
     * @param v
     * @return 返回新的子树根
     */
    public AbstractBinaryTreeNode<K,V> rotate(AbstractBinaryTreeNode<K,V> v) {
        AbstractBinaryTreeNode<K, V> p = v.getParent();
        AbstractBinaryTreeNode<K, V> g = p.getParent();
        AbstractBinaryTreeNode<K, V> gg = g.getParent();
        AbstractBinaryTreeNode<K, V> b;
        boolean cType = g.isLeftChild();
        // 分四种情况 3+4重构
        if (p.isLeftChild()) {
            if (v.isLeftChild()) {
                b = connect34(v, p, g, v.getLeftChild(), v.getRightChild(), p.getRightChild(), g.getRightChild());
            } else {
                b = connect34(p, v, g, p.getLeftChild(), v.getLeftChild(), v.getRightChild(), g.getRightChild());
            }
        } else {
            if (v.isRightChild()) {
                b = connect34(g, p, v, g.getLeftChild(), p.getLeftChild(), v.getLeftChild(), v.getRightChild());
            } else {
                b = connect34(g, v, p, g.getLeftChild(), v.getLeftChild(), v.getRightChild(), p.getRightChild());
            }
        }
        if (gg != null) {
            if (cType) {
                gg.insertAsLeftChild(b);
            } else {
                gg.insertAsRightChild(b);
            }
        }
        return b;
    }

    /**
     * 3+4重构
     *  @param a  a节点
     * @param b  b节点
     * @param c  c节点
     * @param t0 t0子树根结点
     * @param t1 t1子树根结点
     * @param t2 t2子树根结点
     * @param t3 t3子树根结点
     * @return
     */
    public AbstractBinaryTreeNode<K,V> connect34(AbstractBinaryTreeNode<K,V> a, AbstractBinaryTreeNode<K,V> b, AbstractBinaryTreeNode<K,V> c,
                                            AbstractBinaryTreeNode<K,V> t0, AbstractBinaryTreeNode<K,V> t1, AbstractBinaryTreeNode<K,V> t2, AbstractBinaryTreeNode<K,V> t3) {
        a.secede();
        b.secede();
        c.secede();
        if (t0 != null) {
            t0.secede();
        }
        if (t1 != null) {
            t1.secede();
        }
        if (t2 != null) {
            t2.secede();
        }
        if (t3 != null) {
            t3.secede();
        }
        a.insertAsLeftChild(t0);
        a.insertAsRightChild(t1);
        c.insertAsLeftChild(t2);
        c.insertAsRightChild(t3);
        b.insertAsLeftChild(a);
        b.insertAsRightChild(c);
        return b;
    }

    /**
     * 关键码查找
     * @param key 关键码
     * @return 关键码对应的节点
     */
    abstract TreeNode<K,V> find(K key);


    /**
     * 插入关键码
     * @param key 关键码
     * @param value 值
     * @return 插入关键码对应的节点
     */
    abstract TreeNode<K,V> insert(K key,V value);

    /**
     * 删除关键码
     * @param key 关键码
     * @return 是否删除成功
     */
    abstract Boolean remove(K key);


}
