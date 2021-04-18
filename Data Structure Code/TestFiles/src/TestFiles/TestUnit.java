package TestFiles;

import org.junit.Test;

import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class TestUnit {
}
 class test{
     public static void main(String[] args) {
         ListNode h1,h2,h3,h4,h5;
         h5 =new ListNode(5,null);
         h4 =new ListNode(4,h5);
         h3 =new ListNode(3,h4);
         h2 =new ListNode(2,h3);
         h1 =new ListNode(1,h2);

         ListNode currentIndex = reverseList(h1);
         while(currentIndex != null){
             System.out.println(currentIndex.val);
             currentIndex = currentIndex.next;
         }
     }
     public static ListNode reverseList(ListNode head) {
         if (head == null || head.next == null){
             return head;
         }
         ListNode reverse = reverseList(head.next);
         head.next.next = head;
         head.next = null;
         return reverse;
     }
}
