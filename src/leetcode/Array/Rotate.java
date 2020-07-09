package leetcode.Array;

/**
 * @description:
 * 189. 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * @author: zww
 * @date: 2020/7/6
 * @version: V1.0
 */
public class Rotate {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,-100,3,99};
        rotate(nums,2);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }

    }
    /**
     * 环状替代
     * 从第0个同学开始 找到他该坐的位置 并将座位上的同学挤出到角落 角落的同学继续找到自己该坐的位置 如此反复 直到所有的同学都换过一次座位
     * 当 n 和 k 的最大公约数 等于 1 的时候：1 次遍历就可以完成交换；比如 n = 5, k = 3n=5,k=3
     * 当 n 和 k 的最大公约数 不等于 1 的时候：1 次遍历是无法完成的所有元素归位，需要 m (最大公约数) 次
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int l = nums.length;
        int a = k % l;
        // 记录交换位置的次数，n个同学一共需要换n次
        int count = 0;
        for (int i = 0; count < l; i++) {
            // 从0位置开始换位子
            int curr = i;
            int pre = nums[curr];
            int temp ;
            do {
                curr = (curr + a) % l;
                // 被挤出的同学来到角落 等待下一轮入座
                temp = nums[curr];
                nums[curr] = pre;
                pre = temp;
                count++;
                // 角落的人回到空位，循环暂停
            } while (curr != i);
        }
    }
}
