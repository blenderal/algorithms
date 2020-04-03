package impl.dsa;

/**
 * @description: 比较器接口
 * @author: zww
 * @date: 2020-04-01
 * @version: V1.0
 */
public interface Comparator<T> {
    /**
     * 若a>（=或<）b，返回正数、零或负数
     * @param a
     * @param b
     * @return
     */
    int compare(T a,T b);
}
