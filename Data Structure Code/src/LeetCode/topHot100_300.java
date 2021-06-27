package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
 */
public class topHot100_300 {
    @Test
    public void test1(){
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        lengthOfLIS(nums);
    }
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 1;
        for(int i = 0; i < nums.length; i++){
            int cur = nums[i];
            dp[i] = 1;
            int temp = 1;
            for(int j = i - 1; j >= 0; j--){
                if(nums[j] < cur){
                    dp[i] = Math.max(temp + dp[j], dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
