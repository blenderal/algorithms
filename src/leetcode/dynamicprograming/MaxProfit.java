package leetcode.dynamicprograming;

/**
 * @description: 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * @author: zww
 * @date: 2019/11/25
 * @version: V1.0
 */
public class MaxProfit {
    public static void main(String[] args) {
        int[] price = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(solve(price));
    }

    /**
     * 动态规划 参考:{@link leetcode.dynamicprograming.MaxSubArray}
     * 数组[a,b,c,d,e]
     * 组合穷举
     *
     * ab ac ad  ae
     * bc bd be
     * cd ce
     * de
     *
     * 遍历数组
     * max(1): ab
     * max(2): max(bc,ac,ab) = max(maxEnding(c),ab)=max(maxEnding(b)+c-b,0,c-b,max(1));
     * max(3): max(bc,ac,ab,cd,bd,ad) = max(maxEnding(d),bc,ac,ab) = max(maxEnding(c)+d-c,0,d-c,max(2));
     *                                  .
     *                                  .
     *                                  .
     * max(n): max(maxEnding(num(n-1))+num(n)-num(n-1),0,num(n)-num(n-1),max(n-1));
     *
     * @param price
     * @return
     */
    public static int solve(int[] price) {
        int maxEndingHere = 0;
        int maxSoFar = 0;
        int maxHere;
        for (int i = 1; i < price.length; i++) {
            maxHere = Math.max(0, price[i] - price[i - 1]);
            maxEndingHere = Math.max(maxEndingHere + (price[i] - price[i - 1]), maxHere);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}
