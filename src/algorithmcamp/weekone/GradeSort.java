package algorithmcamp.weekone;

/**
 * @description: 问题描述
 * 有 n 名学生，它们的学号分别是 1,2,…,n。这些学生都选修了邓老师的算法训练营、数据结构训练营这两门课程。
 * <p>
 * 学期结束了，所有学生的课程总评都已公布，所有总评分数都是 [0,100] 之间的整数。巧合的是，不存在两位同学，他们这两门课的成绩都完全相同。
 * <p>
 * 邓老师希望将这些所有的学生按这两门课程的总分进行降序排序，特别地，如果两位同学的总分相同，那邓老师希望把算法训练营得分更高的同学排在前面。由于上面提到的巧合，所以，这样排名就可以保证没有并列的同学了。
 * <p>
 * 邓老师将这个排序任务交给了他的助教。可是粗心的助教没有理解邓老师的要求，将所有学生按学号进行了排序。
 * <p>
 * 邓老师希望知道，助教的排序结果中，存在多少逆序对。
 * <p>
 * 如果对于两个学生 (i,j) 而言，i 被排在了 j 前面，并且i 本应被排在 j 的后面，我们就称 (i,j) 是一个逆序对。
 * <p>
 * 请你先帮邓老师把所有学生按正确的顺序进行排名，再告诉他助教的错误排名中逆序对的数目。
 * @author: zww
 * @date: 2019/11/13
 * @version: V1.0
 */
public class GradeSort {
    public static void main(String[] args) {
        int[][] grade = new int[][]{
                {90, 91},
                {90, 93},
                {90, 92},
                {90, 90},
                {89, 90},
                {90, 89},
        };
        System.out.println(solve(grade));
        for (int i = 0; i < grade.length; i++) {
            System.out.println(grade[i][0] + " " + grade[i][1]);
        }

    }

    /**
     * 冒泡排序 交换次数即逆序对数量
     *
     * @param grade
     * @return
     */
    public static int solve(int[][] grade) {
        int result = 0;
        int lastSwapIndex;
        for (int i = grade.length - 1; i > 0; ) {
            lastSwapIndex = 0;
            for (int j = 0; j < i; j++) {
                boolean needSwap = (grade[j][0] + grade[j][1] < grade[j + 1][0] + grade[j + 1][1])
                        || (grade[j][0] + grade[j][1] == grade[j + 1][0] + grade[j + 1][1] && grade[j][0] < grade[j + 1][0]);
                if (needSwap) {
                    swap(grade, j, j + 1);
                    result++;
                    lastSwapIndex = j;
                }

            }
            i = lastSwapIndex;
        }
        return result;
    }

    private static void swap(int[][] grade, int i, int j) {
        int[] temp = grade[i];
        grade[i] = grade[j];
        grade[j] = temp;
    }
}
