package TestFiles;

/**
 * @author:liuzidi
 * @Description:
 */
public class LinkedListTest {
    public static void main(String[] args) {

    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode currIndex1 = l1;
        ListNode currIndex2 = l2;
        ListNode head = new ListNode();
        ListNode curr = head;
        while(currIndex1 != null || currIndex2 != null){
            if(currIndex1 == null){//l1链表走到头 但是l2还有元素未添加
                curr.next =currIndex2;
                return head.next;
            }
            if(currIndex2 == null){//l2链表走到头 但是l1还有元素未添加
                curr.next =currIndex1;
                return head.next;
            }
            if(currIndex1.val < currIndex2.val){
                curr.next = currIndex1;
                curr = curr.next;
                currIndex1 = currIndex1.next;
            }else{
                curr.next = currIndex2;
                curr = curr.next;
                currIndex2 = currIndex2.next;
            }
        }
        return head.next;
    }
}

