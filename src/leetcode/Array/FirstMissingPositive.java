package leetcode.Array;

/**
 * @description:  41. 缺失的第一个正数
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 *
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 * @author: zww
 * @date: 2020/7/9
 * @version: V1.0
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1};
        System.out.println(firstMissingPositive(nums));
    }

    /**
     * 原地哈希法
     * 若数组中存在缺失的正整数 则缺失的正整数必定存在于[1-nums.length+1]中
     * 则可以通过对数组中每个在[1-nums.length]中的数所对应的下标对应位置做标记
     * 故第一次循环可以将数组中小于1的值全部转化为length+1
     * 至此数组中的所有数都为正数
     * 第二次循环对在[1-nums.length]中的数 并对在以此数-1为下标的数组位置中的数添加负号 标记该数已存在
     * 第三次循环 数组第一个正数 则表明数组中第一个缺失的正整数为对应下标+1的数 若无正数 第一个缺失的正整数为nums.length+1
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            if (nums[i] < 1) {
                nums[i] = l + 1;
            }
        }
        for (int i = 0; i < l; i++) {
            int num = Math.abs(nums[i]);
            if (num <= l && nums[num - 1] > 0) {
                nums[num - 1] = -nums[num - 1];
            }
        }
        for (int i = 0; i < l; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return l + 1;
    }
}
