

/**
 *
 *
 *快速排序：(最左边的为参照值)
 * 存在的问题是：当元素里存在大量的重复元素时，分割区间会非常不平衡，导致全部趋向于另一边，建议采用双路快排
 *
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        quickSort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
    public static void quickSort(int[] nums){
        int len = nums.length;
        if(len == 1){
            return;
        }
        int pivotVal = nums[0];
        int leftIndex = 1;
        int rightIndex = len - 1;
        while(leftIndex < rightIndex){
            if(nums[leftIndex] > pivotVal){
                while(leftIndex < rightIndex && nums[rightIndex] < pivotVal){
                    swap(leftIndex,rightIndex,nums);
                    leftIndex++;
                    rightIndex--;
                }
            }
        }
        swap(leftIndex,rightIndex,nums);

    }

    public static void swap(int index1, int index2, int[] nums){
        nums[index1] ^= nums[index2];
        nums[index2] ^= nums[index1];
        nums[index1] ^= nums[index2];
    }
}
