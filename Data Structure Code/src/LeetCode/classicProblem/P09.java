package LeetCode.classicProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 */
public class P09 {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for(int[] area : intervals){
            if(newInterval[0] > area[1] || newInterval[1] < area[0]){
                res.add(area);
            }else if(newInterval[0] > area[0] && newInterval[1] < area[1]){
                res.add(area);
            }else if(newInterval[0] < area[0] && newInterval[1] > area[1]){
                res.add(new int[]{newInterval[0], newInterval[1]});
            }else{
                newInterval[0] = Math.min(area[0], newInterval[0]);
                newInterval[1] = Math.max(area[1], newInterval[1]);
                if(res.size() > 0){
                    int[] last = res.get(res.size() - 1);
                    if(last[0] == newInterval[0] || last[1] == newInterval[1]){
                        res.remove(res.size() - 1);
                    }
                }
                res.add(new int[]{newInterval[0], newInterval[1]});
            }
        }
        int[][] result = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        insert(nums,new int[]{4,8});
    }
}

