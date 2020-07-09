package leetcode;

/**
 * @description:
 * 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * @author: zww
 * @date: 2020/7/7
 * @version: V1.0
 */
public class SortedArrayToBST {
    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode node = sortedArrayToBST(nums);
        System.out.println("end");
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int r = nums.length - 1;
        int l = 0;
        return growUp(nums, l, r);
    }

    public static TreeNode growUp(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        TreeNode p = new TreeNode(nums[mid]);
        p.left = growUp(nums, l, mid - 1);
        p.right = growUp(nums, mid + 1, r);
        return p;
    }
}
