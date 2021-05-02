

/**
 *
 *
 *快速排序：(最左边的为参照值)
 * 存在的问题是：当元素里存在大量的重复元素时，分割区间会非常不平衡，导致全部趋向于另一边，建议采用双路快排
 *
 */
public class QuickSortRandom {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,4,54,3,1,2};
        QuickSortRandom.quickSort(nums);
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
        if(leftIndex >= rightIndex){
            return;
        }
        int l = leftIndex;
        int r = rightIndex;
        swap(leftIndex, (int)(Math.random() * (rightIndex - leftIndex + 1)) + leftIndex,nums);
        int value = nums[leftIndex];
        int j = leftIndex;
        for(int i = j + 1; i <= rightIndex; i++){
            if(nums[i] < value){
                j++;
                swap(i,j,nums);
            }
        }
        swap(leftIndex,j,nums);
        quickSort(l,j - 1,nums);
        quickSort(j + 1,r,nums);
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
