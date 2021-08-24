package LeetCode.Medium_Algorithm;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA06 {
    @Test
    public void test(){
        System.out.println("重地".hashCode());//1179395
        System.out.println("通话".hashCode());//1179395
    }
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if(nums.length <= 1 || nums[0] > nums[1]) return 0;
        if(nums[len - 1] > nums[len - 2]) return len - 1;
        for(int i = 1; i < nums.length - 1; i++){
            if(nums[i] > nums[i - 1] && nums[i] > nums[i + 1]){
                return i;
            }
        }
        return -1;
    }
}
