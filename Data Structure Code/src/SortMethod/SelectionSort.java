package SortMethod;

/**
 * 选择排序
 * 时间复杂度：O(n2) ，最坏O(n2)，最优O(n2),不稳定
 * 空间复杂度：O(1)
 *
 * 每次将最小的放在无需序列的第一位，只需交换一次，但缺点是不稳定；
 *
 *
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        selectionSort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
    public static void selectionSort(int[] nums){
        int len = nums.length;
        int minIndex = 0, temp = 0;
        for (int i = 0; i < len; i++) {
            minIndex = i;//minIndex的初始化
            for (int j = i + 1; j < len; j++) {
                if(nums[j] < nums[minIndex]){
                    minIndex = j;//记录最小值的索引值
                }
            }
            temp = nums[i];//仅交换一次
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }
}
