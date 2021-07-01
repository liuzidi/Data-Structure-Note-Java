package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_322 {
    private int min = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        DFS(amount, 0, coins);
        if(min == Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }
    private void DFS(int amount, int count, int[] coins){
        if(amount == 0){
            min = Math.min(min, count);
            return;
        }
        if(amount < 0){
            return;
        }
        for (int coin : coins) {
            DFS(amount - coin, count + 1, coins);
        }
    }



    @Test
    public void test1(){

    }
}
