package SortMethod;

/**
 * @author:liuzidi
 * @Description:
 */
public class ChooseSort {

    public static void sort(int[] nums){
        int len =  nums.length;
        for(int i = len - 1; i >= 0; i--){
            int max = Integer.MIN_VALUE;
            int index = 0;
            for(int j = 0; j <= i; j++){
                if(nums[j] > max){
                    index = j;
                    max = nums[j];
                }
            }
            swap(nums,index, i);
        }
    }
    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
class ssss{
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        ChooseSort.sort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
}
