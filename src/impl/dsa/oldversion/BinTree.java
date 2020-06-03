package impl.dsa.oldversion;




/**
 * @description: 二叉树接口
 * @author: zww
 * @date: 2020-03-23
 * @version: V1.0
 */
public interface BinTree<T> extends Tree<T> {

    /**
     * 获取树的高度
     * @return 树的高度
     */
    int getHeight();

    /**
     * 前序遍历
     * @return 迭代器
     */
    Iterator<T> preorder();

    /**
     * 中序遍历
     * @return 迭代器
     */
    Iterator<T> inorder();

    /**
     * 后序遍历
     * @return 迭代器
     */
    Iterator<T> postorder();

    /**
     * 层次遍历
     * @return 迭代器
     */
    Iterator<T> levelorder();
}
