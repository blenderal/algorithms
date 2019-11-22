/**
 * @description: 斐波那契数列
 * @author: zww
 * @date: 2019/11/20
 * @version: V1.0
 */
public class Fib {
    public static void main(String[] args) {
        System.out.println(dynamicProgramming(42));
        System.out.println(memoization(42));
        System.out.println(solve(42));
    }

    public static int solve(int num) {
        if (num <= 2) {
            return 1;
        }
        return solve(num - 1) + solve(num - 2);
    }

    /**
     * 备忘录法
     *
     * @param num
     * @return
     */
    public static int memoization(int num) {
        int[] m = new int[num + 1];
        m[1] = 1;
        m[2] = 1;
        if (num <= 2) {
            return m[num];
        }
        if (m[num] != 0) {
            return m[num];
        }
        int result = solve(num - 1) + solve(num - 2);
        m[num] = result;
        return result;
    }

    /**
     * 动态规划 自下而上
     *
     * @param num
     * @return
     */
    public static int dynamicProgramming(int num) {
//        int[] result = new int[num + 1];
//        result[1] = 1;
//        result[2] = 1;
//        for (int i = 3; i <= num; i++) {
//            result[i] = result[i - 1] + result[i - 2];
//        }
//        return result[num];
        if (num == 0) {
            return 0;
        }
        int f = 0;
        int g = 1;
        while (0 < --num) {
            g = f + g;
            f = g - f;
        }
        return g;
    }
}
