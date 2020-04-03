package impl.dsa;

/**
 * @description: 位置接口
 * @author: zww
 * @date: 2020-03-20
 * @version: V1.0
 */
public interface Position<T> {
    T getElement();
    T setElement(T t);
}
