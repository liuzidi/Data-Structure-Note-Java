package LeetCode.Medium_Algorithm;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA08 {
    public boolean isHappy(int n) {
        if(n == 2 || n == 3){
            return false;
        }
        if(n == 1){
            return true;
        }
        int temp = 0;
        while(n != 0){
            int tail = n % 10;
            temp += tail * tail;
            n /= 10;
        }
        return isHappy(temp);
    }
}
