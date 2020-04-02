package impl;

import impl.list.List;

import java.util.NoSuchElementException;

/**
 * @description: 基于列表实现的元素迭代器
 * @author: zww
 * @date: 2020-03-23
 * @version: V1.0
 */
public class IteratorElement<T> implements Iterator<T> {
    private List<T> list;
    private Position<T> nextPosition;

    public IteratorElement() {
        list = null;
    }

    public IteratorElement(List<T> list) {
        this.list = list;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();
        }
    }

    /**
     * 是否有下一个元素
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean hasNext() {
        return nextPosition != null;
    }

    /**
     * 返回下个元素
     *
     * @return 对应元素
     */
    @Override
    public T getNext() {
        if (!hasNext()) {
            throw new NoSuchElementException("没有下一元素");
        } else {
            Position<T> currentPosition = nextPosition;
            if (currentPosition == list.last()) {
                nextPosition = null;
            } else {
                nextPosition = list.getNext(currentPosition);
            }
            return currentPosition.getElement();
        }
    }
}
