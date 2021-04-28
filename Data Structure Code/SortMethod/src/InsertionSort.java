/**
 * @author:liuzidi
 * @Description:
 *
 * 插入排序
 * 时间复杂度：O(n2) ，最坏O(n2)，最优O(n),稳定
 * 空间复杂度：O(1)
 *
 *
 *
 * 原理：
 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
 *
 * 1从第一个元素开始，该元素可以认为已经被排序；
 * 2取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 3如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 4重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 5将新元素插入到该位置后；
 * 6重复步骤2~5。
 *
 * 采用 for - while结构
 *
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        insertionSort(nums);
        for(int num : nums){
            System.out.println(num);
        }
        
    }
    
    public static void insertionSort(int[] nums){
        int len = nums.length;
        int preIndex;//当前检测到的数字的上一个索引
        int current;//记录第i次循环的当前需要检验的值(即nums[i])
        for (int i = 1; i < len; i++) {
            current = nums[i];
            preIndex = i - 1;
            while(preIndex >= 0 &&current < nums[preIndex]){
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = current;
        }
    }
}
