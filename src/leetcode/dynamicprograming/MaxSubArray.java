package leetcode.dynamicprograming;

/**
 * @description: 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * @author: zww
 * @date: 2019/11/22
 * @version: V1.0
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] num = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solve(new int[]{1}));
    }

    /**
     * 动态规划
     * 数组[a,b,c,d,e,f]
     * 转换为求以下子序列和最大值
     * a a+b a+b+c a+b+c+d a+b+c+d+e a+b+c+d+e+f
     * b b+c b+c+d b+c+d+e b+c+d+e+f
     * c c+d c+d+e c+d+e+f
     * d d+e d+e+f
     * e e+f
     * f
     * 遍历数组
     * max(0):max(a);
     * max(1):max(a,a+b,b) = max(maxEnding(b),b,max(0));
     * max(2):max(a,a+b,b,a+b+c,b+c,c) = max(maxEnding(c),c,max(1)) = max(maxEnding(b) + c,c,max(1));
     *                      .
     *                      .
     *                      .
     * max(n):max(maxEnding(n-1) + num(n),num(n),max(n-1));
     *
     * 推导出动态规划函数：
     * max(n) = max(maxEnding(n),num(n),max(n-1))
     * maxEnding(n) = max(maxEnding(n-1)+num(n),num(n));
     *
     * @param num
     * @return
     */
    public static int solve(int[] num) {
        int maxEndingHere = num[0];
        int maxSoFar = num[0];
        for (int i = 1; i < num.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + num[i], num[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;

    }
}
