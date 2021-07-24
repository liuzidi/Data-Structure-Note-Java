package LeetCode;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //默认小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2)->o2 - o1);
        for(int i = 0; i < k; i++){
            pq.add(nums[i]);
        }
        int[] res = new int[nums.length - k];
        if(!pq.isEmpty())
        res[0] = pq.peek();
        for(int i = k; i < nums.length; i++){
            pq.remove(nums[i - k]);
            pq.add(nums[i]);
            res[i - k + 1] = pq.peek();
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            int left = i - k + 1;
            if (!queue.isEmpty()) {
                if (queue.size() == k || i - k == queue.peekFirst()) {
                    queue.removeFirst();
                }
                while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                    queue.removeLast();
                }
            }
            queue.addLast(i);
            if(left >= 0){
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
}
