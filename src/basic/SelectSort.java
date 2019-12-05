package basic;

/**
 * @description: 选择排序
 * @author: zww
 * @date: 2019/12/3
 * @version: V1.0
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] nums = new int[]{8, 4, 2, 7, 5, 3, 1, 6, 9};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    /**
     * 冒泡排序也是选择排序的一种思想 冒泡排序花在交换的时间上很多
     * {@link basic.BubbleSort}
     * @param nums
     */
    public static void sort(int[] nums) {
        for (int i = nums.length; i > 0; ) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[max]) {
                    max = j;
                }
            }
            swap(nums, max, --i);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
