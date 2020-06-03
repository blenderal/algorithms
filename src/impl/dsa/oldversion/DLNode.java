package impl.dsa.oldversion;

/**
 * @description: 基于位置接口实现的双向链表节点类
 * @author: zww
 * @date: 2020-03-23
 * @version: V1.0
 */
public class DLNode<T> implements Position<T> {
    /**
     * 元素
     */
    private T element;

    private DLNode<T> prev;

    private DLNode<T> next;

    public DLNode() {
        this(null,null,null);
    }

    public DLNode(T element, DLNode<T> prev, DLNode<T> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

    /*-------------------------------position method-------------------------------*/

    @Override
    public T getElement() {
        return element;
    }

    @Override
    public T setElement(T t) {
        T oldElement = element;
        element = t;
        return oldElement;
    }

    public DLNode<T> getPrev() {
        return prev;
    }

    public DLNode<T> getNext() {
        return next;
    }

    public void setPrev(DLNode<T> prev) {
        this.prev = prev;
    }

    public void setNext(DLNode<T> next) {
        this.next = next;
    }
}
