package basic;

/**
 * @description: 归并排序
 * @author: zww
 * @date: 2019/12/2
 * @version: V1.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{6, 1, 3, Integer.MAX_VALUE, 8, 0, 3, 2, 7, 5};
        solve(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void solve(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = (lo + hi) >> 1;
        solve(nums, lo, mid);
        solve(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    public static void merge(int[] nums, int lo, int mid, int hi) {
        int ll = mid - lo + 1;
        int lr = hi - mid;
        int[] left = new int[ll];
        for (int i = lo; i <= mid; i++) {
            left[i - lo] = nums[i];
        }

        int[] right = new int[lr];
        for (int i = mid + 1; i <= hi; i++) {
            right[i - mid - 1] = nums[i];
        }
        int l = 0;
        int r = 0;
        int now = lo;
        while (l < ll && r < lr) {
            if (left[l] < right[r]) {
                nums[now++] = left[l++];
            } else {
                nums[now++] = right[r++];
            }
        }
        while (l < ll) {
            nums[now++] = left[l++];
        }
        while (r < lr) {
            nums[now++] = right[r++];
        }
    }
}
