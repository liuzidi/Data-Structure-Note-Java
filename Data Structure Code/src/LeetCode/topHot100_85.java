package LeetCode;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author:liuzidi
 * @Description:最大矩形
 */
public class topHot100_85 {
    @Test
    public void test(){
        char[][] newchars = new char[3][3];
        newchars[0] = new char[]{'1', '0', '1'};
        newchars[1] = new char[]{'0', '1', '1'};
        newchars[2] = new char[]{'1', '1', '0'};
        maximalRectangle(newchars);
    }
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int[][] newMatrix = new int[matrix.length][matrix[0].length + 2];
        //第一排初始化
        for(int j = 1; j <= matrix[0].length; j++){
            newMatrix[0][j] = matrix[0][j - 1] - '0';
        }
        for (int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length + 1; j++){
                if(newMatrix[i - 1][j] > 0){
                    if(matrix[i][j - 1] == '1')
                        newMatrix[i][j] = newMatrix[i - 1][j] + 1;
                }else{
                    newMatrix[i][j] = matrix[i][j - 1] - '0';
                }
            }
        }
        int max = 0;
        for(int[] a : newMatrix){
            int temp = largestRectangleArea(a);
            max =Math.max(max, temp);
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        //头：下标-1，值为0，尾：下标heights.length 值为0
        int[] newArr = new int[heights.length + 2];
        System.arraycopy(heights,0,newArr,1,heights.length);
        newArr[0] = 0;
        newArr[newArr.length - 1] = 0;
        for(int i = 0; i < newArr.length; i++){
            if (!queue.isEmpty() &&newArr[i] < newArr[queue.peekLast()]) {
                while (queue.size() > 1 && newArr[i] < newArr[queue.peekLast()]) {
                    int h = queue.pollLast();
                    if (!queue.isEmpty()) {
                        int width = i - queue.peekLast() - 1;
                        res = Math.max(res, newArr[h] * (width));
                    }
                }
            }
            queue.addLast(i);
        }
        return res;
    }
}
