package LeetCode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author:liuzidi
 * @Description:
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *。
 */
public class SwordOffer_41 {
    class MedianFinder {

        /** initialize your data structure here. */

        Queue<Integer> big,small;
        int size = 0;
        public MedianFinder(){
            big = new PriorityQueue<>();
            small = new PriorityQueue<>((x, y)->(y - x));
        }

        public void addNum(int num) {
           if(small.isEmpty()){
               small.add(num);
               size++;
               return;
           }
           int smallTop = small.peek();
           if(num <= smallTop){
               small.add(num);
           }else{
               big.add(num);
           }
           size++;
           int sizeBig = big.size();
           int sizeSmall = small.size();
           if(sizeBig - sizeSmall == 2){
               small.add(big.poll());
           }
           if(sizeSmall - sizeBig == 2){
               big.add(small.poll());
           }
        }

        public double findMedian() {
            if(size % 2 == 1){
                if(big.size() > small.size()) return (double) big.peek();
                else return (double) small.peek();
            }
            if(big.isEmpty()) return (double) small.peek();
            if(small.isEmpty()) return (double) big.peek();
            return (double)(big.peek() + small.peek()) / 2;
        }
    }
}
