package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_312 {
    public int maxCoins(int[] nums) {
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        newNums[newNums.length - 1] = 1;
        System.arraycopy(nums,0,newNums,1,nums.length);
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        for(int i = newNums.length - 1; i >= 0; i--){
            for(int j = i + 2; j < newNums.length; j++){
                for(int k = i + 1; k < j; k++){
                    int sum = dp[i][k] + dp[k][j] + newNums[k] * newNums[i] * newNums[j];
                    dp[i][j] = Math.max(sum, dp[i][j]);
                }
            }
        }
        return dp[0][nums.length + 1];
    }
}
