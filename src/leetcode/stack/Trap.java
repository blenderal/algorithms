package leetcode.stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 *
 * @description: 42. 接雨水
 * @author: zww
 * @date: 2019-07-16
 * @version: V1.0
 */
public class Trap {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap1(height));
    }

    public static int trap1(int[] height) {
        if (height.length == 0 || height.length == 1 || height.length == 2) {
            return 0;
        }
        int maxIndex = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > height[maxIndex]) {
                maxIndex = i;
            }
        }
        int v = 0;
        int maxBefore = height[0];
        for (int i = 0; i < maxIndex; i++) {
            if (height[i] < maxBefore) {
                v += maxBefore - height[i];
            } else {
                maxBefore = height[i];
            }
        }
        maxBefore = height[height.length - 1];
        for (int i = height.length - 1; i > maxIndex; i--) {
            if (height[i] < maxBefore) {
                v += maxBefore - height[i];
            } else {
                maxBefore = height[i];
            }
        }
        return v;
    }
}
