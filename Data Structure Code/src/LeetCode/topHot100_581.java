package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_581 {
    public int findUnsortedSubarray(int[] nums) {
        int minNum = nums[nums.length - 1];
        int left = nums.length;
        for (int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] > minNum){
                left = i;
            }
            minNum = Math.min(minNum, nums[i]);
        }
        int maxNum = nums[0];
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < maxNum){
                right = i;
            }
            maxNum = Math.max(maxNum, nums[i]);
        }
        return Math.max(right - left + 1, 0);
    }
}
