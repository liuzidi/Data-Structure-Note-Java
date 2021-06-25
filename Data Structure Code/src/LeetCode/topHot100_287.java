package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_287 {
    @Test
    public void test1(){
        int[] q = new int[] {1,3,4,2,2};
        findDuplicate(q);
    }
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int finder= 0;
        while(finder != slow){
            finder = nums[finder];
            slow = nums[slow];
        }
        return finder;
    }
}
