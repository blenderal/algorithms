package impl.dsa.oldversion;

/**
 * @description: 基于可扩充数组的向量实现
 * @author: zww
 * @date: 2020/4/13
 * @version: V1.0
 */
public class ExtArrayVector<T> implements Vector<T> {
    /**
     * 向量容量 可不断增加
     */
    private int N = 8;
    /**
     * 向量实际容量
     */
    private int n;
    /**
     * 对象数组
     */
    private Object[] A;

    public ExtArrayVector() {
        A = new Object[N];
        n = 0;
    }

    @SuppressWarnings("unchecked")
    T elementData(int r) {
        return (T) A[r];
    }

    /*-------------------------vector 接口方法----------------------**/

    /**
     * 返回向量中元素数目
     *
     * @return 向量中元素数目
     */
    @Override
    public int getSize() {
        return n;
    }

    /**
     * 判断向量是否为空
     *
     * @return 向量是否为空
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 取秩为r的元素
     *
     * @param r 秩
     * @return 秩为r的元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    @Override
    public T getRank(int r) throws IndexOutOfBoundsException {
        if (r < 0 || r > n - 1) {
            throw new IndexOutOfBoundsException("意外：秩越界");
        }
        return elementData(r);
    }

    /**
     * 将秩为r的元素替换为t
     *
     * @param r 秩
     * @param t t
     * @return 被替换的元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    @Override
    public T replaceAtRank(int r, T t) throws IndexOutOfBoundsException {
        if (r < 0 || r > n - 1) {
            throw new IndexOutOfBoundsException("意外：秩越界");
        }
        T bak = elementData(r);
        A[r] = t;
        return bak;
    }

    /**
     * 插入t，作为秩为r的元素；返回该元素
     *
     * @param r 秩
     * @param t t
     * @return 返回该元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    @Override
    public T insertAtRank(int r, T t) throws IndexOutOfBoundsException {
        if (r < 0 || r > n - 1) {
            throw new IndexOutOfBoundsException("意外：秩越界");
        }
        if (n >= N) {
            N = N * 2;
            Object[] B = new Object[N];
            for (int i = 0; i < n; i++) {
                B[i] = A[i];
            }
            A = B;
        }
        for (int i = n; i > r; i--) {
            A[i] = A[i - 1];
        }
        T bak = elementData(r);
        A[r] = t;
        n++;
        return bak;
    }

    /**
     * 删除秩为r的元素 返回该元素
     *
     * @param r 秩
     * @return 返回该元素
     * @throws IndexOutOfBoundsException 数组越界异常
     */
    @Override
    public T removeAtRank(int r) throws IndexOutOfBoundsException {
        if (r < 0 || r > n - 1) {
            throw new IndexOutOfBoundsException("意外：秩越界");
        }
        T bak = elementData(r);
        for (int i = r; i < n; i++) {
            A[i] = A[i + 1];
        }
        n--;
        return bak;
    }

}
