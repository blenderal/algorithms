package leetcode.dynamicprograming;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @description: 5. 最长回文子串  (manacher 算法 时间复杂度O(N))
 * @author: zww
 * @date: 2019-04-04
 * @version: V1.0
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "bbaaaaaaaaaaaabbbbbbaaaaaaa";
        String s1 = "baaaaaamabamaaaaaaam";
        String s3 = "cababababababa";
        String s4 = "cbcbcbc";
        String s5 = "akhfdkhfdslijfoisdjilsdlksjjsdlkfjklsdfjlsjflsjfslslk";
        String s6 = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(longestPalindrome(s));
        System.out.println(manacher(s));
    }

    /**
     * 动态规划 算法复杂度 O(N^2)
     * P(i,j) = (P(i+1,j−1) and Si==Sj)
     * ​P(i,i) = true
     * P(i, i+1) = ( Si == Si+1 )
     *
     * 示例：
     * 字符串 "message"
     * P 0 1 2 3 4 5 6 7
     * 0 T F
     * 1   T F
     * 2     T T
     * 3       T F
     * 4         T F
     * 5           T F
     * 6             T F
     * 7               T
     * 先将单个字符和相邻字符是否是回文串解出来
     * 然后依据 P(i,j) = (P(i+1,j−1) and Si==Sj) P(i, i+1) = ( Si == Si+1 )依此判断相邻3、4、5... 字符串是否是回文串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String result = "";
        int n = s.length();
        boolean[][] memory = new boolean[n][n];
        for (int count = 0; count < n; count++) {
            for (int i = n - 1; i - count >= 0; i--) {
                if (count == 0) {
                    memory[i][i] = true;
                } else if (count == 1) {
                    memory[i - 1][i] = s.charAt(i) == s.charAt(i - 1);
                } else {
                    memory[i - count][i] = s.charAt(i) == s.charAt(i - count) && memory[i - count + 1][i - 1];
                }
                if (memory[i - count][i] && result.length() < count + 1) {
                    result = s.substring(i - count, i + 1);
                }
            }
        }
        return result;
    }

    /**
     * 马拉车算法
     * @param s
     * @return
     */
    public static String manacher(String s) {
        if (s.isEmpty()) {
            return "";
        }
        if (s.replaceAll(String.valueOf(s.charAt(0)), "").length() == 0) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            stringBuilder.append("#");
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append("#");
        int[] r = new int[stringBuilder.length()];
        int c = -1;
        int maxRight = -1;
        int maxIndex = 0;
        for (int i = 0; i < stringBuilder.length(); i++) {
            r[i] = i < maxRight ? Math.min(r[2 * c - i], maxRight - i) : 1;
            while (i + r[i] < stringBuilder.length() && i - r[i] > -1) {
                if (stringBuilder.charAt(i + r[i]) == stringBuilder.charAt(i - r[i])) {
                    r[i]++;
                } else {
                    break;
                }
            }
            if (r[i] + i > maxRight) {
                maxRight = r[i] + i;
                c = i;
            }
            maxIndex = r[i] > r[maxIndex] ? i : maxIndex;
        }
        return stringBuilder.substring(maxIndex - r[maxIndex] + 1, maxIndex + r[maxIndex] - 1).replaceAll("#", "");

    }
}
