package TestFiles;

import java.util.*;

/**
 * @author:liuzidi
 * @Description:
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] nums ={1,31,53,59,45,25,14,25,36,15,7,2,6,26,554,36,15,185,48};
//        Arrays.sort(nums);
//        int i = 0;
//        for(int num : nums){
//            System.out.println("["+ i + "] = " + num);
//            i++;
//        }
//        System.out.println(BS(nums, 1));
        List list = new ArrayList();
        int[] a = new int []{2,3};
        int[] b = new int []{2,3};
        list.add(a);
        list.add(b);

        System.out.println(((int[])list.get(0)).length);

    }
    public static int BS(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int middle = left + (right - left) / 2;//除2本来就容易往前移动一格
            int temp = nums[middle];
            if(temp == target){
                return middle;
            }else if(temp > target){
                right = middle - 1;
            }else{
                left = middle + 1;
            }
        }
        return -1;
    }
}
