package algorithmcamp.weekone;

/**
 * @description: 习题1 实现一个栈 完成以下功能
 * 1.入栈
 * 2.出栈
 * 3.询问栈中位置是谁
 * 一开始栈为空。栈中位置从1开始（即栈第位置为1）
 * @author: zww
 * @date: 2019/10/22
 * @version: V1.0
 */
public class Stack {
    private static final int N = 100001;
    private String[] strings = new String[N];
    private int top = 0;

    public void push(String element) {
        if (element.isEmpty()) {
            return;
        }
        strings[top++] = element;
    }

    public String pop() {
        if (top == 0) {
            return null;
        }
        String element = strings[top - 1];
        strings[top - 1] = null;
        top--;
        return element;
    }

    public String get(int n) {
        return strings[n - 1];
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("a");
        System.out.println(stack.get(1));
        System.out.println(stack.get(2));
        System.out.println(stack.get(3));
        System.out.println(stack.get(4));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.get(1));
        System.out.println(stack.get(2));
        System.out.println(stack.get(3));
        System.out.println(stack.get(4));
    }

}
