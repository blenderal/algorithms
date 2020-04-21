package impl.dsa;

/**
 * @description: B树节点位置类
 * @author: zww
 * @date: 2020/4/13
 * @version: V1.0
 */
public interface BTreePosition<T> extends Position<T> {

    BTreePosition<T> getParent();

    void setParent(BTreePosition<T> p);

    Boolean hasParent();

    Vector<BTreePosition<T>> getChild();


}
