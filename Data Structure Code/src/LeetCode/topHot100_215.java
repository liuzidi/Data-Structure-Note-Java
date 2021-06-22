package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_215 {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int target = nums.length - k;
        while(true){
            int index = quickSort(nums, left, right);
            if(index == target){
                return nums[target];
            }else if(index < target){
                left = index + 1;
            }else{
                right = index - 1;
            }
        }
    }
    public int quickSort(int[] nums, int l, int r){
        int pivot = l + (int)((r - l + 1) * Math.random());
        int pValue = nums[pivot];
        swap(nums, l, pivot);
        int left = l, right = r;
        while(left < right){
            while(left < right && nums[right] >= pValue){
                right--;
            }
            while(left < right && nums[left] <= pValue){
                left++;
            }
            if(left < right){
                swap(nums, left, right);
            }
        }
        swap(nums, l, left);
        return left;
    }
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
