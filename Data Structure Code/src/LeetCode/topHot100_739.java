package LeetCode;

import org.junit.Test;

import java.util.Stack;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_739 {
    @Test
    public void test1(){
        int[] a = {73,74,75,71,69,72,76,73};
        int [] b = dailyTemperatures(a);
        for(int i : b){
            System.out.println(i);
        }
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> queue = new Stack<>();
        for(int i = 0; i < temperatures.length; i++){
            if (!queue.isEmpty()) {
                while (!queue.isEmpty() && temperatures[i] > temperatures[queue.peek()]) {
                    int index = queue.pop();
                    res[index] = i - index;
                }
            }
            queue.push(i);
        }
        return res;
    }
}
