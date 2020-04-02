package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 二叉树的层次遍历
 * @author: zww
 * @date: 2019/12/10
 * @version: V1.0
 */
public class LevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        treeNode.left = new TreeNode(2);
        System.out.println(solve1(null));
    }

    public static List<Integer> levelorder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return result;
    }

    public static List<List<Integer>> solve1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> levelResult = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                result.add(levelResult);
                levelResult = new ArrayList<>();
                if (!queue.isEmpty()) {
                    queue.offer(null);
                }
                continue;
            }
            levelResult.add(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return result;
    }
}
