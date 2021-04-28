

/**
 * @author:liuzidi
 * @Description:
 * 冒泡算法
 * 时间复杂度：O(n2) ，最坏O(n2)，最优O(n2),稳定
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        BubbleSortMethod(nums);
        for(int num : nums){
            System.out.println(num);
        }


    }
    public static void BubbleSortMethod(int[] nums){
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                int temp;
                if(nums[j] > nums[j + 1]){
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
}

