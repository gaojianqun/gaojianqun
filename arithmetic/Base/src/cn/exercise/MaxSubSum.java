package cn.exercise;

/**
 * Created by gaojianqun on 2018/4/17.
 * 给定一个整数数组nums，找到一个具有最大和的连续子数组（子数组最少包含一个数），返回其最大和
 * 示例：
 * 输入：[-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释:连续子数组[4,-1,2,1]的和最大，为6
 */
public class MaxSubSum {

    public static void main(String [] args){
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int maxSubSum = maxSubSum(nums,0,nums.length-1);
        System.out.println(maxSubSum);
    }


    /**
     * 分割
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public static int maxSubSum(int[] nums,int left,int right){
        int mid = 0;
        int maxLeftSum = 0;
        int maxRightSum = 0;

        if(left < right){
            mid = (left+right)/2;
            //递归左
             maxLeftSum = maxSubSum(nums, left, mid);
            //递归右
             maxRightSum = maxSubSum(nums, mid+1, right);
        }

        //累加左序列
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = mid; i >= left; i--) {
            leftBorderSum += nums[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        //累加右序列
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            rightBorderSum += nums[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        int maxBorderSum = maxLeftBorderSum + maxRightBorderSum;
        return maxBorderSum > maxLeftSum ? maxBorderSum > maxRightSum ? maxBorderSum : maxRightSum
                : maxLeftSum > maxRightSum ? maxLeftSum : maxRightSum;

    }
}
