package leetcode.dynamicprograming;

/**
 * @description: 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * @author: zww
 * @date: 2019/11/28
 * @version: V1.0
 */
public class NumDecodings {
    public static void main(String[] args) {
        System.out.println(solve("10"));
    }

    /**
     * 动态规划
     * 设dp[i]为前i个字符串的解码方法种数
     * 当s.charAt(i) != '0' && s.charAt(i-1)!=0 时
     *                dp[i-1] 当第i和第i-1数字大于26时
     *              /
     *      dp[i]
     *              \
     *                dp[i-1] + dp[i-1]; 当第i和第i-1数字小于26时
     * 当s.charAt(i) == '0'时
     *      dp[i] = dp[i-2] 第i-1数字小于3时
     *      总字符串无解码方式          其他情况
     *
     * @param s
     * @return
     */
    public static int solve(String s) {
        s = "0" + s + "#";
        int l = s.length() - 1;
        int previous = 1;
        int previousTwo = 0;
        int result = 0;
        for (int i = 1; i < l; i++) {
            char now = s.charAt(i);
            char pre = s.charAt(i - 1);
            if (now == '0') {
                if (pre == '0' || pre > '2' || s.charAt(i + 1) == '0') {
                    return 0;
                }
                result = previousTwo;
                previous = result;
                i++;
                continue;
            }
            if ((pre < '2') || ((now < '7') && ('2' == pre))) {
                result = previous + previousTwo;
                previousTwo = previous;
                previous = result;
            } else {
                result = previous;
                previousTwo = previous;
            }
        }
        return result;
    }
}
