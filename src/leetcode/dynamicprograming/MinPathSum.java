package leetcode.dynamicprograming;

/**
 * @description: 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * @author: zww
 * @date: 2019/11/26
 * @version: V1.0
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1}, {1, 5, 1}, {4, 2, 1}
        };
        System.out.println(solve(grid));
    }

    /**
     * 动态规划
     * 先解决第一行第一列每个方格的最小值 其值为其上方或左方的值加当前方格值
     * 然后解决其他方格 其他方格为上方与左方的最小值加当前方格值
     * 参考：{@link leetcode.dynamicprograming.UniquePaths}
     * @param grid
     * @return
     */
    public static int solve(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;
        for (int i = 1; i < h; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < w; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[h - 1][w - 1];
    }
}
