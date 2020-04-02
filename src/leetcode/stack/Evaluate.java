package leetcode.stack;

import java.util.Stack;

import static java.lang.Character.isDigit;

/**
 * @description:
 * @author: zww
 * @date: 2019/12/4
 * @version: V1.0
 */
public class Evaluate {
    public static void main(String[] args) {
        System.out.println(solve("12345"));
    }
    public static float solve(String s) {
        // TODO
        Stack<Float> opnd = new Stack<>();
        Stack<Character> optr = new Stack<>();
        optr.push('\0');
        int i = 0;
//        while (!optr.isEmpty()) {
//            if (isDigit(s.charAt(i))) {
//                readNumber(s, i, opnd);
//            }
//        }
        float number = 0;
        while (i < s.length() && isDigit(s.charAt(i))) {
            number = number * 10 + s.charAt(i) - '0';
            i++;
        }
        opnd.push(number);

        System.out.println(i);
        return opnd.pop();
    }

    private static void readNumber(String s, int i, Stack<Float> opnd) {
        float number = 0;
        while (i < s.length() && isDigit(s.charAt(i))) {
            number = number * 10 + s.charAt(i) - '0';
            i++;
        }
        opnd.push(number);
    }
}
