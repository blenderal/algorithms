package impl.dsa.oldversion;

import java.util.NoSuchElementException;

/**
 * @description: 基于双向链表实现列表结构
 * @author: zww
 * @date: 2020-03-23
 * @version: V1.0
 */
public class LinkedList<T> implements List<T> {
    /**
     * 元素数量
     */
    private int numElem;

    private DLNode<T> header, trailer;

    public LinkedList() {
        numElem = 0;
        header = new DLNode<>();
        trailer = new DLNode<>(null, header, null);
        header.setNext(trailer);
    }

    @Override
    public int getSize() {
        return numElem;
    }

    @Override
    public Boolean isEmpty() {
        return numElem == 0;
    }

    @Override
    public Position<T> first() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("意外：列表空");
        }
        return header.getNext();
    }


    @Override
    public Position<T> last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("意外：列表空");
        }
        return trailer.getPrev();
    }

    @Override
    public Position<T> getNext(Position<T> p) throws NoSuchElementException, IndexOutOfBoundsException {
        DLNode<T> node = checkPosition(p);
        DLNode<T> next = node.getNext();
        if (node.getNext() == trailer) {
            throw new IndexOutOfBoundsException();
        }
        return next;
    }

    @Override
    public Position<T> getPre(Position<T> p) throws NoSuchElementException, IndexOutOfBoundsException {
        DLNode<T> node = checkPosition(p);
        DLNode<T> prev = node.getPrev();
        if (prev == header) {
            throw new IndexOutOfBoundsException();
        }
        return prev;
    }

    @Override
    public Position<T> insertFirst(T t) {
        numElem++;
        DLNode<T> newNode = new DLNode<>(t, header, header.getNext());
        header.getNext().setPrev(newNode);
        header.setNext(newNode);
        return newNode;
    }

    @Override
    public Position<T> insertLast(T t) {
        numElem++;
        DLNode<T> newNode = new DLNode<>(t, trailer.getPrev(), trailer);
        trailer.getPrev().setNext(newNode);
        trailer.setPrev(newNode);
        return newNode;
    }

    @Override
    public Position<T> insertAfter(Position<T> p, T t) throws NoSuchElementException {
        DLNode<T> node = checkPosition(p);
        numElem++;
        DLNode<T> newNode = new DLNode<>(t, node, node.getNext());
        node.getNext().setPrev(newNode);
        node.setNext(newNode);
        return newNode;
    }

    @Override
    public Position<T> insetBefore(Position<T> p, T t) throws NoSuchElementException {
        DLNode<T> node = checkPosition(p);
        numElem++;
        DLNode<T> newNode = new DLNode<>(t, node.getPrev(), node);
        node.getPrev().setNext(newNode);
        node.setPrev(newNode);
        return newNode;
    }

    @Override
    public T remove(Position<T> p) throws NoSuchElementException {
        DLNode<T> node = checkPosition(p);
        numElem--;
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node.setPrev(null);
        node.setNext(null);
        return node.getElement();
    }

    @Override
    public T removeFirst() {
        return remove(header.getNext());
    }

    @Override
    public T removeLast() {
        return remove(trailer.getPrev());
    }

    @Override
    public T replace(Position<T> p, T t) throws NoSuchElementException {
        DLNode<T> node = checkPosition(p);
        T element = node.getElement();
        node.setElement(t);
        return element;
    }

    @Override
    public Iterator<Position<T>> positions() {
        return new IteratorPosition<>(this);
    }

    @Override
    public Iterator<T> elements() {
        return new IteratorElement<>(this);
    }


    /**
     * 检查给定位置在列表中是否合法，若是，则将其转换为*DLNode
     */
    protected DLNode<T> checkPosition(Position<T> p) throws IndexOutOfBoundsException {
        if (null == p) {
            throw new IndexOutOfBoundsException("意外：传递给List_DLNode的位置是null");
        }
        if (header == p) {
            throw new IndexOutOfBoundsException("意外：头节点哨兵位置非法");
        }
        if (trailer == p) {
            throw new IndexOutOfBoundsException("意外：尾结点哨兵位置非法");
        }
        return (DLNode<T>) p;
    }
}
