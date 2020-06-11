package impl.dsa.tree;

import impl.dsa.Iterator;

/**
 * @description: 树相关接口
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public interface Tree<K,V> {

    /**
     * 获取根结点
     * @return 根结点
     */
    TreeNode<K,V> getRoot();

    /**
     * 是否为空
     * @return 是否为空
     */
    Boolean isEmpty();

    /**
     * 获取树的规模
     * @return 树的规模
     */
    int getSize();

    /**
     * 获取树的高度
     * @return 树的高度
     */
    int getHeight();


    /**
     * 前序遍历
     * @return 迭代器
     */
    Iterator<TreeNode<K,V>> preorder();

    /**
     * 中序遍历
     * @return 迭代器
     */
    Iterator<TreeNode<K,V>> inorder();

    /**
     * 后序遍历
     * @return 迭代器
     */
    Iterator<TreeNode<K,V>> postorder();

    /**
     * 层次遍历
     * @return 迭代器
     */
    Iterator<TreeNode<K,V>> levelorder();
}
