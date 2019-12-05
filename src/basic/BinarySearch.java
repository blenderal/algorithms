package basic;

/**
 * @description: 二分查找
 * @author: zww
 * @date: 2019/11/29
 * @version: V1.0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 7, 7, 7, 7, 9, 11, 27, 29};
        System.out.println(search3(nums, 30));
    }

    /**
     * 查找成功时平均查找长度(ASL)为4.14 查找失败时 平均查找长度为4.50 算法复杂度1.50logN
     * 算法优化斐波那契查找 分割点由系数0.5改为0.618
     *
     * @param nums
     * @param n
     * @return
     */
    public static int search1(int[] nums, int n) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (nums[mid] > n) {
                hi = mid;
            } else if (nums[mid] < n) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 优化向左向右的比较次数相同
     *
     * @param nums
     *
     * @param n
     * @return
     */
    public static int search2(int[] nums, int n) {
        int lo = 0;
        int hi = nums.length;
        while (1 < hi - lo) {
            int mid = (lo + hi) >> 1;
            if (n < nums[mid]) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return n == nums[lo] ? lo : 0;
    }

    /**
     * 符合以下语义：
     * 返回值为相同的最后一个值 且查找不成功时返回小于该值的最大值
     *
     * @param nums
     * @param n
     * @return
     */
    public static int search3(int[] nums, int n) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (n < nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return --lo;
    }
}
