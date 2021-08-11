package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class SwordOffer_43 {
    @Test
    public void test1(){
        System.out.println(countDigitOne(12));
    }
    public int countDigitOne(int n) {
        if(n < 1) return 0;
        int temp = n;
        int len = 1;
        while(temp > 9){
            temp /= 10;
            len++;
        }
        int res = 0;
        for(int i = 1; i <= len; i++){
            int digit = (int)Math.pow(10,i);
            int high = n / digit;
            int low = n % (digit / 10);
            int cur = (n / (digit / 10)) % 10;
            if(cur == 0){
                res += high * digit / 10;
            }else if(cur == 1){
                res += high * digit / 10 + low + 1;
            }else{
                res += (high + 1) * digit / 10;
            }
        }
        return res;
    }
}
