package leetcode.dynamicprograming;

/**
 * @description: 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * @author: zww
 * @date: 2019/11/26
 * @version: V1.0
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(solve(3));
    }

    /**
     * 动态规划
     * 令 dp[i]dp[i] 表示能到达第 i 阶的方法总数：
     * dp[i]=dp[i-1]+dp[i-2]
     * dp[1]=1
     * 实则上该题是个斐波那契数列 也可用斐波那契数列公式求解 复杂度O(logN)
     *
     * @param n
     * @return
     */
    public static int solve(int n) {
        int previous = 1;
        int previousTwo = 1;
        int now = 1;
        for (int i = 2; i <= n; i++) {
            now = previous + previousTwo;
            previousTwo = previous;
            previous = now;
        }
        return now;
    }
}
