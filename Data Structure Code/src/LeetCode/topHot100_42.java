package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_42 {

    public int trap(int[] height) {
        int[] dp = new int[height.length];
        for(int i = 1; i < height.length; i++){
            if(height[i] <= height[i - 1]){
                dp[i] = dp[i - 1];
            }else{
                int index = i - 1;
                int sum = 0;
                while(index >= 1 && height[index] <= height[index - 1] && height[index] <= height[i]){
                    index--;
                    sum += height[index];
                }
                sum -= height[index];
                int minHeight = Math.min(height[i], height[index]);
                dp[i] = minHeight * (i - index) + dp[index] - sum;
            }
        }
        return dp[height.length - 1];
    }

}
