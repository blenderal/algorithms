package leetcode.dynamicprograming;

/**
 * @description: 72. 编辑距离
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * @author: zww
 * @date: 2019/11/26
 * @version: V1.0
 */
public class MinDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("intention", "execution"));
    }

    /**
     * 动态规划
     * word1 word2
     * dp[i][j]表示单词1前i个字符和单词2前j个字符的编辑距离
     *
     * 当word1的第i个单词和word2的第j个单词不同时
     * 即word1.charAt(i) != word2.charAt(j)
     * 我们可以得出
     * dp[i][j] = 1 + min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1])
     *          = min(
     *          dp[i][j-1]+替换word1的i处字符串为word2的j处字符串,
     *          dp[i-1][j]+替换word1的i处字符串为word2的j处字符串,
     *          dp[i-1][j-1]+替换word1的i处字符串为word2的j处字符串
     *          )
     * 同理可得 当word1.charAt(i) == word2.charAt(j)时
     * dp[i][j] = 1 + min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1])
     *          = min(
     *          dp[i][j-1]+替换word1的i处字符串为word2的j处字符串,
     *          dp[i-1][j]+替换word1的i处字符串为word2的j处字符串,
     *          dp[i-1][j-1]
     *          )
     * 同样
     * dp[0][j] = j;
     * dp[i][0] = i;
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int h = word1.length() + 1;
        int w = word2.length() + 1;
        int[][] table = new int[h][w];
        for (int i = 0; i < h; i++) {
            table[i][0] = i;
        }
        for (int i = 0; i < w; i++) {
            table[0][i] = i;
        }
        int temp;
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    temp = Math.min(table[i][j - 1], table[i - 1][j]);
                    table[i][j] = 1 + Math.min(temp, table[i - 1][j - 1] - 1);
                } else {
                    temp = Math.min(table[i][j - 1], table[i - 1][j]);
                    table[i][j] = 1 + Math.min(temp, table[i - 1][j - 1]);
                }
            }
        }
        return table[h-1][w-1];
    }
}
