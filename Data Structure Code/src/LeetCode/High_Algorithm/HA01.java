package LeetCode.High_Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 */
public class HA01 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0){
            return list;
        }
        int top = 0;
        int bot = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while(left <= right && top <= bot){
            for(int i = left; i <= right; i++){
                list.add(matrix[top][i]);
            }
            for(int i = top + 1; i <= bot; i++){
                list.add(matrix[i][right]);
            }
            if(left < right && top < bot) {
                for (int i = right - 1; i >= left; i--) {
                    list.add(matrix[bot][i]);
                }
                for (int i = bot - 1; i >= top + 1; i--) {
                    list.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bot--;
        }
        return list;
    }
}
