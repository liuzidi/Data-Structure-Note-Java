package LeetCode.Medium_Algorithm;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA10 {
    public int titleToNumber(String columnTitle) {
        int res = 0;
        char[] chars = columnTitle.toCharArray();
        for (char c : chars){
            res *= 26;
            res += (c - 'A' + 1);
        }
        return res;
    }
}
