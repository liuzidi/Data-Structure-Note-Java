package LeetCode;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author:liuzidi
 * @Description:
 *
 * */

public class topHot100_23 {
    @Test
    public void test1(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l5;
        l3.next = l4;
        l2.next = l6;
        ListNode[] lists = new ListNode[]{l1,l3,l2};
        mergeKLists3(lists);
    }
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode resHead = new ListNode();
        ListNode cur = resHead;
        int cnt = 0;
        while(cnt < lists.length){
            int index = 0;
            cnt = 0;
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < lists.length; i++){
                if(lists[i] == null){
                    cnt++;
                }else if(lists[i].val <= min){
                    min = lists[i].val;
                    cur.next = lists[i];
                }
            }
            if(cur != null) cur = cur.next;
        }
        return resHead.next;
    }
    public ListNode mergeKLists2(ListNode[] lists){
        class InnerClass implements Comparable<InnerClass>{
            ListNode node;
            InnerClass(){}
            InnerClass(ListNode node){
                this.node = node;
            }
            @Override
            public int compareTo(InnerClass o) {
                return this.node.val - o.node.val;
            }
        }
        PriorityQueue<InnerClass> queue = new PriorityQueue<>();
        for(ListNode node : lists){
            if(node != null)
            queue.offer(new InnerClass(node));
        }
        ListNode resHead = new ListNode();
        ListNode cur = resHead;
        while(!queue.isEmpty()){
            ListNode minNode = queue.poll().node;
            cur.next = minNode;
            cur = cur.next;
            if(minNode!= null){
                queue.offer(new InnerClass(minNode.next));
            }
        }
        return resHead.next;
    }
    @Test
    public void test2(){

        ListNode[] lists = new ListNode[]{null};
    }

    public ListNode mergeKLists3(ListNode[] lists){
        ListNode res = mergeSort(lists, 0 , lists.length - 1);
        return res;
    }
    public ListNode mergeSort(ListNode[] lists, int l, int r){
        if(l == r){
            return lists[l];
        }
        int mid = (l + r) / 2;
        ListNode leftNode = mergeSort(lists,l,mid);
        ListNode rightNode = mergeSort(lists,mid + 1,r);
        return mergeTwoListNode(leftNode,rightNode);

    }
    private ListNode mergeTwoListNode(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode();
        ListNode cur = head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }


}
