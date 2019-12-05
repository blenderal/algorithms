package leetcode.stack;


import java.util.Stack;

/**
 * @description: 946. 验证栈序列
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 * <p>
 * 链接：https://leetcode-cn.com/problems/validate-stack-sequences
 * @author: zww
 * @date: 2019/12/3
 * @version: V1.0
 */
public class ValidateStackSequences {
    public static void main(String[] args) {

        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] popped = new int[]{4,3,5,1,2};
        System.out.println(solve(pushed, popped));
    }

    /**
     * 栈混洗甄别 模拟栈混洗过程
     * @param pushed
     * @param popped
     * @return
     */
    public static boolean solve(int[] pushed, int[] popped) {
        int length = popped.length;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int push : pushed) {
            stack.push(push);
            while (!stack.isEmpty() && i < length && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return i == length;
    }
}
