/**
 *
 *需要申请一个相同长度的数组来存储有序的数列
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 *
 * 算法描述:
 * 1把长度为n的输入序列分成两个长度为n/2的子序列；
 * 2对这两个子序列分别采用归并排序；
 * 3将两个排序好的子序列合并成一个最终的排序序列。
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
        int left2 = middle + 1;
        mergeSortRecursion(nums,res,left1, middle);
        mergeSortRecursion(nums,res,left2, right);
        int currentIndex = left;//从给到的数组的最左端开始填入
        while(left1 <= middle && left2 <= right){
            res[currentIndex++] = nums[left1] < nums[left2] ? nums[left1++] : nums[left2++];
        }
        while(left1 <= middle){//右部分已经排好序或者是空
            res[currentIndex++] = nums[left1++];
        }
        while(left2 <= right){
            res[currentIndex++] = nums[left2++];
        }
        while(left < right){
            nums[left++] = res[left++];
        }
    }
}
