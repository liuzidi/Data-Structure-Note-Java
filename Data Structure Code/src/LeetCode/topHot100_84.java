package LeetCode;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_84 {
    @Test
    public void test1(){
        int[] test = new int[]{2,2,2};
        System.out.println(largestRectangleArea(test));
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
