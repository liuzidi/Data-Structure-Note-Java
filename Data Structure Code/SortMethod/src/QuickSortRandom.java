

/**
 *
 *
 *快速排序：(最左边的为参照值)
 * 存在的问题是：当元素里存在大量的重复元素时，分割区间会非常不平衡，导致全部趋向于另一边，建议采用双路快排
 *
 */
public class QuickSortRandom {

    /**随机快排
     *
     * @param leftIndex 左索引
     * @param rightIndex 右索引
     * @param nums 需要排序的数组
     */
    public static void quickSort(int leftIndex, int rightIndex, int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }
        if(leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= nums.length){
            return;
        }
//
        int l = leftIndex;
        int r = rightIndex;
        //记录左界和右界
        int pivotIndex = (int)(Math.random() * (rightIndex - leftIndex + 1)) + leftIndex;//基准值的设定
        int pivotVal = nums[pivotIndex];
        swap(pivotIndex,leftIndex,nums);//换到最后一个
        int currentIndex = l;//记录当前pivotVal所在数组的位置，现在为数组末尾

        while(leftIndex < rightIndex){
            if(nums[rightIndex] < pivotVal){
                swap(rightIndex,currentIndex,nums);
                currentIndex =rightIndex;
                while(leftIndex < rightIndex && nums[leftIndex] < pivotVal){
                    leftIndex++;
                }
                swap(leftIndex,currentIndex,nums);
                currentIndex = leftIndex;
            }
            rightIndex--;
        }
        pivotIndex = leftIndex;
        quickSort(l,pivotIndex - 1,nums);
        quickSort(pivotIndex + 1,r,nums);
    }

    public static void quickSort(int[] nums){
        int len = nums.length;
        quickSort(0,len - 1,nums);
    }

    //交换nums数组的两个索引对应的值
    private static void swap(int index1, int index2, int[] nums){
        if(index1 == index2){
            return;
        }

        nums[index1] ^= nums[index2];
        nums[index2] ^= nums[index1];
        nums[index1] ^= nums[index2];
    }
}
