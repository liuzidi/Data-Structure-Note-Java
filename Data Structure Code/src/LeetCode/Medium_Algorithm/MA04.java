package LeetCode.Medium_Algorithm;

import LeetCode.TreeNode;

import java.util.PriorityQueue;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA04 {
    public PriorityQueue<Integer> pq = new PriorityQueue<>();
    public int kthSmallest(TreeNode root, int k) {
        DFS(root);
        for(int i = 0; i < k - 1; i++){
            pq.poll();
        }
        return pq.poll();
    }
    public void DFS(TreeNode root){
        if(root == null) return;
        pq.add(root.val);
        DFS(root.left);
        DFS(root.right);
    }

}
