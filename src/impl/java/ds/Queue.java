package impl.java.ds;

import java.util.NoSuchElementException;

/**
 * @description: 队列接口
 * @author: zww
 * @date: 2020-03-25
 * @version: V1.0
 */
public interface Queue<T> {
    Boolean isEmpty();

    int getSize();

    T front() throws NoSuchElementException;

    void enqueue(T t);

    T dequeue() throws NoSuchElementException;

    void traversal();
}
