package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class SwordOffer51 {
    private int res = 0;
    public int reversePairs(int[] nums) {
        mergeSort(0,nums.length - 1, nums);
        return res;
    }
    public void mergeSort(int l, int r, int[] nums){
        if(l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(l, mid,nums);
        mergeSort(mid + 1, r, nums);
        int left = l;
        int right = mid + 1;
        int[] temp = new int[r - l + 1];
        int tempIndex = 0;
        while(left <= mid && right <= r){
            if(nums[left] > nums[right]){
                temp[tempIndex++] = nums[right];
                res += (mid - left + 1);
            }else{
                temp[tempIndex++] = nums[left];
            }
        }
        while(left <= mid) temp[tempIndex++] = nums[left++];
        while(right <= r) temp[tempIndex++] = nums[right++];
        System.arraycopy(temp, 0, nums, l, temp.length);
    }
}
