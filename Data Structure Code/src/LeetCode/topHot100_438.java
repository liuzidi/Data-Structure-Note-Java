package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_438 {
    @Test
    public void test1(){
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        char[] sArr = s.toCharArray();
        int[] map = new int[26];
        int[] temp = new int[26];
        int len = p.length();
        for(char c : p.toCharArray()){
            map[c - 'a']++;
        }
        int start = 0;
        int end = len - 1;
        for(int i = start; i <= end; i++){
            temp[sArr[i] - 'a']++;
        }
        if(isEqual(map,temp)){
            res.add(start);
        }
        while(end < sArr.length - 1){
            temp[sArr[start++] - 'a']--;
            temp[sArr[++end] - 'a']++;
            if(isEqual(map, temp)){
                res.add(start);
            }
        }
        return res;
    }
    private boolean isEqual(int[] a, int[] b){
        for(int i = 0; i < 26; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
}
