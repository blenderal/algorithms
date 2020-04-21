package impl.dsa;

/**
 * @description: B树类
 * @author: zww
 * @date: 2020/4/17
 * @version: V1.0
 */
public class BTree<K extends Comparable<K>, V> implements B_Tree<K, V> {

    /**
     * 关键码数
     */
    private int size;

    /**
     * 根结点
     */
    private BTreePosition<Vector<Entry<K, V>>> root;

    /**
     * B树阶树
     */
    private final int order;

    /**
     * 比较器
     */
    protected Comparator<K> comparator;

    /**
     * 最后操作的节点
     */
    protected BTreePosition<Vector<Entry<K, V>>> lastV;

    public BTree(int order) {
        this(null, order, new ComparatorDefault<>());
    }

    public BTree(BTreePosition<Vector<Entry<K, V>>> root, int order) {
        this(root, order, new ComparatorDefault<>());
    }

    public BTree(BTreePosition<Vector<Entry<K, V>>> root, int order, Comparator<K> comparator) {
        if (order < 3) {
            order = 3;
        }
        this.root = root;
        this.order = order;
        this.comparator = comparator;
        this.size = root == null ? 0 : root.getElement().getSize();
    }

    /**
     * 获取树的规模
     *
     * @return 树的规模
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 获取根结点
     *
     * @return 根结点
     */
    @Override
    public Position<Vector<Entry<K, V>>> getRoot() {
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
     * 搜索关键码对应的词条
     *
     * @param key 关键码
     * @return 关键码对应的词条
     */
    @Override
    public Entry<K, V> search(K key) {
        if (isEmpty()) {
            return null;
        }
        BTreeNode<K, V> node = find((BTreeNode<K, V>) root, key);
        if (node == null) {
            return null;
        }
        int rank = findRank(lastV.getElement(), key);
        return lastV.getElement().getRank(rank);
    }

    /**
     * 插入词条
     *
     * @param key   关键码
     * @param value 值
     * @return 插入的词条
     */
    @Override
    public Entry<K, V> insert(K key, V value) {
        BTreeNode<K, V> node = find((BTreeNode<K, V>) root, key);
        if (node != null) {
            return null;
        }
        Vector<Entry<K, V>> data = lastV.getElement();
        int rank = findRank(data, key);
        Entry<K, V> entry = new EntryDefault<>(key, value);
        data.insertAtRank(rank + 1, entry);
        size++;
        // 关键码插入后若节点上溢，则做节点分裂处理
        solveOverflow(lastV);
        return entry;
    }

    /**
     * 删除关键码对应的词条
     *
     * @param key 关键码
     * @return 删除的词条
     */
    @Override
    public Entry<K, V> remove(K key) {
        BTreeNode<K, V> v = find((BTreeNode<K, V>) root, key);
        if (v == null) {
            return null;
        }
        // 找到待删除的节点的秩序
        int rank = findRank(v.getElement(), key);
        Entry<K, V> e = v.getElement().getRank(rank);
        // 如果待删除的关键码所在节点不为叶节点
        if (v.getChild().getRank(0) != null) {
            // 找到待删除关键码的后继节点

            BTreePosition<Vector<Entry<K, V>>> u = v.getChild().getRank(rank + 1);
            // 往关键码的右子树左孩子持续深入找到后继节点
            while (u.getChild() != null) {
                u = u.getChild().getRank(0);
            }
            // 互换关键码词条
            v.getElement().replaceAtRank(rank, u.getElement().removeAtRank(0));
            // 将待删除关键码的节点指向对换数据后的叶子节点
            v = (BTreeNode<K, V>) u;
            rank = 0;
        }
        // 删除对换后的词条
        v.getElement().removeAtRank(rank);
        v.getChild().removeAtRank(rank + 1);
        size--;
        // 处理节点下溢
        solveUnderflow(v);
        return e;
    }

    /**
     * 关键码删除后若节点下溢，则做节点旋转或合并处理
     *
     * @param v 可能发生下溢的节点
     */
    private void solveUnderflow(BTreeNode<K, V> v) {
        // 递归基 未发生下溢
        if ((order + 1) / 2 >= v.getChild().getSize()) {
            return;
        }
        BTreePosition<Vector<Entry<K, V>>> p = v.getParent();
        // 到达根结点无父节点
        if (p == null) {
            // 且根结点为空节点指向唯一一个孩子
            if (v.getElement().getSize() == 0 && v.getChild().getRank(0) != null) {
                // 则将其孩子作为根结点
                root = v.getChild().getRank(0);
                root.setParent(null);
            }
            // 树的整体高度下降一层
            return;
        }
        //
        int r = 0;
        while (p.getChild().getRank(r) != v) {
            r++;
        }
        // 有左兄弟
        if (r > 0) {
            BTreeNode<K, V> ls = (BTreeNode<K, V>) p.getChild().getRank(r - 1);
            // 左兄弟足够胖可以借元素
            if ((order + 1) / 2 < ls.getChild().getSize()) {
                v.getElement().insertAtRank(0, p.getElement().getRank(r - 1));
                p.getElement().replaceAtRank(r - 1, ls.getElement().removeAtRank(ls.getElement().getSize() - 1));
                v.getChild().insertAtRank(0, ls.getChild().removeAtRank(ls.getChild().getSize() - 1));
                if (v.getChild().getRank(0) != null) {
                    v.getChild().getRank(0).setParent(v);
                }
                return;
            }
        }
        // 至此，左兄弟要么为空，要么太“瘦”
        // v不是父节点的最后一个孩子
        if (p.getChild().getSize() - 1 > r) {
            BTreeNode<K, V> rs = (BTreeNode<K, V>) p.getChild().getRank(r + 1);
            // 右兄弟足够胖可以借元素
            if ((order + 1) / 2 < rs.getChild().getSize()) {
                v.getElement().insertAtRank(v.getElement().getSize(), p.getElement().getRank(r));
                p.getElement().replaceAtRank(r, rs.getElement().removeAtRank(0));
                v.getChild().insertAtRank(v.getChild().getSize(), rs.getChild().removeAtRank(0));
                if (v.getChild().getRank(v.getChild().getSize() - 1) != null) {
                    v.getChild().getRank(v.getChild().getSize() - 1).setParent(v);
                }
                return;
            }
        }
        // 至此，左、右兄弟要么为空（但不可能同时），要么都太“瘦”――合并
        // 与左兄弟一起合并
        if (r > 0) {
            BTreeNode<K, V> ls = (BTreeNode<K, V>) p.getChild().getRank(r - 1);
            ls.getElement().insertAtRank(ls.getElement().getSize(), p.getElement().removeAtRank(r - 1));
            p.getChild().removeAtRank(r - 1);
            ls.getChild().insertAtRank(ls.getChild().getSize(), v.getChild().removeAtRank(0));
            if (ls.getChild().getRank(ls.getChild().getSize()) != null) {
                ls.getChild().getRank(ls.getChild().getSize()).setParent(ls);
            }
            while (!v.getElement().isEmpty()) {
                ls.getElement().insertAtRank(ls.getElement().getSize(), v.getElement().removeAtRank(0));
                ls.getChild().insertAtRank(ls.getChild().getSize(), v.getChild().removeAtRank(0));
                if (ls.getChild().getRank(ls.getChild().getSize() - 1) != null) {
                    ls.getChild().getRank(ls.getChild().getSize() - 1).setParent(ls);
                }
            }
        } else {
            // 与右兄弟进行合并
            BTreeNode<K, V> rs = (BTreeNode<K, V>) p.getChild().getRank(r + 1);
            rs.getElement().insertAtRank(0, p.getElement().removeAtRank(r));
            p.getChild().removeAtRank(r);
            rs.getChild().insertAtRank(0, v.getChild().removeAtRank(p.getElement().getSize() - 1));
            if (rs.getChild().getRank(0) != null) {
                rs.getChild().getRank(0).setParent(rs);
            }
            while (!v.getElement().isEmpty()) {
                rs.getElement().insertAtRank(0, v.getElement().removeAtRank(v.getElement().getSize() - 1));
                rs.getChild().insertAtRank(0, v.getChild().removeAtRank(v.getElement().getSize() - 1));
                if (rs.getChild().getRank(0) != null) {
                    rs.getChild().getRank(0).setParent(rs);
                }
            }
        }
        // 上升一层，如有必要则继续分裂――至多递归O(logN)层
        solveUnderflow((BTreeNode<K, V>) p);
    }

    /**
     * 获取B树阶数
     *
     * @return B树阶数
     */
    @Override
    public int getOrder() {
        return order;
    }


    /*-----------------------------private method------------------------**/

    /**
     * 关键码插入后若节点上溢，则做节点分裂处理
     *
     * @param v 最后操作的节点
     */
    private void solveOverflow(BTreePosition<Vector<Entry<K, V>>> v) {
        // 递归基 节点未上溢
        if (order >= v.getChild().getSize()) {
            return;
        }
        // 轴点
        int s = order / 2;
        // 新建一个节点用于存放分裂后的右边元素
        BTreePosition<Vector<Entry<K, V>>> u = new BTreeNode<>();
        // 将分裂前的右边元素一一剪切至新节点u
        for (int i = 0; i < order - s - 1; i++) {
            u.getElement().insertAtRank(i, v.getElement().removeAtRank(s + 1));
            u.getChild().insertAtRank(i, v.getChild().removeAtRank(s + 1));
        }
        u.getChild().insertAtRank(order - s - 1, v.getChild().removeAtRank(s + 1));
        Vector<BTreePosition<Vector<Entry<K, V>>>> uChild = u.getChild();
        // 若新节点u的孩子不为空
        if (uChild.getRank(0) != null) {
            // 将新节点u的孩子的父节点一一指向新节点u
            for (int i = 0; i < order - s; i++) {
                uChild.getRank(i).setParent(u);
            }
        }
        // 原节点的父节点
        BTreePosition<Vector<Entry<K, V>>> p = v.getParent();
        // 父节点为空
        if (p == null) {
            // 则创造一个父节点并将此节点作为根结点
            p = new BTreeNode<>();
            p.getChild().insertAtRank(0, v);
            v.setParent(p);
            root = p;
        }
        // 找到父节点应该指向u节点的秩
        int r = 1 + findRank(p.getElement(), v.getElement().getRank(0).getKey());
        // 父节点插入上溢关键码
        p.getElement().insertAtRank(r, v.getElement().removeAtRank(s));
        // 父节点与分类出来的新节点u相关联
        p.getChild().insertAtRank(r + 1, u);
        u.setParent(p);
        // 上升一层，如有必要则继续分裂――至多递归O(logN)层
        solveOverflow(p);
    }

    /**
     * 查找key对应的节点
     *
     * @param v   查找的节点
     * @param key key
     * @return key对应的节点
     */
    private BTreeNode<K, V> find(BTreeNode<K, V> v, K key) {
        while (v != null) {
            Vector<Entry<K, V>> vector = v.getElement();
            int rank = findRank(vector, key);
            // 查找成功在当前节点中命中目标关键码
            if (rank > -1 && rank < vector.getSize() && vector.getRank(rank).equals(key)) {
                return v;
            }
            lastV = v;
            v = (BTreeNode<K, V>) v.getChild().getRank(rank + 1);
        }
        return null;
    }

    /**
     * 在当前节点中，找到不大于key的最大关键码
     *
     * @param vector 当前节点向量
     * @param key    查找关键码
     * @return 不大于key的最大关键码
     */
    private int findRank(Vector<Entry<K, V>> vector, K key) {
        int n = vector.getSize();
        for (int i = 0; i < n; i++) {
            int c = comparator.compare(key, vector.getRank(i).getKey());
            if (c >= 0) {
                if (c > 0) {
                    return i - 1;
                } else {
                    return i;
                }
            }
        }
        return n;
    }
}
