package impl.java.ds;


import java.util.NoSuchElementException;

/**
 * @description: 基于列表实现的位置迭代器
 * @author: zww
 * @date: 2020-03-23
 * @version: V1.0
 */
public class IteratorPosition<T> implements Iterator<Position<T>> {
    private List<T> list;

    private Position<T> nextPosition;

    public IteratorPosition() {
        list = null;
    }

    public IteratorPosition(List<T> list) {
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
        return nextPosition == null;
    }

    /**
     * 返回下个元素
     *
     * @return 对应元素
     */
    @Override
    public Position<T> getNext() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("没有下个元素");
        } else {
            Position<T> currentPosition = nextPosition;
            if (currentPosition == list.last()) {
                nextPosition = null;
            } else {
                nextPosition = list.getNext(currentPosition);
            }
            return currentPosition;
        }
    }
}
