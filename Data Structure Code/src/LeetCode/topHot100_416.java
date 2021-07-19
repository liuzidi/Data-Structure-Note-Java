package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_416 {
    class Solution {
        public boolean canPartition(int[] nums) {
            if(nums.length < 2) return false;
            int plus = 0;
            for(int num: nums){
                plus += num;
            }
            if(plus % 2 != 0) return false;
            int target = plus / 2;
            boolean[][] dp = new boolean[nums.length][target + 1];
            for(int i = 0; i < target + 1; i++){
                dp[0][i] = nums[0] == target;
            }
            for(int i = 1; i < nums.length; i++){
                for(int j = 0; j < target + 1; j++){
                    if(dp[i - 1][j] || nums[i] == j){
                        dp[i][j] = true;
                    }else{
                        if(j > nums[i])
                            dp[i][j] = dp[i - 1][j - nums[i]];
                        else
                            dp[i][j] = false;
                    }
                }
            }
            return dp[nums.length - 1][target];
        }
    }
}
