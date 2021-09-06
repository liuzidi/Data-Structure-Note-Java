package SortMethod;

/**
 * @author:liuzidi
 * @Description:
 */
public class InsertSort {
    public void sort(int[] nums){
        int len = nums.length;
        for(int i = 1; i < len; i++){
            if(nums[i] < nums[i - 1]){
                int index = i - 1;
                int value = nums[i];
                while(index >= 0 && nums[i] < nums[index]){
                    index--;
                }
                for(int j = index + 1; j < i ; j++){
                    swap(nums, j, j + 1);
                }
                nums[index + 1] = value;
            }
        }
    }
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
class ssfdfd{
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        ChooseSort.sort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
}
