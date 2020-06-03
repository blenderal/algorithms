package impl.dsa.tree;

/**
 * @description: B树节点
 * @author: zww
 * @date: 2020/4/27
 * @version: V1.0
 */
public class BTreeNode<K extends Comparable<K>> extends AbstractBTreeNode<K> {
    /**
     * 轴点
     */
    private final int s = order / 2;

    public BTreeNode(int order) {
        super(order);
    }

    /**
     * 节点是否已满
     *
     * @return 节点是否上溢
     */
    @Override
    boolean isOverflow() {
        return order < children.getSize();
    }

    /**
     * 节点是否下溢
     *
     * @return 节点是否下溢
     */
    @Override
    boolean isUnderflow() {
        return (order + 1) / 2 > children.getSize();
    }


    /**
     * 节点分裂
     *
     * @return 父节点
     */
    @Override
    AbstractBTreeNode<K> split() {
        //  新建一个节点用于存放分裂后的右边元素
        AbstractBTreeNode<K> u = new BTreeNode<>(order);
        // 将分裂前的右边元素一一剪切至新节点u
        for (int i = 0; i < order - s - 1; i++) {
            u.insertKeyAt(i, this.removeKeyAt(s + 1));
            u.insertChildAt(i, this.removeChildAt(s + 1));

        }
        u.replaceChildAt(order - s - 1, this.removeChildAt(s + 1));
        // 若新节点u的孩子不为空
        if (!u.isLeaf()) {
            // 将新节点u的孩子的父节点一一指向新节点u
            for (int i = 0; i < order - s; i++) {
                u.getChild(i).setParent(u);
            }
        }
        // 原节点的父节点
        AbstractBTreeNode<K> p = this.getParent();
        // 父节点为空
        if (this.isRoot()) {
            // 则创造一个父节点并将此节点作为根结点
            p = new BTreeNode<>(order);
            p.replaceChildAt(0, this);
            this.setParent(p);
        }
        // 找到父节点应该指向u节点的秩
        int rank = 1 + p.search(this.getKey(0));
        // 父节点插入上溢关键码
        p.insertKeyAt(rank, this.removeKeyAt(s));
        // 父节点与分类出来的新节点u相关联
        p.insertChildAt(rank + 1, u);
        u.setParent(p);
        return p;
    }

    /**
     * 节点合并
     */
    @Override
    AbstractBTreeNode<K> merge() {
        AbstractBTreeNode<K> p = this.getParent();
        // 在父亲节点重的位置
        int r = 1 + p.search(this.getKey(0));
        // 有左兄弟
        if (r > 0) {
            AbstractBTreeNode<K> ls = p.getChild(r - 1);
            // 左兄弟足够胖可以借元素
            if ((order + 1) / 2 < ls.getChildrenSize()) {
                // p借出一个关键码给当前节点（作为最小关键码）
                this.insertKeyAt(0, p.getKey(r - 1));
                // ls的最大关键码转入p
                p.replaceKeyAt(r - 1, ls.removeKeyAt(ls.getKeySize() - 1));
                //同时ls的最右侧孩子过继给当前节点
                this.insertChildAt(0, ls.removeChildAt(ls.getChildrenSize() - 1));
                if (!this.isLeaf()) {
                    this.getChild(0).setParent(this);
                }
                // 至此，通过右旋已完成当前层（以及所有层）的下溢处理
                return p;
            }
        }
        // 至此左兄弟为空要么太瘦
        // 有右兄弟
        if (r < p.getChildrenSize() - 1) {
            AbstractBTreeNode<K> rs = p.getChild(r + 1);
            // 右孩子足够胖可以借元素
            if ((order + 1) / 2 < rs.getChildrenSize()) {
                // p借出一个关键码给当前节点（作为最大关键码）
                this.insertKeyAt(this.getKeySize(), p.getKey(r));
                // rs的最小关键码转入p
                p.replaceKeyAt(r, rs.removeKeyAt(0));
                // 同时rs的最左侧孩子过继给v
                this.insertChildAt(this.getChildrenSize(), rs.removeChildAt(0));
                if (!this.isLeaf()) {
                    this.getChild(this.getChildrenSize() - 1).setParent(this);
                }
                //至此，通过左旋已完成当前层（以及所有层）的下溢处理
                return p;
            }
        }
        // 左、右兄弟要么为空（但不可能同时），要么都太“瘦”――合并
        // 与左兄弟合并
        if (r > 0) {
            AbstractBTreeNode<K> ls = p.getChild(r - 1);
            // p的第r - 1个关键码转入l
            ls.insertKeyAt(ls.getKeySize(), p.removeKeyAt(r - 1));
            // 当前节点不再是p的第r个孩子
            p.removeChildAt(r);
            // 当前节点的最左侧孩子过继给ls做最右侧孩子
            ls.insertChildAt(ls.getChildrenSize(), this.removeChildAt(0));
            if (!ls.isLeaf()) {
                ls.getChild(ls.getChildrenSize() - 1).setParent(ls);
            }
            // 当前节点剩余的关键码和孩子，依次转入ls
            while (!this.isKeyEmpty()) {
                ls.insertKeyAt(ls.getKeySize(), this.removeKeyAt(0));
                ls.insertChildAt(ls.getChildrenSize(), this.removeChildAt(0));
                if (!ls.isLeaf()) {
                    ls.getChild(ls.getChildrenSize() - 1).setParent(ls);
                }
            }
        } else {
            // 与左兄弟合并
            AbstractBTreeNode<K> rs = p.getChild(r + 1);
            // p的第r个关键码转入r
            rs.insertKeyAt(0, p.removeKeyAt(r));
            // 当前节点不再是p的第r个孩子
            p.removeChildAt(r);
            // 当前节点的最左侧孩子过继给ls做最右侧孩子
            rs.insertChildAt(0, this.removeChildAt(this.getChildrenSize() - 1));
            if (!rs.isLeaf()) {
                rs.getChild(0).setParent(rs);
            }
            // 当前节点的关键码和孩子，依次转入rs
            while (!this.isKeyEmpty()) {
                rs.insertKeyAt(0, this.removeKeyAt(this.getKeySize() - 1));
                rs.insertChildAt(0, this.removeChildAt(this.getChildrenSize() - 1));
                if (!rs.isLeaf()) {
                    rs.getChild(0).setParent(rs);
                }
            }
        }
        return this.getParent();
    }
}
