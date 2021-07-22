package LeetCode;

/**
 * @author:liuzidi
 * @Description:回文子串
 */
public class topHot100_647 {
    public int countSubstrings(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        for(int len = 0; len < chars.length; len++){//子串长度
            for(int left = 0; left + len < chars.length; left++){
                int right = left + len;
                if(isValid(chars,left,right)) res++;
            }
        }
        return res;
    }
    private boolean isValid(char[] chars, int left, int right){
        while(left < right){
            if(chars[left] == chars[right]){
                left++;
                right--;
            }else return false;
        }
        return true;
    }

}
