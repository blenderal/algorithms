package impl.dsa;


import java.util.NoSuchElementException;

/**
 * @description: 基于单链表实现队列结构
 * @author: zww
 * @date: 2020-03-25
 * @version: V1.0
 */
public class LinkedListQueue<T> implements Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedListQueue() {
    }

    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T front() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("意外：队列空");
        }
        return head.getElement();
    }

    @Override
    public void enqueue(T t) {
        Node<T> newNode = new Node<>();
        newNode.setElement(t);
        newNode.setNext(null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("意外：队列空");
        }
        T t = head.getElement();
        head = head.getNext();
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return t;
    }

    @Override
    public void traversal() {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.getElement());
            node = node.getNext();
        }
    }
}
