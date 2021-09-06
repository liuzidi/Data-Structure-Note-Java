package SortMethod;

/**
 * @author:liuzidi
 * @Description:迭代写法
 */
public class MergeSort2 {
    public static void main(String[] args) {
        int[] nums = {56,2,45,25,3,5,7,8,4,0,46,86};
        sort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
    public static void sort(int[] nums){
        mergeSort(nums,0, nums.length - 1 );
    }
    private static void merge(int[] nums, int l, int mid, int r){
        if(l >= r) return;
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
    public static void mergeSort(int[] nums, int l, int r){
        int step = 1;
        int len = nums.length;
        while(step < len){
            int offset = step * 2;
            for(int i = 0; i < len; i += offset){
                int left = i;
                int mid = Math.min(i + step - 1, len - 1);
                int right = Math.min(i + offset - 1, len - 1);
                merge(nums, left, mid, right);
            }
            step *= 2;
        }
    }
}
