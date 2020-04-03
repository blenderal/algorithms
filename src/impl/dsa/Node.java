package impl.dsa;

/**
 * @description: 单向链表节点
 * @author: zww
 * @date: 2020-03-25
 * @version: V1.0
 */
public class Node<T> implements Position<T>{
    private T data;
    private Node<T> next;

    public Node() {
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public T getElement() {
        return data;
    }

    @Override
    public T setElement(T t) {
        T oldData = data;
        data = t;
        return oldData;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
