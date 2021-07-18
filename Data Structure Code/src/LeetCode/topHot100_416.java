package LeetCode;

import java.util.Arrays;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_416 {
    public boolean canPartition(int[] nums) {
        if(nums.length < 2) return false;
        int addPlus = 0;
        for(int num : nums){
            addPlus += num;
        }
        if(addPlus % 2 != 0) return false;
        int target = addPlus / 2;
        boolean[] dp = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i] = DFS(nums,target,i);
        }
        return null;
    }
    //判断nums的前len个元素能不能够凑成target和,即索引0到index的元素
    public boolean DFS(int[] nums, int target, int index){
        if(index == 0){
            return nums[0] == target;
        }
    }
}
