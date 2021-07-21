package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liuzidi
 * @Description:给定一个整数数组和一个整数 k，你需要找到该数组中和为 k \
 * 的连续的子数组的个数。
 */
public class topHot100_560 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        map.put(0,1);
        for(int num : nums){
            sum += num;
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
