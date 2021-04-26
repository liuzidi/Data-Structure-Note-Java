import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author:liuzidi
 * @Description:
 */
public class test3 {
    public int countPrimes(int n) {
        //非质数必然能够除尽至少一个比自己小的质数
        if(n <= 2){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        set.add(2);//最小的质数 2
        for(int i = 3; i < n; i += 2){
            Iterator<Integer> iterator = set.iterator();
            while(iterator.hasNext()){
                int zhishuInt = iterator.next();
                if(i != zhishuInt && i % zhishuInt == 0){//说明i不是质
                    break;
                }
                if(!iterator.hasNext()){
                    Integer newZhiShu = Integer.valueOf(i);
                    set.add(newZhiShu);
                }
            }
        }
        return set.size();
    }
}
