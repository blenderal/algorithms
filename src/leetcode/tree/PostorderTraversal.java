package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 二叉树的后序遍历
 * @author: zww
 * @date: 2019/12/6
 * @version: V1.0
 */
public class PostorderTraversal {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        treeNode.left = new TreeNode(2);
        System.out.println(solve0(treeNode));
    }

    public static List<Integer> solve0(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            if (stack.peek().left != root && stack.peek().right != root) {
                gotoHLVFL(stack);
            }
            root = stack.pop();
            result.add(root.val);
        }
        return result;
    }

    public static void gotoHLVFL(Stack<TreeNode> stack) {
        while (stack.peek() != null) {
            TreeNode current = stack.peek();
            if (current.left != null) {
                if (current.right != null) {
                    stack.push(current.right);
                }
                stack.push(current.left);
            } else {
                stack.push(current.right);
            }
        }
        stack.pop();
    }

    /**
     * 递归的方法
     *
     * @param root
     * @return
     */
    public static List<Integer> solve1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(result, root);
        return result;

    }

    public static void traversal(List<Integer> result, TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        traversal(result, treeNode.left);
        traversal(result, treeNode.right);
        result.add(treeNode.val);
    }
}
