/**
 * @author:liuzidi
 * @Description:
 */
public class QuickSortNormal {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        QuickSortNormal.quickSort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
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
        if(leftIndex >= rightIndex){
            return;
        }
//
        int l = leftIndex;
        int r = rightIndex;
        //记录左界和右界

        int pivotVal = nums[l];
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
        int pivotIndex = leftIndex;
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
