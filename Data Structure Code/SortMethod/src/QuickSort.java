

/**
 *
 *
 *快速排序：(最左边的为参照值)
 * 存在的问题是：当元素里存在大量的重复元素时，分割区间会非常不平衡，导致全部趋向于另一边，建议采用双路快排
 *
 */
public class QuickSort {
    /**
     *
     * @param leftIndex 左索引
     * @param rightIndex 右索引
     * @param nums 需要排序的数组
     */
    public static void quickSort(int leftIndex, int rightIndex, int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }
        if(leftIndex == rightIndex){
            return;
        }
        if(rightIndex - leftIndex == 1){
            if(nums[leftIndex] > nums[rightIndex] ){
                swap(leftIndex,rightIndex,nums);
            }
            return;
        }
        int pivotIndex = (int)(Math.random() * (rightIndex - leftIndex + 1)) + leftIndex;//基准值的设定
        quickSort(leftIndex,pivotIndex - 1,nums);
        quickSort(pivotIndex + 1,rightIndex,nums);

        int pivotVal = nums[pivotIndex];
        int pivotCurrentIndex = pivotIndex;
        while(leftIndex < rightIndex){
            if(nums[rightIndex] < pivotVal){
                swap(pivotCurrentIndex, rightIndex, nums);
                pivotCurrentIndex = rightIndex;
            }else{
                rightIndex--;
            }
            if(nums[leftIndex] > pivotVal){
                swap(pivotCurrentIndex, rightIndex, nums);
                pivotCurrentIndex = leftIndex;
            }else{
                leftIndex++;
            }
        }
    }

    //交换nums数组的两个索引对应的值
    private static void swap(int index1, int index2, int[] nums){
        nums[index1] ^= nums[index2];
        nums[index2] ^= nums[index1];
        nums[index1] ^= nums[index2];
    }

}
