package LeetCode.classicProblem;

import java.util.PriorityQueue;

/**
 * @author:liuzidi
 * @Description:
 */
public class P03 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x1,x2)->(x2 - x1));
        for(int num : nums){
            pq.offer(num);
        }
        int res = 0;
        for(int i = 0; i < k; i++){
            res = pq.poll();
        }
        return res;
    }
}
