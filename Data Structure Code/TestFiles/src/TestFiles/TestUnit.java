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
         ListNode head =new ListNode();
         ListNode h1,h2,h3,h4,h5;
         h5 =new ListNode(5,null);
         h4 =new ListNode(4,h5);
         h3 =new ListNode(3,h4);
         h2 =new ListNode(2,h3);
         h1 =new ListNode(1,h2);
         head.next = h1;

         ListNode currentIndex = removeNthFromEnd(head,1);
         while(currentIndex.next != null){
             currentIndex = currentIndex.next;
             System.out.println(currentIndex.val);
         }
     }
     public static ListNode removeNthFromEnd(ListNode head, int n) {
         ListNode currentIndex = head;
         int total = 0;//计算有几个节点
         while(currentIndex.next != null){
             currentIndex = currentIndex.next;
             total++;
         }
         System.out.println("total ="+ total);
         currentIndex = head;
         for(int i = 0; i < total - n; i++){
             currentIndex = currentIndex.next;
         }//目前指针指向目标的上一个
         if(currentIndex.next.next != null){
             currentIndex.next = currentIndex.next.next;
             return head;
         }
         currentIndex.next = null;
         return head;
     }

}
