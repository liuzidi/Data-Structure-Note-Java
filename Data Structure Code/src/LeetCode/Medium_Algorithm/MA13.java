package LeetCode.Medium_Algorithm;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA13 {
    public int divide(int dividend, int divisor) {
        if(divisor == 1) return dividend;
        if(divisor == -1){
            if(dividend > Integer.MIN_VALUE) return -dividend;
            return Integer.MAX_VALUE;
        }
        if(dividend == 0) return 0;
        boolean positive = true;
        if(dividend < 0) positive = !positive;
        if(divisor < 0) positive = !positive;
        long dividendLong,divisorLong;
        dividendLong = dividend > 0 ? (long)dividend : -(long)dividend;
        divisorLong = divisor > 0 ? (long)divisor : -(long)divisor;
        int res = div(dividendLong, divisorLong);
        return positive ? res : -res;
    }
    public int div(long dividend, long divisor){
        if(dividend < divisor) return 0;
        long res = 1;
        long divFactor = divisor;
        while(divFactor + divFactor <= dividend){
            res += res;
            divFactor += divFactor;
        }
        return (int)(res + div(dividend - divFactor, divisor));
    }
}
