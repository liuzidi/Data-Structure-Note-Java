package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_152 {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int temp = dp[0];
        for(int i = 1; i < len; i++){
            if(nums[i] == 0){
                temp = 0;
            }else if(temp == 0){
                temp = nums[i];
            }else{
                temp *= nums[i];
            }
            dp[i] = Math.max(temp, dp[i - 1]);
            dp[i] = Math.max(dp[i], nums[i]);
        }
        return dp[len - 1];
    }
}
