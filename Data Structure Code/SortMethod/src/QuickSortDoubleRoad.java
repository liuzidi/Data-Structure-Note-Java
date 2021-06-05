/**
 *
 */
public class QuickSortDoubleRoad {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        QuickSortDoubleRoad.quickSort(nums);
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
        //记录左界和右界
        int Index = (int)(Math.random() * (rightIndex - leftIndex + 1)) + leftIndex;//基准值的设定
        int pivotVal = nums[Index];
        swap(Index,leftIndex,nums);
        while(l < r){
            while(l < r && nums[r] >= pivotVal){
                r--;
            }
            while(l < r && nums[l] <= pivotVal){
                l++;
            }
            if(l < r)
            swap(l,r,nums);
        }
        swap(l,leftIndex,nums);
        quickSort(leftIndex,l - 1,nums);
        quickSort(l + 1,rightIndex,nums);
    }
    public static void quickSort(int[] nums){
        int len = nums.length;
        quickSort(0,len - 1,nums);
    }
    //交换nums数组的两个索引对应的值
    private static void swap(int index1, int index2, int[] nums){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
