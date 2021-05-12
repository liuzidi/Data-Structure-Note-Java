package TestFiles;

import java.util.List;
import java.util.ArrayList;

/**
 * @author:liuzidi
 * @Description:
 */
public class test512 {
    public static void main(String[] args){
        findContinuousSequence(9);
    }
    public static int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int left = 1;
        int right = target / 2 + 1;
        while(left < right){
            int current = helper(left,right);
            if(current == target){
                int[] temp = new int[right - left + 1];
                for(int i = left, count = 0; i <= right; i++){
                    temp[count] = i;
                    count++;
                }
                list.add(temp);
            }
            if(current > target){
                right--;
            }else{
                left++;
            }
        }
        return list.toArray(new int[list.size()][]);


    }
    public static int helper(int l, int r){
        return (r + l) * (r - l + 1) / 2;
    }
}
