package impl.tree;

import impl.Iterator;

/**
 * @description: 基于链表实现的二叉树
 * @author: zww
 * @date: 2020-03-23
 * @version: V1.0
 */
public class BinTree_LinkedList<T> implements BinTree<T> {
    protected BinTreePosition<T> root;

    public BinTree_LinkedList() {
        root = null;
    }

    public BinTree_LinkedList(BinTreePosition<T> root) {
        this.root = root;
    }


    /**
     * 获取根结点
     *
     * @return 根结点
     */
    @Override
    public BinTreePosition<T> getRoot() {
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
    public Iterator<T> preorder() {
        return root.elementPreorder();
    }

    /**
     * 中序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> inorder() {
        return root.elementInorder();
    }

    /**
     * 后序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> postorder() {
        return root.elementPostorder();
    }

    /**
     * 层次遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> levelorder() {
        return root.elementLevelorder();
    }
}
