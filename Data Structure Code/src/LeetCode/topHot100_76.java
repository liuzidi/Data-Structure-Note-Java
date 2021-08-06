package LeetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liuzidi
 * @Description:最小覆盖子窗
 */
public class topHot100_76 {
    @Test
    public void test(){
        String s = "ADOBECODEBANC" ;
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        Map<Character,Integer> map = new HashMap<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for(char c : tChars){
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        int left = 0, right = 0;
        String minString = "";
        int minLen = s.length();
        while(right < s.length()){
            if(!isValid(map)){
                if(map.containsKey(sChars[right])){
                    map.put(sChars[right], map.get(sChars[right]) - 1);
                }
                right++;
            }else{
                if(right - left < minLen){
                    minLen = right - left;
                    minString = s.substring(left,right);
                }
                if(map.containsKey(sChars[left])){
                    map.put(sChars[left], map.get(sChars[left]) + 1);
                }
                left++;
            }
        }
        while(isValid(map)){
            if(right - left < minLen){
                minLen = right - left;
                minString = s.substring(left,right);
            }
            if(map.containsKey(sChars[left])){
                map.put(sChars[left], map.get(sChars[left]) + 1);
            }
            left++;
        }
        return minString;
    }
    public boolean isValid(Map<Character,Integer> map){
        for(Integer value : map.values()){
            if(value > 0) return false;
        }
        return true;
    }

}
