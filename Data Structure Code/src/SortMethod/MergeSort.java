package SortMethod;

/**
 *递归写法
 *
 */
public class MergeSort {
    public static void main(String[] args){
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        sort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }

    public static void sort(int[] nums){
        mSort(nums,0,nums.length - 1);
    }
    public static void mSort(int[] nums, int left, int right){
        if(left >= right) return;
        int mid = left + (right - left) / 2;
        mSort(nums, left, mid);
        mSort(nums,mid + 1, right);
        int[] newNums = new int[right -left + 1];
        int l1 = left, l2 = mid + 1, i = 0;
        while(l1 <= mid && l2 <= right){
            if(nums[l1] < nums[l2]){
                newNums[i++] = nums[l1++];
            }else{
                newNums[i++] = nums[l2++];
            }
        }
        while(l1 <= mid){
            newNums[i++] = nums[l1++];
        }
        while(l2 <= right){
            newNums[i++] = nums[l2++];
        }
        System.arraycopy(newNums, 0, nums, left, right + 1 - left);
    }

}
