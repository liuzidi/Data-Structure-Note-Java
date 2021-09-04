package LeetCode.classicProblem;

/**
 * @author:liuzidi
 * @Description:
 */
public class P02 {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int order = nums.length - k + 1;
        quickSort(nums,0, nums.length - 1, order);
        return nums[order];
    }
    public void quickSort(int[] nums, int left, int right, int k){
        int p = left + (int)((right - left + 1) * Math.random());
        int pivot= nums[p];
        swap(nums, left, pivot);
        int l = left, r = right;
        while(l < r){
            while(l < r && nums[r] >= pivot){
                r--;
            }
            while(l < r && nums[l] <= pivot){
                l++;
            }
            if(l < r){
                swap(nums, l, r);
            }
        }
        swap(nums, left, l);
        if(l == k){
            return;
        }else if(l < k){
            quickSort(nums, l + 1, right,k);
        }else{
            quickSort(nums, left, r - 1,k);
        }
    }
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
