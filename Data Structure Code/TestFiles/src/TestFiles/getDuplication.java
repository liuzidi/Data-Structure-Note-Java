package TestFiles;

import java.util.ConcurrentModificationException;

/**
 * @author:liuzidi
 * @Description:
 * 实现：一个长度为n+1的数组里的所有数字都在1~n的范围内，
 * 不修改数组找到重复的数字，无需输出所有重复的数字
 */
public class getDuplication {
    public int getDupNum(final int nums[]){
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 1;
        int end = nums.length -1;
        while(end >= start){
            int middle =((end - start)>>1)+start;
            int count = countRange(nums, nums.length, start, middle);
                if(end == start){
                    if(count >1){
                        return start;
                    }else{
                        break;
                    }
                }
                if(count > (middle - start + 1)){
                    end = middle;//可以确定在start - middle之间有重复元素
                }else{
                    start = middle + 1;//可以确定在middle+1 - end之间有重复元素
                }
            }
            return -1;
        }



    public int countRange(final int nums[], int length, int start, int end){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if(nums[i] >= start && nums[i] <=end){
                count++;
            }
        }
        return count;
    }
}
class testDay412{
    public static void main(String[] args) {
        int[] nums = {2,3,5,4,3,2,6,7};
        getDuplication instance1 =new getDuplication();
        System.out.println(instance1.getDupNum(nums));
    }
}
