/**
 *
 *需要申请一个相同长度的数组来存储有序的数列
 *
 */
public class MergeSort {
    public static void main(String[] args){
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        int[]res = mergeSort(nums);
        for(int num : res){
            System.out.println(num);
        }

    }
    public static int[] mergeSort(int[] nums){
        int len = nums.length;
        int[] res = new int[len];
        mergeSortRecursion(nums,res,0,len - 1);
        return res;
    }

    private static void mergeSortRecursion(int[] nums, int[] res, int left, int right){
        if(left >= right){
            return;
        }
        int middle = left + ((right - left) >> 1);
        int left1 = left;
        int right1 = middle;
        int left2 = middle + 1;
        int right2 = right;
        mergeSortRecursion(nums,res,left1,right1);
        mergeSortRecursion(nums,res,left2,right2);
        int currentIndex = left;//从给到的数组的最左端开始填入
        while(left1 <= right1 && left2 <= right2){
            res[currentIndex++] = nums[left1] < nums[left2] ? nums[left1++] : nums[left2++];
        }
        while(left1 <= right1){//右部分已经排好序或者是空
            res[currentIndex++] = nums[left1++];
        }
        while(left2 <= right2){
            res[currentIndex++] = nums[left2++];
        }
        for(int i = left; i <= right; i++){
            nums[i] = res[i];
        }
    }
}
