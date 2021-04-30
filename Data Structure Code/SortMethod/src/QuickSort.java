import java.util.Random;

/**
 *
 *
 *快速排序：双指针经典写法：随机快排
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
        Random random = new Random();
        random.nextInt();


    }
}
