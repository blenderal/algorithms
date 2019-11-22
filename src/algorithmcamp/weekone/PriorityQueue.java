package algorithmcamp.weekone;

import java.util.Queue;

/**
 * @description: 问题描述
 * 有一篇文章，文章包含 n种单词，单词的编号从 1 至 n，第 i 种单词的出现次数为 w[i]。
 * <p>
 * 现在，我们要用一个 2 进制串（即只包含 0 或 1 的串） s[i] 来替换第 i 种单词，使其满足如下要求：对于任意的 1≤i,j≤n（i≤j），都有 s[i] 不是 s[j] 的前缀。（这个要求是为了避免二义性）
 * <p>
 * 你的任务是对每个单词选择合适的 s[i]，使得替换后的文章总长度（定义为所有单词出现次数与替换它的二进制串的长度乘积的总和）最小。求这个最小长度。
 * <p>
 * 字符串 S1（不妨假设长度为 n）被称为字符串 S2 的前缀，当且仅当：S2 的长度不小于 n，且 S1 与 S2 前 n 个字符组组成的字符串完全相同。
 * @author: zww
 * @date: 2019/10/24
 * @version: V1.0
 */
public class PriorityQueue {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 3, 6, 7, 8, 2, 9};
        System.out.println(solve(nums));
    }

    /**
     * 构造哈夫曼树 最小长度 = sum(叶子结点*树深) = a*3+b*3+c*2+d*1 = 累计项相加 = (a+b)+(a+b+c)+(a+b+c+d)
     *      a+b+c+d
     *       /   \
     *    a+b+c  d
     *    /  \
     *  a+b   c
     *  / \
     * a   b
     * @param nums
     * @return
     */
    public static int solve(int[] nums) {
        Queue<Integer> pq = new java.util.PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        int result = 0;
        while (pq.size() > 1) {
            int temp = pq.poll() + pq.poll();
            result += temp;
            pq.add(temp);
        }
        return result;
    }
}
