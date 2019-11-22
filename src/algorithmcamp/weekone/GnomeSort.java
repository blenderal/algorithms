package algorithmcamp.weekone;

/**
 * @description: 贪心算法
 * @author: zww
 * @date: 2019/10/21
 * @version: V1.0
 */
public class GnomeSort {
    public static void main(String[] args) {
//        int[] nums = new int[]{5, 1, 3, 9, 2, 7, 6, 8, 4};
        int[] nums = new int[]{5, 1, 3, 2, 4, 7, 8, 9};
//        gnomeSort(nums);
//        bubbleSort(nums);
//        bubbleSortPreBreak(nums);
        bubbleSortSkipSorted(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void gnomeSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void bubbleSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序优化 提前跳出循环
     * 情景：5 1 2 3 4 7 8 9
     * 循环两次即可
     */
    public static void bubbleSortPreBreak(int[] nums) {
        boolean isSorted = false;
        for (int i = nums.length - 1; i > 0; i--) {
            if (isSorted) {
                break;
            }
            isSorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    isSorted = false;
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序优化 跳过有序的
     * 情景 ：5 1 3 2 4 7 8 9 --> 1 3 2 4 5 7 8 9 --> 直接从7开始冒泡
     *
     * @param nums
     */
    public static void bubbleSortSkipSorted(int[] nums) {
        int lastSwapIndex;
        for (int i = nums.length - 1; i > 0; ) {
            lastSwapIndex = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    lastSwapIndex = j;
                    swap(nums, j, j + 1);
                }
            }
            i = lastSwapIndex;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
