package LeetCode.classicProblem;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liuzidi
 * @Description:
 */
public class P01 {
    @Test
    public void test(){
        System.out.println(lengthOfLongestSubstring("abba"));
    }
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();//key:字符，value：记录上次出现的位置
        int left = -1, res = 0;
        for(int right = 0; right < s.length(); right++) {
            if(dic.containsKey(s.charAt(right))) {
                left = Math.max(left, dic.get(s.charAt(right)));
            }
            dic.put(s.charAt(right), right); // 哈希表记录
            res = Math.max(res, right - left); // 更新结果
        }
        return res;
    }
}
