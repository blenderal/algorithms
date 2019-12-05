package leetcode.stack;

import java.util.Stack;

/**
 * @description: 栈实现进制转换
 * @author: zww
 * @date: 2019/12/3
 * @version: V1.0
 */
public class Convert {
    public static void main(String[] args) {
        System.out.println(convert(128923,16));
    }

    public static String convert(int num, int formats) {
        char[] digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        Stack<Character> stack = new Stack<>();
        while (num > 0) {
            stack.push(digit[num % formats]);
            num /= formats;
        }
        StringBuilder result= new StringBuilder();
        while (!stack.empty()) {
           result.append(stack.pop());
        }
        return result.toString();
    }
}
