package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 */
public class topHot100_56 {
    public static void main(String[] args) {

    }
    public static int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return null;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0];
        });
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int low =  res.get(res.size() - 1)[0];
            int high = res.get(res.size() - 1)[1];
            int newlo = intervals[i][0];
            int newhi = intervals[i][1];
            if(newhi > high && newlo <= high){
                res.get(res.size() - 1)[1] = newhi;
            }
            if(newlo > high){
                res.add(new int[]{newlo, newhi});
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
