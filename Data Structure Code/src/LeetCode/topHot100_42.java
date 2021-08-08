package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_42 {
    /**
     *  双指针写法
     */
    public int trap1(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        int left_max = height[left];
        int right_max = height[right];
        while(left <= right){
            left_max = Math.max(left_max, height[left]);
            right_max = Math.max(right_max, height[right]);
            if(left_max < right_max){
                res += (left_max - height[left]);
                left++;
            }else{
                res += (right_max - height[right]);
                right--;
            }
        }
        return res;
    }

}
