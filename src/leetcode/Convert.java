package leetcode;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * @description: 6. Z 字形变换
 * @author: zww
 * @date: 2019-10-18
 * @version: V1.0
 */
public class Convert {
    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        String s1 = "AB";
        System.out.println(convert(s1, 1));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        int n = 1;
        int k = 0;
        while (result.length() < s.length()) {
            if (n == 1 || n == numRows) {
                if ((2 * numRows - 2) * k > s.length() - 1) {
                    n += 1;
                    k = 0;
                    continue;
                }
                result.append(s.charAt((2 * numRows - 2) * k + (n - 1)));
            } else {
                if (k % 2 == 0) {
                    if ((2 * numRows - 2) * (k / 2) + (n - 1) > s.length() - 1) {
                        n += 1;
                        k = 0;
                        continue;
                    }
                    result.append(s.charAt((2 * numRows - 2) * (k / 2) + (n - 1)));
                } else {
                    if ((2 * numRows - 2) * (k + 1) / 2 - (n - 1) > s.length() - 1) {
                        n += 1;
                        k = 0;
                        continue;
                    }
                    result.append(s.charAt((2 * numRows - 2) * (k + 1) / 2 - (n - 1)));
                }
            }
            k += 1;
        }

        return result.toString();
    }
}
