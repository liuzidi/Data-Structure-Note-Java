package TestFiles;

/**
 * @author:liuzidi
 * @Description:
 * //翻转数组的方法
 */
public class Rotate {
    public static void main(String[] args) {
        int[] a ={0,1,2,3,4,5,6,7,8,9,10};
        int[] b =rotateArr(a,3,10);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]+"\t");
        }
    }

    /**
     *
     * @param nums
     * @param start
     * @param end
     * 反转数组的从start光标到 end光标的所有数组元素
     */
    public static int[] rotateArr(int[] nums,int start, int end){
        if(start < end && end <nums.length){
            while(start < end){
                int temp =nums[start];
                nums[start] =nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
            return nums;
        }
        else throw new RuntimeException("input error");
    }
}
