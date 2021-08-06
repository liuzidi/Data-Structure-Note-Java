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
        String s = "a";
        String t = "b";
        System.out.println(minWindow(s, t));
    }
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        if(s.length() == t.length() && s.equals(t)) return s;
        Map<Character,Integer> map = new HashMap<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for(char c : tChars){
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        int len = 0;
        String minString = "";
        int left = 0, right = 0;
        while(right < sChars.length){
             if(isValid(map)) break;
             char cur = sChars[right];
             if(map.containsKey(cur)){
                 map.put(cur,map.get(cur) - 1);
             }
             right++;
        }
        if(right >= sChars.length) return "";
        minString = s.substring(left,right);
        right--;
        len = right - left + 1;

        while(left <= right && right < sChars.length){
            char cLeft = sChars[left];
            if(map.containsKey(cLeft)){
                map.put(cLeft, map.get(cLeft) + 1);
                if(map.get(cLeft) > 0) {
                    right++;
                    while(right < sChars.length){
                        char cRight = sChars[right];
                        if(map.containsKey(cRight)){
                            map.put(cRight, map.get(cRight) - 1);
                            if(cRight == cLeft) break;
                        }
                        right++;
                    }
                }
            }
            if(right - left < len){
                len = right - left + 1;
                minString = s.substring(left, right);
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
