/**
 * @description: 求数组中最大的两个数
 * @author: zww
 * @date: 2019/11/19
 * @version: V1.0
 */
public class Max2 {
    public static void main(String[] args) {
        int[] num = new int[]{0,9,4,6,3,2,7,1,5};
        int[] result = max2(num,0,num.length-1);
        System.out.println(num[result[0]]+" "+num[result[1]]);
    }

    /**
     * 分而治之
     * @param num 数组
     * @param lo 最小位
     * @param hi 最大位
     * @return 最大的两个数
     */
    public static int[] max2(int[] num, int lo, int hi){
        int[] result = new int[2];
        if(lo>=hi-1){
            result[0] = num[hi]>num[lo]?hi:lo;
            result[1] = num[hi]>num[lo]?lo:hi;
            return result;
        }
        int[] left = max2(num,lo,(lo+hi)/2);
        int[] right = max2(num,(lo+hi)/2+1,hi);
        if(num[left[0]]>num[right[0]]){
            result[0]=left[0];
            result[1] = num[right[0]] > num[left[1]] ? right[0] : left[1];
        }else{
            result[0]=right[0];
            result[1] = num[left[0]] > num[right[1]] ? left[0] : right[1];
        }
        return result;
    }
}
