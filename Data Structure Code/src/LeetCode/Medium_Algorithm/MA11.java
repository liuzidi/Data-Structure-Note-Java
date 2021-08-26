package LeetCode.Medium_Algorithm;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA11 {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        long longN = n;
        if(n < 0){
            x = 1 / x;
            longN = - longN;
        }
        double res = 1.0;
        while(longN > 0){
            if(longN % 2 == 1){
                res *= x;
            }
            x *= x;
            longN /= 2;
        }
        return res;
    }
}
