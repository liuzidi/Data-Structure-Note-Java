package LeetCode.classicProblem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 */

public class P08 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        findSum(candidates, target, 0, list);
        return res;
    }
    public void findSum(int[] candidates, int rest, int index, List<Integer> list){
        if(rest == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = index + 1; i < candidates.length; i++){
            if(candidates[i] > rest) break;
            if(i >= 1 && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            findSum(candidates, rest - candidates[i], i, list);
            list.remove((Integer)(candidates[i]));
        }
    }
    @Test
    public void test1(){
        int i = ~0;
        System.out.println(i);
    }
}
