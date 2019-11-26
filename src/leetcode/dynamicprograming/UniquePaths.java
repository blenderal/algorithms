package leetcode.dynamicprograming;

/**
 * @description: 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * 0 1 2 3 4 5
 * 0 s
 * 1
 * 2           e
 * <p>
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 * @author: zww
 * @date: 2019/11/26
 * @version: V1.0
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(solve1(7, 3));
    }

    /**
     * 动态规划 自下而上
     * m = 7 n = 3
     * 0  1  2  3  4  5  6
     * 0 1  1  1  1  1  1  1
     * 1 1  2  3  4  5  6  7
     * 2 1  3  6 10 15 21 28
     * <p>
     * table[m][n] = table[m-1][n]+table[m][n-2]
     * table[1][n] = table[m][1] = 1
     *
     * @param m
     * @param n
     * @return
     */
    public static int solve(int m, int n) {
        int[][] table = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 1;
                } else {
                    table[i][j] = table[i - 1][j] + table[i][j - 1];
                }
            }
        }
        return table[m - 1][n - 1];
    }

    /**
     * 递归法
     * @param m
     * @param n
     * @return
     */
    public static int solve1(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return solve1(m - 1, n) + solve1(m, n - 1);

    }
}
