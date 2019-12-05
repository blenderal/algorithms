package basic;

/**
 * @description: 插入排序
 * @author: zww
 * @date: 2019/12/3
 * @version: V1.0
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] nums = new int[]{8, 4, 2, 7, 5, 3, 1, 6, 9};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    /**
     * 插入排序
     * 算法复杂度
     * 最坏情况下逆序对数量为N^2 O(N^2+N) = O(N^2)
     * 最好情况下逆序对数量为0 比较次数为N 算法复杂度为O(0+N) = O(N)
     * @param nums
     */
    public static void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            while (j >= 0 && temp < nums[j]) {
                nums[j + 1] = nums[j--];
            }
            nums[j + 1] = temp;
        }
    }
}
