package SortMethod;

/**
 * @author:liuzidi
 * @Description:
 */
public class MergeSort2 {
    public static void main(String[] args) {
        int[] nums = {56,2,45,25};
        sort(nums);
    }
    public static void sort(int[] nums){
        mergeSort(nums,0, nums.length - 1 );
        for(int num : nums){
            System.out.println(num);
        }
    }
    private static void mergeSort(int[] nums, int l, int r){
        if(l >= r) return;
        int mid = l + (r - l) / 2;
        /**
         * 分解
         */
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        /**
         * l到mid为有序序列, mid+1到r为有序队列, 接下来进行合并
         */
        int[] res = new int[r - l + 1];
        int lIndex = l, rIndex = mid + 1, resIndex = 0;
        while(lIndex <= mid && rIndex <= r){
            if(nums[lIndex] < nums[rIndex]){
                res[resIndex++] = nums[lIndex++];
            }else{
                res[resIndex++] = nums[rIndex++];
            }
        }
        while(lIndex <= mid) res[resIndex++] = nums[lIndex++];
        while(rIndex <= r)   res[resIndex++] = nums[rIndex++];
        System.arraycopy(res, 0, nums, l, r + 1 - l);

    }
}
