package LeetCode.Medium_Algorithm;

import LeetCode.ListNode;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA15 {
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pre, cur;
        pre = null;
        cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
