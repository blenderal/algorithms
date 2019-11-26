package leetcode.dynamicprograming;

/**
 * @description:
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *   0 1 2 3 4 5
 * 0 s
 * 1     1
 * 2           e
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * @author: zww
 * @date: 2019/11/26
 * @version: V1.0
 */
public class UniquePathsWithObstacles {
    public static void main(String[] args) {
        int[][] obstacleGrid =new int[][]{
                {0,1,0},{0,1,0},{0,0,0}
        };
        System.out.println(solve(obstacleGrid));
    }

    /**
     * 动态规划
     * 先初始化第一行与第一列
     * 然后遍历其他方格
     * 如果没有阻挡 方格的值等于左边与其上方的值之和
     * 如果有阻挡 方格的值等于0
     * 参考: {@link leetcode.dynamicprograming.UniquePaths}
     * @param obstacleGrid
     * @return
     */
    public static int solve(int[][] obstacleGrid) {
        int h = obstacleGrid.length;
        int w = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        } else {
            obstacleGrid[0][0] = 1;
        }
        for (int i = 1; i < h; i++) {
            obstacleGrid[i][0] = obstacleGrid[i][0] == 1 ? 0 : obstacleGrid[i - 1][0];
        }
        for (int i = 1; i < w; i++) {
            obstacleGrid[0][i] = obstacleGrid[0][i] == 1 ? 0 : obstacleGrid[0][i - 1];
        }
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                obstacleGrid[i][j] = obstacleGrid[i][j] == 1 ? 0 : obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }
        return obstacleGrid[h - 1][w - 1];
    }
}
