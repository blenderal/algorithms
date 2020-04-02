package impl.java.ds;



/**
 * @description: 基于链表式BST实现的词典结构
 * 基于BinTree进行扩充
 * @author: zww
 * @date: 2020-04-01
 * @version: V1.0
 */
public class BSTree<K extends Comparable<K>, V> extends BinTree_LinkedList<Entry<K, V>> implements Dictionary<K, V> {

    /**
     * 比较器
     */
    protected Comparator<K> comparator;
    /**
     * 最后操作的节点，以便AVL树、伸展树重平衡
     */
    protected BinTreePosition<Entry<K, V>> lastV;

    public BSTree() {
        this(null, new ComparatorDefault<>());
    }

    public BSTree(BinTreePosition<Entry<K, V>> r) {
        this(r, new ComparatorDefault<>());
    }

    public BSTree(BinTreePosition<Entry<K, V>> r, Comparator<K> comparator) {
        root = r;
        this.comparator = comparator;
    }

    /*-------------------------------------Dictionary 方法-----------------------------------**/

    @Override
    public Entry<K, V> find(K key) {
        if (isEmpty()) {
            return null;
        }
        BSTreeNode<K, V> node = binSearch((BSTreeNode<K, V>) root, key, comparator);
        return comparator.compare(key, node.getKey()) == 0 ? node.getElement() : null;
    }


    @Override
    public Iterator<Entry<K, V>> findAll(K key) {
        List<Entry<K, V>> list = new LinkedList<>();
        findAllNodes(key, (BSTreeNode<K, V>) root, list, comparator);
        return list.elements();
    }

    /**
     * 插入节点
     *
     * @param key   键
     * @param value 值
     * @return 节点存放词条
     */
    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = new EntryDefault<>(key, value);
        if (isEmpty()) {
            lastV = root = new BSTreeNode<>(entry, null, null, null, true);
        } else {
            boolean asLChild;
            BSTreeNode<K, V> p = (BSTreeNode<K, V>) root;
            while (true) {
                p = binSearch(p, key, comparator);
                int compare = comparator.compare(key, p.getKey());
                // key小于目标节点
                if (compare < 0) {
                    asLChild = true;
                    break;
                    // key大于目标节点
                } else if (compare > 0) {
                    asLChild = false;
                    break;
                    // key等于目标节点并且没有左孩子
                } else if (!p.hasLeftChild()) {
                    asLChild = true;
                    break;
                    // key等于目标节点并且有左孩子 没有右孩子
                } else if (!p.hasRightChild()) {
                    asLChild = false;
                    break;
                    // key等于目标节点并且有左孩子 有右孩子
                } else {
                    p = (BSTreeNode<K, V>) p.getLeftChild();
                }
            }
            lastV = new BSTreeNode<>(entry, null, null, p, asLChild);
        }
        return entry;
    }

    @Override
    public Entry<K, V> remove(K key) {
        if (isEmpty()) {
            return null;
        }
        // 待删除的节点
        BinTreePosition<Entry<K, V>> v = binSearch((BSTreeNode<K, V>) root, key, comparator);
        // 查找不成功
        if (comparator.compare(key, v.getElement().getKey()) != 0) {
            return null;
        }
        // 若v的左子树存在
        if (v.hasLeftChild()) {
            // 找到v的前驱节点
            BinTreePosition<Entry<K, V>> w = v.getPrev();
            // 调换两者的数据
            w.setElement(v.setElement(w.getElement()));
            // 将待删除节点指向w节点
            v = w;
        }
        // 至此待删除的v节点至多只有一个孩子
        // 删除v节点用其孩子节点取代之
        lastV = v.getParent();
        BinTreePosition<Entry<K, V>> c = v.hasLeftChild() ? v.getLeftChild() : v.getRightChild();
        // 若v为根节点
        if (lastV == null) {
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
                lastV.insertAsLeftChild(c);
            } else {
                // 脱离待删除节点与其父亲节点的关系
                v.secede();
                // 将待删除节点的孩子节点作为其祖父节点的孩子
                lastV.insertAsRightChild(c);
            }
        }
        return v.getElement();
    }

    @Override
    public Iterator<Entry<K, V>> entries() {
        return null;
    }

    /*-----------------------------private method-------------------------------------**/

    private BSTreeNode<K, V> binSearch(BSTreeNode<K, V> root, K key, Comparator<K> comparator) {
        BSTreeNode<K, V> u = root;
        while (true) {
            int compare = comparator.compare(key, u.getKey());
            // key小于当前节点值
            if (compare < 0) {
                if (u.hasLeftChild()) {
                    u = (BSTreeNode<K, V>) u.getLeftChild();
                } else {
                    return u;
                }
                // key大于当前节点值
            } else if (compare > 0) {
                if (u.hasRightChild()) {
                    u = (BSTreeNode<K, V>) u.getRightChild();
                } else {
                    return u;
                }

            } else { // 查找命中
                return u;
            }
        }
    }


    private void findAllNodes(K key, BSTreeNode<K, V> node, List<Entry<K, V>> list, Comparator<K> comparator) {
        // 递归基
        if (node == null) {
            return;
        }
        int compare = comparator.compare(key, node.getKey());
        if (compare < 0) {
            findAllNodes(key, (BSTreeNode<K, V>) node.getLeftChild(), list, comparator);
        }
        if (compare == 0) {
            list.insertLast(node.getElement());
        }
        if (compare > 0) {
            findAllNodes(key, (BSTreeNode<K, V>) node.getRightChild(), list, comparator);
        }

    }
}
