import java.util.HashSet;
import java.util.Set;

/**
 * @author:liuzidi
 * @Description:
 */
public class test2 {
    public static int countPrimes(int n) {
        //非质数必然能够除尽至少一个比自己小的质数
        if(n < 2){
            return 0;
        }
        if(n == 2){
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        set.add(2);//最小的质数 2
        for(int i = 3; i <= n; i++){
            Integer temp = Integer.valueOf(i);
            set.add(temp);
            for(Integer zhishu : set){
                int zhishuInt = zhishu.intValue();
                if(i != zhishuInt && i % zhishuInt == 0){//说明i不是质数
                    set.remove(temp);
                    break;
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }
}
