package algorithmcamp.weekone;

import java.util.Arrays;

/**
 * @description: 哈夫曼编码树
 * @author: zww
 * @date: 2019/10/21
 * @version: V1.0
 */
public class HuffmanTree {
    public static void main(String[] args) {


    }
    public Node huffmanTree(int[] nums){
        Arrays.sort(nums);

        return null;
    }


    public static class Node {
        private int val;
        private Node left;
        private Node right;
        private char letter;

        public Node() {
        }

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

        public char getLetter() {
            return letter;
        }

        public void setLetter(char letter) {
            this.letter = letter;
        }
    }
}
