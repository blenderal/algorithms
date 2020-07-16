package leetcode.Array;

import java.util.Arrays;

/**
 * @description:
 * 274. H 指数
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 *
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数 不超过 h 次。）
 *
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 *
 *
 *
 * 示例：
 *
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 *
 *
 * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 *
 * @author: zww
 * @date: 2020/7/14
 * @version: V1.0
 */
public class HIndex {
    public static void main(String[] args) {
//        int[] citations = new int[]{3,0,6,1,5};
        int[] citations = new int[]{0,1,1};
        System.out.println(hIndex(citations));
    }

    /**
     * 排序法
     * @param citations
     * @return
     */
    public static int hIndexByOrder(int[] citations) {
        Arrays.sort(citations);
        int l = citations.length;
        for (int i = 0; i < l; i++) {
            if(citations[i]>=l-i){
                return l-i;
            }
        }
        return 0;
    }

    /**
     * 计数排序法
     * citations = [3,0,6,1,5]
     * count = [1,0,1,0,2]
     * paper = [4,3,3,2,2] 统计引用次数大于i的论文数
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations){
        int l = citations.length;
        int[] count = new int[l + 1];
        // 统计l范围内饮用次数的出现次数
        for (int citation : citations) {
            count[Math.min(l, citation)]++;
        }
        int c = 0;
        // 从统计数组末端往回走 统计引用次数大于i的论文数
        for (int i = l; i > 0; i--) {
            count[i] = count[i] + c;
            c = count[i];
            if (i <= count[i]) {
                return i;
            }
        }
        return 0;
    }
}
