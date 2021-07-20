package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_494 {
    @Test
    public void test1(){

    }

    private int res = 0;
    public int findTargetSumWays(int[] nums, int target) {
        DFS(nums,target,0,0);
        return res;
    }
    private void DFS(int[] nums, int target, int index, int count){
        if(index == nums.length) {
            if(target == count){
                res++;
            }
            return;
        }
        DFS(nums, target, index + 1, count + nums[index]);
        DFS(nums, target, index + 1, count - nums[index]);

    }
}
