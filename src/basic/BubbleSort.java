package basic;

/**
 * @description: 冒泡排序
 * @author: zww
 * @date: 2019/12/2
 * @version: V1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = new int[]{6, 1, 3, 6, 8, 0, 3, 2, 7, 5};
        sort2(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void sort0(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j + 1] < nums[j]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void sort1(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            boolean isSorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j + 1] < nums[j]) {
                    isSorted = false;
                    swap(nums, j + 1, j);
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    public static void sort2(int[] nums) {
        for (int i = nums.length - 1; i > 0; ) {
            int lastSwapIndex = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j + 1] < nums[j]) {
                    lastSwapIndex = j;
                    swap(nums, j, j + 1);
                }
            }
            i = lastSwapIndex;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
