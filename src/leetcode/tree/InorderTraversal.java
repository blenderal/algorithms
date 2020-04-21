package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 中序遍历
 * @author: zww
 * @date: 2019/12/6
 * @version: V1.0
 */
public class InorderTraversal {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        treeNode.left = new TreeNode(2);
        System.out.println(inorder(treeNode));
    }


    public static List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (true) {
            goAlongLeftBranch(current, stack);
            if (stack.isEmpty()) {
                break;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    public static void goAlongLeftBranch(TreeNode treeNode, Stack<TreeNode> stack) {
        while (treeNode != null) {
            stack.push(treeNode);
            treeNode = treeNode.left;
        }

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
        result.add(treeNode.val);
        traversal(result, treeNode.left);
    }

}
