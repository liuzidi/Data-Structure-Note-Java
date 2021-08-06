package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 * 给你两个单词word1 word2，请你计算出将word1转换成word2 所使用的最少操作数。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class topHot100_72 {
    public int minDistance(String word1, String word2) {
        if(word1.length() == 0 || word2.length() == 0){
            return Math.max(word1.length(), word2.length());
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i < word1.length() + 1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j < word2.length() + 1; j++){
            dp[0][j] = j;
        }
        for(int i = 1; i < word1.length() + 1; i++){
            for(int j = 1; j < word2.length() + 1; j++){
                int A = dp[i - 1][j] + 1;
                int B = dp[i][j - 1] + 1;
                int C = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(A,Math.min(B, C));
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
