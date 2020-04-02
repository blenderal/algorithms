package impl.java.ds;

/**
 * @description: 迭代器
 * @author: zww
 * @date: 2020-03-20
 * @version: V1.0
 */
public interface Iterator<T> {
    /**
     * 是否有下一个元素
     * @return {@link Boolean}
     */
    Boolean hasNext();

    /**
     * 返回下个元素
     * @return 对应元素
     */
    T getNext();
}
