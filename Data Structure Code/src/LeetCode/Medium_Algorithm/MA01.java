package LeetCode.Medium_Algorithm;

import LeetCode.ListNode;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA01 {
        public ListNode oddEvenList(ListNode head) {
            if(head == null) return null;
            ListNode oddIdx, evenIdx,oddHead,evenHead;
            evenIdx = head;
            oddIdx = head.next;
            oddHead = oddIdx;
            evenHead = evenIdx;
            while(oddIdx != null){
                evenIdx.next = oddIdx.next;
                if(evenIdx.next == null){
                    break;
                }
                evenIdx = evenIdx.next;
                oddIdx.next = evenIdx.next;
                oddIdx = oddIdx.next;
            }
            evenIdx.next = oddHead;
            return evenHead;
        }

}
