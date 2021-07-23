package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_10 {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        dp[0][0] = true;
        for(int j = 1; j <= p.length(); j++){
            if(cp[j - 1] == '*')
            dp[0][j] = dp[0][j - 2];
        }
        //dp[0][j] 和 dp[i][0]已经初始化完成
        for(int i = 1; i <= s.length(); i++){
            for (int j = 1; j <= p.length(); j++) {
                if(cs[i - 1] == cp[j - 1] || cp[j - 1] == '.'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(cp[j - 1] == '*'){
                    if(cs[i - 1] == cp[j - 2] || cp[j - 2] == '.')
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                    else
                        dp[i][j] = dp[i][j - 2];
                }
            }

        }
        return dp[s.length()][p.length()];
    }
}
