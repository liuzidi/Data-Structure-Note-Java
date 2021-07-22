package LeetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author:liuzidi
 * @Description:
 * 给你一个用字符数组tasks 表示的 CPU 需要执行的任务列表。
 * 其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，
 * 并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 */
public class topHot100_621 {
    @Test
    public void test1(){
        char[] tasks = {'A','A','A','B','B','B'};
        leastInterval(tasks,2);
    }
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;
        int[] map = new int[26];
        for(char task : tasks){
            map[task - 'A']++;
        }
        Arrays.sort(map);
        int cnt = 0;
        for(int m : map){
            if(m == map[25]) cnt++;
        }
        int max = map[map.length - 1];
        return Math.max(tasks.length,(max - 1) * (n + 1) + cnt);
    }
}
