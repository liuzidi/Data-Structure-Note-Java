package LeetCode.classicProblem;

import LeetCode.ListNode;
import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class P04 {
    @Test
    public void test1(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode aa = reverseKGroup(l1, 2);
        System.out.println(aa);
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return head;
        int step = 0;
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null && step < k){
            cur = cur.next;
            step++;
        }
        if(step < k){
            return head;
        }
        cur = head;
        for(int i = 0; i < k; i++){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        ListNode nextHead = reverseKGroup(cur,k);
        head.next = nextHead;
        return pre;
    }
}
