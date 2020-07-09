package leetcode;

import java.util.Arrays;

/**
 * @description:
 * 976. 三角形的最大周长
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 *
 * 如果不能形成任何面积不为零的三角形，返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[2,1,2]
 * 输出：5
 * 示例 2：
 *
 * 输入：[1,2,1]
 * 输出：0
 * 示例 3：
 *
 * 输入：[3,2,3,4]
 * 输出：10
 * 示例 4：
 *
 * 输入：[3,6,2,3]
 * 输出：8
 *
 *
 * 提示：
 *
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 * @author: zww
 * @date: 2020/7/6
 * @version: V1.0
 */
public class LargestPerimeter {
    public static void main(String[] args) {
        int[] A = new int[]{2,1,2};
        System.out.println(largestPerimeter(A));
    }

    public static int largestPerimeter(int[] A) {
        // 不失一般性的，我们假设三角形的边长满足 a <= b <= c
        // 那么这三条边组成三角形的面积非零的充分必要条件是 a + b > c
        Arrays.sort(A);
        for (int i = A.length - 1; i > 1; i--) {
            if (A[i - 1] + A[i - 2] > A[i]) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }
}
