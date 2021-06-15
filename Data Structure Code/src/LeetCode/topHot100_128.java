package LeetCode;

import java.util.Arrays;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_128 {
    public static void main(String[] args) {
        longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1});
    }
    public static int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int res = 1;
        int max = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] - nums[i - 1] == 1){
                res++;
            }else{
                max = Math.max(max, res);
                res = 1;
            }
        }
        return max;
    }
}
