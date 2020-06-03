package impl.dsa.tree;

import impl.dsa.*;

/**
 * @description: 抽象二叉树节点类
 * @author: zww
 * @date: 2020/5/20
 * @version: V1.0
 */
public abstract class AbstractBinaryTreeNode<K> implements TreeNode<K> {
    public AbstractBinaryTreeNode(K key, AbstractBinaryTreeNode<K> lChild, AbstractBinaryTreeNode<K> rChild, AbstractBinaryTreeNode<K> parent, boolean asLChild) {
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

    /**
     * 关键码
     */
    protected K key;
    /**
     * 左孩子
     */
    protected AbstractBinaryTreeNode<K> lChild;
    /**
     * 右孩子
     */
    protected AbstractBinaryTreeNode<K> rChild;
    /**
     * 父节点
     */
    protected AbstractBinaryTreeNode<K> parent;
    /**
     * 高度
     */
    protected int height;
    /**
     * 深度
     */
    protected int depth;

    /**
     * 子树规模
     */
    protected int size;


    /**
     * 获取节点元素
     *
     * @return 节点元素
     */
    @Override
    public K getElement() {
        return this.key;
    }

    /**
     * 设置节点元素
     *
     * @param key 新的元素
     * @return 返回旧的元素
     */
    @Override
    public K setElement(K key) {
        K tmp = this.key;
        this.key = key;
        return tmp;
    }


    public Boolean hasParent() {
        return parent != null;
    }


    public AbstractBinaryTreeNode<K> getParent() {
        return parent;
    }


    public void setParent(AbstractBinaryTreeNode<K> p) {
        parent = p;
    }


    public Boolean isLeaf() {
        return lChild == null && rChild == null;
    }


    public Boolean isLeftChild() {
        return hasParent() && parent.getLeftChild() == this;
    }


    public Boolean hasLeftChild() {
        return lChild != null;
    }


    public AbstractBinaryTreeNode<K> getLeftChild() {
        return lChild;
    }


    public void setLeftChild(AbstractBinaryTreeNode<K> l) {
        lChild = l;
    }


    public Boolean isRightChild() {
        return hasParent() && parent.getRightChild() == this;
    }


    public Boolean hasRightChild() {
        return rChild != null;
    }


    public AbstractBinaryTreeNode<K> getRightChild() {
        return rChild;
    }


    public void setRightChild(AbstractBinaryTreeNode<K> r) {
        rChild = r;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void updateSize() {
        size = 1;
        if (hasLeftChild()) {
            size += lChild.getSize();
        }
        if (hasRightChild()) {
            size += rChild.getSize();
        }
        if (hasParent()) {
            parent.updateSize();
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateHeight() {
        height = 0;
        if (hasLeftChild()) {
            height = Math.max((1 + lChild.getHeight()), height);
        }
        if (hasRightChild()) {
            height = Math.max((1 + rChild.getHeight()), height);
        }
        if (hasParent()) {
            parent.updateHeight();
        }
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void updateDepth() {
        depth = hasParent() ? 1 + parent.getDepth() : 0;
        if (hasLeftChild()) {
            lChild.updateDepth();
        }
        if (hasRightChild()) {
            rChild.updateDepth();
        }
    }

    /**
     * 获取该节点前继节点
     *
     * @return 该节点前继节点
     */
    @Override
    public TreeNode<K> getPrev() {
        // 有左孩子
        if (hasLeftChild()) {
            return findMaxDescendant(getLeftChild());
        }
        // 没有左孩子且自己是右孩子
        if (isRightChild()) {
            return getParent();
        }
        // 没有左孩子且自己是左孩子
        AbstractBinaryTreeNode<K> v = this;
        while (v.isLeftChild()) {
            v = v.getParent();
        }
        return v.getParent();
    }


    /**
     * 中序遍历意义下当前节点的直接后继
     *
     * @return 直接后继节点位置
     */
    @Override
    public AbstractBinaryTreeNode<K> getSucc() {
        // 有右孩子
        if (hasRightChild()) {
            return findMinDescendant(getRightChild());
        }
        // 没有右孩子 且自己是左孩子
        if (isLeftChild()) {
            return getParent();
        }
        // 没有右孩子 且自己是右孩子
        /*
         *          succ        gp
         *          /            \
         *         /              p
         *        /                \
         *       /                  v
         *      /                  /
         *     gp                lTree
         *      \
         *       p
         *        \
         *         v
         *        /
         *      lTree
         */
        AbstractBinaryTreeNode<K> v = this;
        // 沿着当前节点一直往上查找 直到节点为左孩子 或者节点没有父节点
        while (v.isRightChild()) {
            v = v.getParent();
        }
        // 直到节点左孩子则返回他的父节点 或者 节点没有父节点则没有后继节点
        return v.getParent();
    }


    /**
     * 断绝当前节点与其父亲的父子关系
     *
     * @return 当前节点
     */
    public AbstractBinaryTreeNode<K> secede() {
        if (hasParent()) {
            if (isLeftChild()) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            parent.updateSize();
            parent.updateHeight();
            this.setParent(null);
            updateDepth();
        }
        return this;
    }


    /**
     * 添加左孩子
     *
     * @param l 待添加的元素
     * @return 当前节点位置
     */
    public AbstractBinaryTreeNode<K> insertAsLeftChild(AbstractBinaryTreeNode<K> l) {
        if (hasLeftChild()) {
            lChild.secede();
        }
        if (l != null) {
            l.secede();
            setLeftChild(l);
            l.setParent(this);
            updateHeight();
            updateSize();
            l.updateDepth();
        }
        return this;
    }

    /**
     * 添加右孩子
     *
     * @param r 待添加的元素
     * @return 当前节点位置
     */

    public AbstractBinaryTreeNode<K> insertAsRightChild(AbstractBinaryTreeNode<K> r) {
        if (hasRightChild()) {
            rChild.secede();
        }
        if (r != null) {
            r.secede();
            rChild = r;
            r.setParent(this);
            updateSize();
            updateHeight();
            r.updateDepth();
        }
        return this;
    }

    /**
     * 前序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<K> elementPreorder() {
        List<K> list = new LinkedList<>();
        preorder(list, this);
        return list.elements();
    }

    /**
     * 中序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<K> elementInorder() {
        List<K> list = new LinkedList<>();
        inorder(list, this);
        return list.elements();
    }

    /**
     * 后序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<K> elementPostorder() {
        List<K> list = new LinkedList<>();
        postorder(list, this);
        return list.elements();
    }

    /**
     * 层次遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<K> elementLevelorder() {
        List<K> list = new LinkedList<>();
        levelorder(list, this);
        return list.elements();
    }

    /*---------------------------------------------private method---------------------------------------*/

    private AbstractBinaryTreeNode<K> findMaxDescendant(AbstractBinaryTreeNode<K> v) {
        if (v != null) {
            while (v.hasRightChild()) {
                v = v.getRightChild();
            }
        }
        return v;
    }

    private AbstractBinaryTreeNode<K> findMinDescendant(AbstractBinaryTreeNode<K> v) {
        if (v != null) {
            while (v.hasLeftChild()) {
                v = v.getLeftChild();
            }
        }
        return v;
    }

    private void preorder(List<K> list, AbstractBinaryTreeNode<K> v) {
        if (v == null) {
            return;
        }
        list.insertLast(v.getElement());
        preorder(list, v.getLeftChild());
        preorder(list, v.getRightChild());
    }

    private void inorder(List<K> list, AbstractBinaryTreeNode<K> position) {
        if (position == null) {
            return;
        }
        inorder(list, position.getLeftChild());
        list.insertLast(position.getElement());
        inorder(list, position.getRightChild());
    }

    private void postorder(List<K> list, AbstractBinaryTreeNode<K> v) {
        if (v == null) {
            return;
        }
        postorder(list, v.getLeftChild());
        postorder(list, v.getRightChild());
        list.insertLast(v.getElement());
    }

    private void levelorder(List<K> list, AbstractBinaryTreeNode<K> v) {
        Queue<AbstractBinaryTreeNode<K>> queue = new LinkedListQueue<>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            AbstractBinaryTreeNode<K> position = queue.dequeue();
            list.insertLast(position.getElement());
            if (position.hasLeftChild()) {
                queue.enqueue(position.getLeftChild());
            }
            if (position.hasRightChild()) {
                queue.enqueue(position.getRightChild());
            }
        }
    }
}
