package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_279 {
    @Test
    public void test1(){
        numSquares(43);
    }
    public int numSquares(int n) {
        int start = (int)Math.sqrt(n);
        int min = n;
        while(start > 0){
            int count = numOf(start, n);
            start--;
            if(min >= count){
                min = count;
            }else{
                break;
            }
        }
        return min;
    }
    private int numOf (int max, int target){//以max为最大值时的位数
        int count = 0;
        while(target != 0){
            if(target - max * max >= 0){
                target -= max * max;
                count++;
            }else{
                max--;
            }
        }
        return count;
    }

}

