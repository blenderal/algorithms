package impl.dsa.tree;



import impl.dsa.Iterator;

/**
 * @description: 二叉树抽象类
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public abstract class AbstractBinaryTree<K> implements Tree<K>{

    /**
     * 根结点
     */
    protected AbstractBinaryTreeNode<K> root;


    /**
     * 获取根结点
     *
     * @return 根结点
     */
    @Override
    public TreeNode<K> getRoot() {
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
    public Iterator<K> preorder() {
        return root.elementPreorder();
    }

    /**
     * 中序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<K> inorder() {
        return root.elementInorder();
    }

    /**
     * 后序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<K> postorder() {
        return root.elementPostorder();
    }

    /**
     * 层次遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<K> levelorder() {
        return root.elementLevelorder();
    }

    /**
     * 关键码查找
     * @param key 关键码
     * @return 关键码对应的节点
     */
    abstract TreeNode<K> find(K key);


    /**
     * 插入关键码
     * @param key 关键码
     * @return 插入关键码对应的节点
     */
    abstract TreeNode<K> insert(K key);

    /**
     * 删除关键码
     * @param key 关键码
     * @return 删除关键码对应的节点的父节点
     */
    abstract TreeNode<K> remove(K key);
}
