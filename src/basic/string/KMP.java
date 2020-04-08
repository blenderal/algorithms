package basic.string;

/**
 * @description: KMP算法
 * @author: zww
 * @date: 2020-03-04
 * @version: V1.0
 */
public class KMP {
    public static void main(String[] args) {
        System.out.println(match("001", "0000010001"));
    }

    public static int match(String pattern, String text) {
        char[] p = pattern.toCharArray();
        char[] t = text.toCharArray();
        int m = p.length;
        int n = t.length;
        int[] next = buildNext(p);
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (j < 0 || p[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return i - j;
    }

    /**
     * 构造next表
     *
     * @param p 匹配串
     * @return next表
     */
    public static int[] buildNext(char[] p) {
        int m = p.length;
        int j = 0;
        int[] N = new int[m];
        int t = N[0] = -1;
        while (j < m - 1) {
            if (t < 0 || p[j] == p[t]) {
                j++;
                t++;
                N[j] = p[j] != p[t] ? t : N[t];
            } else {
                t = N[t];
            }
        }
        return N;
    }
}
