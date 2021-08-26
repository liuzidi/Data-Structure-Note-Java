package LeetCode.Medium_Algorithm;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA12 {
    @Test
    public void test(){
        mySqrt(8);
    }
    public int mySqrt(int x) {
        return helper(1, x, x);
    }
    public int helper(int left, int right, int p){
        if(left >= right){
            return right;
        }
        if(left + 1 == right){
            return left;
        }
        int mid = (left + right) / 2;
        if(p / mid == mid){
            return mid;
        }else if(p / mid < mid){
            return helper(left, mid, p);
        }else{
            return helper(mid, right, p);
        }
    }
}
