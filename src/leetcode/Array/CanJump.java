package leetcode.Array;

/**
 * @description: 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * @author: zww
 * @date: 2020/7/15
 * @version: V1.0
 */
public class CanJump {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,0,4};
        System.out.println(greedy(nums));
    }


    public static boolean canJump(int[] nums) {
        int index = -1;
        for (int i = nums.length - 1; i != -1; i--) {
            // 等于0 且该位置之后的0都能成功跳过 则将该位置置为未能跳过的0
            if (nums[i] == 0 && index == -1) {
                index = i;
                continue;
            }
            // 该位置不为0 且该位置后有0且不能跳过 并且可通过该位置跳至不能跳过的0处
            if (index != -1 && nums[i] >= index - i) {
                // 若该位置能跳的步数刚好能跳至不能跳过的0的位置 且不能跳过的0不是末尾 则依然未能解决该位置不能跳过的0
                if (nums[i] == index - i && index != nums.length - 1) {
                    continue;
                }
                index = -1;
            }
        }
        return index == -1 || nums.length == 1;
    }

    /**
     * 贪心法
     * @param nums
     * @return
     */
    public static boolean greedy(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                max = Math.max(nums[i] + i, max);
            }else {
                return false;
            }
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
