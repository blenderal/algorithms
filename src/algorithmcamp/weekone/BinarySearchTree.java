package algorithmcamp.weekone;

/**
 * @description: 构建二叉搜索树
 * @author: zww
 * @date: 2019/10/23
 * @version: V1.0
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 9, 3, 5, 7, 10, 8, 4, 1};
        Node root = null;
        for (int i = 0; i < nums.length; i++) {
            root = insert(nums[i], root);
        }
        dlr(root);
        lrd(root);
    }

    public static Node insert(int value, Node node) {
        if (node == null) {
            node = new Node();
            node.val = value;
            return node;
        }
        if (node.val > value) {
            node.left = insert(value, node.left);
        } else {
            node.right = insert(value, node.right);
        }
        return node;
    }

    /**
     * 前序遍历 根->左->右
     * @param root 根结点
     */
    public static void dlr(Node root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        dlr(root.left);
        dlr(root.right);
    }

    /**
     * 后序遍历 左->右->根
     * @param root 根结点
     */
    public static void lrd(Node root) {
        if(root == null){
            return;
        }
        lrd(root.left);
        lrd(root.right);
        System.out.println(root.val);
    }


    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
