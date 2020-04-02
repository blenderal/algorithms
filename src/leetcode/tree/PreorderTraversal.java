package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 二叉树的先序遍历
 * @author: zww
 * @date: 2019/12/6
 * @version: V1.0
 */
public class PreorderTraversal {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        treeNode.left = new TreeNode(2);
        System.out.println(preorder(treeNode));
    }

    /**
     * 运用栈的方式以迭代方式取代递归 前序中序后续遍历通用
     *
     * @param root
     * @return
     */
    public static List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            visitAlongLeftBranch(result, stack);
        }
        return result;
    }

    public static void visitAlongLeftBranch(List<Integer> result, Stack<TreeNode> stack) {
        TreeNode current = stack.pop();
        while (current != null) {
            result.add(current.val);
            stack.push(current.right);
            current = current.left;
        }
    }

    /**
     * 运用栈的方式以迭代方式取代递归
     *
     * @param root
     * @return
     */
    public static List<Integer> solve1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return result;
    }

    /**
     * 递归的方法
     *
     * @param root
     * @return
     */
    public static List<Integer> solve2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private static void traversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traversal(root.left, result);
        traversal(root.right, result);
    }


}
