package cn.divideAndconquer;

/**
 * 案例：
 * 求解连续最大子数组的和
 * Created by gaojianqun on 2018/7/31.
 */
public class findMaximumSubarray {


    /**
     * 求解最大子数组的值
     * @param array
     * @param low
     * @param mid
     * @param high
     * @return
     */
    public static int findMaxCrossingSubArray(int[] array,int low,int mid,int high){
        //计算子数组左侧的最大值
        int leftSum = 0;
        int sum1 = 0;
        int maxLeft = 0;
        for(int i = 0;mid > low;mid--){
            sum1 = sum1 + array[i];
            if(sum1 > leftSum){
                leftSum = sum1;
                maxLeft = i;
            }
        }

        //计算子数组右侧的最大值
        int rightSum = 0;
        int sum2 = 0;
        int maxRight = 0;
        for(int i = mid+1;mid < high;mid++){
            sum2 = sum2 + array[i];
            if(sum2 > leftSum){
                rightSum = sum2;
                maxRight = i;
            }
        }

        return maxLeft + rightSum;
    }

}
