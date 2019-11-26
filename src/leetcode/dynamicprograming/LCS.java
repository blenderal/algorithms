package leetcode.dynamicprograming;

/**
 * @description: 最长公共子序列
 * @author: zww
 * @date: 2019/11/21
 * @version: V1.0
 */
public class LCS {
    public static void main(String[] args) {
        System.out.println(solve("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));
        System.out.println(solveString("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));
        System.out.println(dynamicProgramming("advantage", "didactical"));
        System.out.println(dynamicProgramming("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));
        System.out.println(dynamicProgrammingBetter("advantage", "didactical"));

    }

    public static int solve(String a, String b) {
        if (a.length() == 0 || b.length() == 0) {
            return 0;
        }
        if (a.charAt(a.length() - 1) == b.charAt(b.length() - 1)) {
            return 1 + solve(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1));
        }
        return Math.max(solve(a, b.substring(0, b.length() - 1)), solve(a.substring(0, a.length() - 1), b));
    }

    public static String solveString(String a, String b) {
        if (a.length() == 0 || b.length() == 0) {
            return "";
        }
        if (a.charAt(a.length() - 1) == b.charAt(b.length() - 1)) {
            return solveString(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1)) + a.charAt(a.length() - 1);
        }
        String result1 = solveString(a, b.substring(0, b.length() - 1));
        String result2 = solveString(a.substring(0, a.length() - 1), b);
        return result1.length() > result2.length() ? result1 : result2;
    }

    /**
     * 动态规划
     * 自下而上 两字母相同时由左上角数值加1 两字母不同时选择上方或左方最大值
     *     d i d a c t i c a l
     *   0 0 0 0 0 0 0 0 0 0 0
     * a 0 0 0 0 1 1 1 1 1 1 1
     * d 0 1 1 1 1 1 1 1 1 1 1
     * v 0 1 1 1 1 1 1 1 1 1 1
     * a 0 1 1 1 2 2 2 2 2 2 2
     * n 0 1 1 1 2 2 2 2 2 2 2
     * t 0 1 1 1 2 2 3 3 3 3 3
     * a 0 1 1 1 2 2 3 3 3 4 4
     * g 0 1 1 1 2 2 3 3 3 4 4
     * e 0 1 1 1 2 2 3 3 3 4 4
     *
     * @param a 字符串A
     * @param b 字符串B
     * @return 最长公共子序列
     */
    public static int dynamicProgramming(String a, String b) {
        int[][] table = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }
//        System.out.print("    ");
//        for(int i = 0; i < b.length(); i++){
//            System.out.print(b.charAt(i)+" ");
//        }
//        System.out.println();
//        for (int i = 0; i <= a.length(); i++) {
//            if(i==0){
//                System.out.print("  ");
//            }
//            if(i>0){
//                System.out.print(a.charAt(i-1)+" ");
//            }
//            for (int j = 0; j <= b.length(); j++) {
//                System.out.print(table[i][j]+" ");
//            }
//            System.out.println();
//        }
        return table[a.length()][b.length()];
    }

    /**
     * 压缩存储空间动态规划 二维数组的只有最后一行有用
     * @param a 字符串A
     * @param b 字符串B
     * @return 最长公共子序列
     */
    public static int dynamicProgrammingBetter(String a, String b) {
        int h = a.length();
        int w = b.length();
        int[] array = new int[w + 1];
        int temp;
        for (int i = 0; i < h; i++) {
            int corner = 0;
            for (int j = 1; j <= w; j++) {
                temp = array[j];
                if (a.charAt(i) == b.charAt(j - 1)) {
                    array[j] = corner + 1;
                } else {
                    array[j] = Math.max(array[j - 1], array[j]);
                }
                corner = temp;
            }
        }
        return array[w];
    }
}
