package impl.dsa.oldversion;


/**
 * @description: 树接口
 * @author: zww
 * @date: 2020/4/17
 * @version: V1.0
 */
public interface Tree<T> {
    /**
     * 获取根结点
     * @return 根结点
     */
    Position<T> getRoot();

    /**
     * 是否为空
     * @return 是否为空
     */
    Boolean isEmpty();

    /**
     * 获取树的规模
     * @return 树的规模
     */
    int getSize();
}
