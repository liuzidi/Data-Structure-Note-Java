package LeetCode.classicProblem;

import LeetCode.ListNode;
import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class P10 {
    @Test
    public void test1(){
        ListNode head = new ListNode(0);
        ListNode node2 = new ListNode(1);
        head.next = node2;
        rotateRight(head,4);
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        ListNode cur = head;
        int count = 0;
        ListNode tail = null;
        while(cur != null){
            count++;
            if(cur.next == null){
                tail = cur;
            }
            cur = cur.next;
        }
        if(count == 1) return head;
        int res = k % (count);
        cur = head;
        for(int i = 0; i < count - res - 1; i++){
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        tail.next = head;
        return newHead;
    }
}
