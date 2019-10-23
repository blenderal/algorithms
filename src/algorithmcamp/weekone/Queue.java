package algorithmcamp.weekone;

/**
 * @description: 习题1 实现一个队列 完成以下功能
 * @author: zww
 * @date: 2019/10/23
 * @version: V1.0
 */
public class Queue {
    private static final int N = 100001;
    private String[] queue = new String[N];
    private int head = 0;
    private int tail = 0;

    public void enqueue(String element) {
        queue[tail++] = element;
    }

    public String dequeue() {
        String element = queue[head++];
        queue[head - 1] = null;
        return element;
    }

    public String get(int n) {
        return queue[n - 1];
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        System.out.println(queue.get(1));
        System.out.println(queue.get(2));
        System.out.println(queue.get(3));
        System.out.println(queue.get(4));
        System.out.println(queue.get(5));
        System.out.println(queue.dequeue());
        queue.dequeue();
        System.out.println(queue.get(1));
        System.out.println(queue.get(2));
        System.out.println(queue.get(3));
        System.out.println(queue.get(4));
        System.out.println(queue.get(5));
    }
}
