package impl.dsa.btree;

import impl.dsa.LinkedListQueue;
import impl.dsa.Queue;

/**
 * @description: B树类
 * @author: zww
 * @date: 2020/4/27
 * @version: V1.0
 */
public class BTree<K extends Comparable<K>> extends AbstractBTree<K> {
    /**
     * 最后操作的节点
     */
    private AbstractBTreeNode<K> lastV;

    public BTree(int order) {
        super(order);
    }

    /**
     * 查找词条
     *
     * @param key 关键码
     * @return key对应词条
     */
    @Override
    K search(K key) {
        if (isEmpty()) {
            return null;
        }
        AbstractBTreeNode<K> v = find(key);
        if (v == null) {
            return null;
        }
        int rank = v.search(key);
        return v.getKey(rank);

    }

    /**
     * 插入索引
     *
     * @param key 索引
     * @return 插入的索引
     */
    @Override
    K insert(K key) {
        AbstractBTreeNode<K> v = find(key);
        if (v != null) {
            return null;
        }
        int rank = lastV.search(key);
        lastV.insertKey(rank + 1, key);
        lastV.insertChild(rank + 2, null);
        size++;
        if (!lastV.isOverflow()) {
            return key;
        }
        while (lastV.isOverflow()) {
            // 发生下溢节点分裂 返回其父节点 循环检查
            lastV = lastV.split();
        }
        // 根结点分裂后产生新的根结点 使树的根结点指向新的根结点
        if (lastV.isRoot()) {
            root = lastV;
        }
        return key;
    }


    /**
     * 删除索引
     *
     * @param key 索引
     * @return 删除的索引
     */
    @Override
    K remove(K key) {
        AbstractBTreeNode<K> v = find(key);
        if (v == null) {
            return null;
        }
        int rank = v.search(key);
        if (!v.isLeaf()) {
            AbstractBTreeNode<K> u = v.getChild(rank + 1);
            while (!u.isLeaf()) {
                u = u.getChild(0);
            }
            v.replaceKey(rank, u.getKey(0));
            v = u;
            rank = 0;
        }
        v.removeKey(rank);
        v.removeChild(rank + 1);
        size--;
        while (v.isUnderflow()) {
            // 到达根结点
            if (v.isRoot()) {
                // 如果根结点的索引已被孩子节点合并
                if (v.isKeyEmpty() && v.getChild(0) != null) {
                    // 则将根结点指向其唯一的孩子节点
                    root = v.getChild(0);
                    v.getChild(0).setParent(null);
                }
                // 树的高度下降一层
                break;
            }
            v = v.merge();
        }
        return key;
    }

    /**
     * 查找索引对应的节点
     *
     * @param key 索引
     * @return 索引对应的节点
     */
    @Override
    public AbstractBTreeNode<K> find(K key) {
        AbstractBTreeNode<K> v = root;
        while (v != null) {
            int rank = v.search(key);
            if (rank > -1 && rank < v.getKeys().getSize() && v.getKeys().getRank(rank).equals(key)) {
                return v;
            }
            lastV = v;
            v = v.getChild(rank + 1);
        }
        return null;
    }




    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if(isEmpty()){
            return "[]";
        }
        Queue<AbstractBTreeNode<K>> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            AbstractBTreeNode<K> node = queue.dequeue();
            stringBuilder.append(node.keys);
            for (int i = 0; i < node.getChildSize(); i++) {
                if (node.getChild(i) != null) {
                    queue.enqueue(node.getChild(i));
                }
            }

            if (node.isRoot()) {
                stringBuilder.append("\n");
                continue;
            }
            AbstractBTreeNode<K> temp = node;
            while (temp != null) {
                if (temp.isRoot()) {
                    stringBuilder.append("\n");
                    break;
                }
                if (temp.getParent().search(temp.getKey(0)) != temp.getParent().getKeySize() - 1) {
                    break;
                } else {
                    stringBuilder.append("    ");
                }
                temp = temp.getParent();
            }

        }
        return stringBuilder.toString();
    }
}
