package impl.java.ds;

import java.util.Comparator;

/**
 * @description: 默认比较器
 * @author: zww
 * @date: 2020-04-01
 * @version: V1.0
 */
public class ComparatorDefault<T> implements Comparator<T> {

    /**
     * 若a>（=或<）b，返回正数、零或负数
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(T a, T b) throws ClassCastException{
        return ((Comparable) a).compareTo(b);
    }
}
