package impl.dsa.tree;

import impl.dsa.Entry;
import impl.dsa.Iterator;

/**
 * @description: 树节点接口
 * @author: zww
 * @date: 2020/5/19
 * @version: V1.0
 */
public interface TreeNode<K,V> {
    /**
     * 获取节点key
     * @return 节点key
     */
    K getKey();

    /**
     * 获取节点value
     * @return 节点value
     */
    V getValue();

    /**
     * 获取节点元素
     * @return 节点元素
     */
    Entry<K,V> getElement();

    /**
     * 获取节点元素
     * @return 节点元素
     */
    Entry<K,V> setElement(Entry<K,V> e);

    /**
     * 设置节点value
     * @param v 新的value
     * @return 返回旧的value
     */
    V setValue(V v);

    /**
     * 获取以该节点为根的子树规模
     *
     * @return 该节点为根的子树规模
     */
    int getSize();

    /**
     * 更新以该节点为根的子树规模
     */
    void updateSize();

    /**
     * 获取节点的高度
     *
     * @return 该节点的高度
     */
    int getHeight();

    /**
     * 更新节点高度
     */
    void updateHeight();

    /**
     * 获取节点的深度
     *
     * @return 该节点的深度
     */
    int getDepth();

    /**
     * 更新节点深度
     */
    void updateDepth();

    /**
     * 获取该节点前继节点
     *
     * @return 该节点前继节点
     */
    TreeNode<K,V> getPrev();

    /**
     * 获取该节点后继节点
     *
     * @return 该节点后继节点
     */
    TreeNode<K,V> getSucc();

    /**
     * 以该节点为根的子树前序遍历
     *
     * @return 子树前序遍历迭代器
     */
    Iterator<TreeNode<K,V>> elementPreorder();

    /**
     * 以该节点为根的子树中序遍历
     *
     * @return 子树中序遍历迭代器
     */
    Iterator<TreeNode<K,V>> elementInorder();

    /**
     * 以该节点为根的子树后序遍历
     *
     * @return 子树后序遍历迭代器
     */
    Iterator<TreeNode<K,V>> elementPostorder();

    /**
     * 以该节点为根的子树层次遍历
     *
     * @return 子树层次遍历迭代器
     */
    Iterator<TreeNode<K,V>> elementLevelorder();
}
