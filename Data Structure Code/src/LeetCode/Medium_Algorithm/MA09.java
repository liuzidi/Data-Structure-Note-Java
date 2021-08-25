package LeetCode.Medium_Algorithm;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA09 {
    public int trailingZeroes(int n) {
        if(n < 5){
            return 0;
        }
        int res = 0;
        for(int i = 5; i <= n; i+=5){
            int temp = i;
            while(temp % 5 == 0 && temp != 0){
                temp /= 5;
                res++;
            }
        }
        return res;
    }
}
