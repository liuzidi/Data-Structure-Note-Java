package SortMethod;

/**
 * @author:liuzidi
 * @Description:
 * 注：
 * 稳定：如果a原本在b前面，而a=b，排序之后a仍然在b的前面。
 * 不稳定：如果a原本在b的前面，而a=b，排序之后 a 可能会出现在 b 的后面。
 *
 *
 * 冒泡排序
 * 时间复杂度：O(n2) ，最坏O(n2)，最优O(n2),稳定
 * 空间复杂度：O(1)
 *
 * 原理：在无序序列中，从头遍历到尾，把最大的放在最后一位，形成新的无需序列和有序序列，直到只剩一个
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        sort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }


    public static void sort(int[] nums){
        int len = nums.length;
        for(int i = len - 1; i >= 0; i--){
            for(int j = 1; j <= i; j++){
                if(nums[j] < nums[j - 1]){
                    swap(nums, j, j - 1);
                }
            }
        }
    }
    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

