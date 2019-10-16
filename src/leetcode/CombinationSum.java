package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * @description: 39. 组合总和
 * @author: zww
 * @date: 2019-10-16
 * @version: V1.0
 */
public class CombinationSum {
    private static List<List<Integer>> result = new ArrayList<>();
    private static int sum = 0;

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 7};
        combinationSum(candidates, 18);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0 || candidates[0] > target) {
            return result;
        }
        Arrays.sort(candidates);
//        dfs(new ArrayList<>(), 0, candidates, target, false);
        backtrack(new ArrayList<>(),target,0,candidates);
        return result;
    }


    public static void dfs(List<Integer> temp, int index, int[] candidates, int target, boolean isLast) {
        for (int i = index; i < candidates.length; i++) {
            if (target > sum + candidates[i]) {
                temp.add(candidates[i]);
                sum += candidates[i];
                dfs(temp, i, candidates, target, i == candidates.length - 1);
                if (isLast && !temp.isEmpty()) {
                    sum -= temp.get(temp.size() - 1);
                    temp.remove(temp.size() - 1);
                }
            } else if (target == sum + candidates[i]) {
                List<Integer> solve = new ArrayList<>(temp);
                solve.add(candidates[i]);
                result.add(solve);
                if (!temp.isEmpty()) {
                    sum -= temp.get(temp.size() - 1);
                    temp.remove(temp.size() - 1);
                }
                if (isLast && !temp.isEmpty()) {
                    sum -= temp.get(temp.size() - 1);
                    temp.remove(temp.size() - 1);
                }
                break;
            } else {
                if (!temp.isEmpty()) {
                    sum -= temp.get(temp.size() - 1);
                    temp.remove(temp.size() - 1);
                }
                if (isLast && !temp.isEmpty()) {
                    sum -= temp.get(temp.size() - 1);
                    temp.remove(temp.size() - 1);
                }
                break;
            }
        }
    }

    private static void backtrack(List<Integer> temp, int target, int index, int[] candidates) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target < candidates[i]) {
                break;
            }
            temp.add(candidates[i]);
            backtrack(temp, target - candidates[i], i, candidates);
            temp.remove(temp.size() - 1);
        }
    }
}
