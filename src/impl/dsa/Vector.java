package impl.dsa;

/**
 * @description: 向量接口
 * @author: zww
 * @date: 2020/4/13
 * @version: V1.0
 */
public interface Vector<T> {
    /**
     * 返回向量中元素数目
     * @return 向量中元素数目
     */
    int getSize();

    /**
     * 判断向量是否为空
     * @return 向量是否为空
     */
    boolean isEmpty();

    /**
     * 取秩为r的元素
     * @param r 秩
     * @return 秩为r的元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    T getRank(int r) throws IndexOutOfBoundsException;

    /**
     * 将秩为r的元素替换为t
     * @param r 秩
     * @param t t
     * @return 被替换的元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    T replaceAtRank(int r,T t) throws IndexOutOfBoundsException;

    /**
     * 插入t，作为秩为r的元素；返回该元素
     * @param r 秩
     * @param t t
     * @return 返回该元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    T insertAtRank(int r,T t) throws IndexOutOfBoundsException;

    /**
     * 删除秩为r的元素 返回该元素
     * @param r 秩
     * @return 返回该元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    T removeAtRank(int r) throws IndexOutOfBoundsException;
}
