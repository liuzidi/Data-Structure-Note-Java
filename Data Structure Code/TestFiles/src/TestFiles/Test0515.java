package TestFiles;

import java.util.*;

/**
 * @author:liuzidi
 * @Description:
 */
public class Test0515 {
    public static void main(String[] args) {
        lastRemaining(2,1);
    }
    public static void test(int n ){
        int dp[][] = new int[n + 1][6 * n + 1];
        for(int i = 1; i <= 6; i++){
            dp[1][i] = 1;
        }
        for(int i = 1; i <= 6 * 4; i++)//所有筛子的点数
            for(int k = 2; k <= 4; k++)
                for(int j = 1; j <= 6; j++)//最后一个筛子的点数
                    if(i - j >= 1)
                        dp[k][i] += dp[k - 1][i - j];

    }
    public static int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(i);
        }
        int index = -1 ;
        while(list.size() == 1){
            index = (index + m) % list.size();
            list.remove(index);
            index = index == 0 ? list.size() - 1 : index - 1;
        }
        return list.get(0).intValue();
    }
}
