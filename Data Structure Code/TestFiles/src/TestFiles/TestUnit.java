package TestFiles;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class TestUnit {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
 class test{
     public static void main(String[] args) {
         TestUnit t =new TestUnit();
         int [] a ={0};
         int length =t.removeDuplicates(a);
         for (int i = 0; i <length ; i++) {
             System.out.println(a[i]);
         }

     }

}
