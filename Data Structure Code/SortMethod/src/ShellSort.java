/**
 * @author:liuzidi
 * @Description:
 * 希尔排序：第一个突破n平方的排序算法，是简单插入排序的改进版，也是属于插入排序的一种，
 * 又称为缩小增量排序，简单插入排序即增量为1的插入排序，希尔排序取gap = length/2为增量，
 * 随后gap = gap / 2，直至gap = 1；
 * 时间复杂度：O(n1.3) ，最坏O(n2)，最优O(n),不稳定
 * 空间复杂度：O(1)
 *
 * 相较于简单插入排序，仅仅直接添加了个关于gap的外层循环
 *
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        shellSort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
    public static void shellSort(int[] nums){
        int len = nums.length;
        int current,preIndex;//记录当前重点对象的值，以及活动的上一个索引
        for(int gap = len / 2; gap > 0; gap /= 2){
            //插入排序(增量为gap)-移动法
            for (int i = gap; i < len; i++) {
                current = nums[i];
                preIndex = i - gap;
                while(preIndex >= 0 && nums[preIndex] > current){
                    nums[preIndex + gap] = nums[preIndex];//保存上一个值
                    preIndex -= gap;
                }
                nums[preIndex + gap] = current;
            }
        }
    }


    public static void swap(int[] nums, int index1, int index2){//交换nums数组的index1和index2位置的值
        nums[index1] ^= nums[index2];
        nums[index2] ^= nums[index1];
        nums[index1] ^= nums[index2];
    }
}
