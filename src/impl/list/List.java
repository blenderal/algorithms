package impl.list;

import impl.Iterator;
import impl.Position;

import java.util.NoSuchElementException;

/**
 * @description: 列表接口相关功能
 * @author: zww
 * @date: 2020-03-23
 * @version: V1.0
 */
public interface List<T> {
    int getSize();

    Boolean isEmpty();

    Position<T> first();

    Position<T> last();

    Position<T> getNext(Position<T> p) throws NoSuchElementException,IndexOutOfBoundsException;

    Position<T> getPre(Position<T> p) throws NoSuchElementException,IndexOutOfBoundsException;

    Position<T> insertFirst(T t);

    Position<T> insertLast(T t);

    Position<T> insertAfter(Position<T> p,T t) throws NoSuchElementException;

    Position<T> insetBefore(Position<T> p,T t) throws NoSuchElementException;

    T remove(Position<T> p) throws NoSuchElementException;

    T removeFirst();

    T removeLast();

    T replace(Position<T> p,T t) throws NoSuchElementException;

    Iterator<Position<T>> positions();

    Iterator<T> elements();
}
