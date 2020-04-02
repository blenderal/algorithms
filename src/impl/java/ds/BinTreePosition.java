package impl.java.ds;



/**
 * @description: 节点位置
 * @author: zww
 * @date: 2020-03-20
 * @version: V1.0
 */
public interface BinTreePosition<T> extends Position<T> {

    Boolean hasParent();

    BinTreePosition<T> getParent();

    void setParent(BinTreePosition<T> p);

    Boolean isLeaf();

    Boolean isLeftChild();

    Boolean hasLeftChild();

    BinTreePosition<T> getLeftChild();

    void setLeftChild(BinTreePosition<T> l);

    Boolean isRightChild();

    Boolean hasRightChild();

    BinTreePosition<T> getRightChild();

    void setRightChild(BinTreePosition<T> r);

    int getSize();

    void updateSize();

    int getHeight();

    void updateHeight();

    int getDepth();

    void updateDepth();

    BinTreePosition<T> getPrev();

    BinTreePosition<T> getSucc();

    BinTreePosition<T> secede();

    BinTreePosition<T> insertAsLeftChild(BinTreePosition<T> l);

    BinTreePosition<T> insertAsRightChild(BinTreePosition<T> r);

    Iterator<T> elementPreorder();

    Iterator<T> elementInorder();

    Iterator<T> elementPostorder();

    Iterator<T> elementLevelorder();
}
