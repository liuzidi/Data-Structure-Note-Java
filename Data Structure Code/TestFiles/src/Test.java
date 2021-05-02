/**
 * @author:liuzidi
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2};
        swap(1,1,nums);
        for(int num : nums){
            System.out.println(num);
        }

    }
    private static void swap(int index1, int index2, int[] nums){
        nums[index1] ^= nums[index2];
        nums[index2] ^= nums[index1];
        nums[index1] ^= nums[index2];
    }
}
