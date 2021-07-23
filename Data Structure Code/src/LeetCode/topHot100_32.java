package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_32 {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int maxLen = 0;
        for(int i = 1; i < chars.length; i++){
            if(chars[i] == '(') continue;
            if(chars[i - 1] == '(' && chars[i] == ')'){
                if(i >= 2)
                    dp[i] = dp[i - 2] + 2;
                else
                    dp[i] = 2;
            }else if(i - dp[i - 1]  > 0 && chars[i - dp[i - 1] - 1] == '('){
                if(i - dp[i - 1] >= 2 )
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2]+ 2;
                else
                    dp[i] = dp[i - 1] + 2;
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
