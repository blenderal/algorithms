package impl.java.ds;


/**
 * @description: 二叉树节点类
 * @author: zww
 * @date: 2020-03-20
 * @version: V1.0
 */

public class BinTreeNode<T> implements BinTreePosition<T> {
    /**
     * 数据
     */
    private T data;
    /**
     * 左孩子
     */
    private BinTreePosition<T> lChild;
    /**
     * 右孩子
     */
    private BinTreePosition<T> rChild;
    /**
     * 父节点
     */
    private BinTreePosition<T> parent;
    /**
     * 高度
     */
    private int height;
    /**
     * 深度
     */
    private int depth;

    /**
     * 红黑树对应颜色
     */
    private RBColor rbColor;

    /**
     * 子树规模
     */
    private int size;

    public BinTreeNode() {
    }

    public BinTreeNode(T data, BinTreePosition<T> lChild, BinTreePosition<T> rChild, BinTreePosition<T> parent, boolean asLChild) {
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

        this.data = data;
        if (lChild != null) {
            insertAsLeftChild(lChild);
        }
        if (rChild != null) {
            insertAsRightChild(rChild);
        }
    }

    /*---------------------------------------------Position接口方法实现---------------------------------------*/

    @Override
    public T getElement() {
        return this.data;
    }

    @Override
    public T setElement(T t) {
        T tmp = this.data;
        this.data = t;
        return tmp;
    }

    /*---------------------------------------------BinNodePosition接口方法实现---------------------------------------*/

    @Override
    public Boolean hasParent() {

        return parent != null;
    }

    @Override
    public BinTreePosition<T> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinTreePosition<T> p) {
        parent = p;
    }

    @Override
    public Boolean isLeaf() {
        return lChild == null && rChild == null;
    }

    @Override
    public Boolean isLeftChild() {
        return hasParent() && parent.getLeftChild() == this;
    }

    @Override
    public Boolean hasLeftChild() {
        return lChild != null;
    }

    @Override
    public BinTreePosition<T> getLeftChild() {
        return lChild;
    }

    @Override
    public void setLeftChild(BinTreePosition<T> l) {
        lChild = l;
    }

    @Override
    public Boolean isRightChild() {
        return hasParent() && parent.getRightChild() == this;
    }

    @Override
    public Boolean hasRightChild() {
        return rChild != null;
    }

    @Override
    public BinTreePosition<T> getRightChild() {
        return rChild;
    }

    @Override
    public void setRightChild(BinTreePosition<T> r) {
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

    @Override
    public BinTreePosition<T> getPrev() {
        // 有左孩子
        if(hasLeftChild()){
            return findMaxDescendant(getLeftChild());
        }
        // 没有左孩子且自己是右孩子
        if(isRightChild()){
            return getParent();
        }
        // 没有左孩子且自己是左孩子
        BinTreePosition<T> v = this;
        while (v.isLeftChild()){
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
    public BinTreePosition<T> getSucc() {
        // 有右孩子
        if (hasRightChild()) {
            return findMinDescendant(getRightChild());
        }
        // 没有右孩子 且自己是左孩子
        if(isLeftChild()){
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
        BinTreePosition<T> v = this;
        // 沿着当前节点一直往上查找 直到节点为左孩子 或者节点没有父节点
        while(v.isRightChild()){
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
    @Override
    public BinTreePosition<T> secede() {
        if (hasParent()) {
            if (isLeftChild()) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            this.setParent(null);
            parent.updateSize();
            parent.updateHeight();
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
    @Override
    public BinTreePosition<T> insertAsLeftChild(BinTreePosition<T> l) {
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
    @Override
    public BinTreePosition<T> insertAsRightChild(BinTreePosition<T> r) {
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
    public Iterator<T> elementPreorder() {
        List<T> list = new LinkedList<>();
        preorder(list, this);
        return list.elements();
    }

    /**
     * 中序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> elementInorder() {
        List<T> list = new LinkedList<>();
        inorder(list, this);
        return list.elements();
    }

    /**
     * 后序遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> elementPostorder() {
        List<T> list = new LinkedList<>();
        postorder(list, this);
        return list.elements();
    }

    /**
     * 层次遍历
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> elementLevelorder() {
        List<T> list = new LinkedList<>();
        levelorder(list, this);
        return list.elements();
    }

    /*---------------------------------------------private method---------------------------------------*/

    private BinTreePosition<T> findMaxDescendant(BinTreePosition<T> v) {
        if(v!=null){
            while(hasRightChild()){
                v = v.getRightChild();
            }
        }
        return v;
    }

    private BinTreePosition<T> findMinDescendant(BinTreePosition<T> v) {
        if (v != null) {
            while (v.hasLeftChild()) {
                v = v.getLeftChild();
            }
        }
        return v;
    }

    private void preorder(List<T> list, BinTreePosition<T> v) {
        if (v == null) {
            return;
        }
        list.insertLast(v.getElement());
        preorder(list, v.getLeftChild());
        preorder(list, v.getRightChild());
    }

    private void inorder(List<T> list, BinTreePosition<T> position) {
        if (position == null) {
            return;
        }
        inorder(list, position.getLeftChild());
        list.insertLast(position.getElement());
        inorder(list, position.getRightChild());
    }

    private void postorder(List<T> list, BinTreePosition<T> v) {
        if (v == null) {
            return;
        }
        postorder(list, v.getLeftChild());
        postorder(list, v.getRightChild());
        list.insertLast(v.getElement());
    }

    private void levelorder(List<T> list, BinTreePosition<T> v) {
        Queue<BinTreePosition<T>> queue = new LinkedListQueue<>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            BinTreePosition<T> position = queue.dequeue();
            list.insertLast(position.getElement());
            if (position.hasLeftChild()) {
                queue.enqueue(position.getLeftChild());
            }
            if (position.hasRightChild()) {
                queue.enqueue(position.getRightChild());
            }
        }
    }


    public static void main(String[] args) {
        BinTreeNode<Integer> root = new BinTreeNode<>(1, null, null, null, false);
        BinTreeNode<Integer> left = new BinTreeNode<>(2, null, null, null, false);
        BinTreeNode<Integer> right = new BinTreeNode<>(3, null, null, null, false);
        root.insertAsLeftChild(left);
        root.insertAsRightChild(right);
        BinTree<Integer> binTree = new BinTree_LinkedList<>(root);
        Iterator<Integer> iterator = binTree.levelorder();
        while (iterator.hasNext()) {
            System.out.println(iterator.getNext());
        }
        System.out.println(binTree.getHeight());
        System.out.println(binTree.getSize());
        System.out.println(binTree.getRoot());
    }


}
