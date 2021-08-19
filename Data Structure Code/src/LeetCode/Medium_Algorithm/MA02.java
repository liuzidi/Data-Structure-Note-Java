package LeetCode.Medium_Algorithm;

import LeetCode.TreeNode;

import java.util.*;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA02 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root);
        while(!queue2.isEmpty()){
            List<Integer> list = new ArrayList<>();
            while(!queue2.isEmpty()){
                TreeNode t = queue2.poll();
                list.add(t.val);
                queue1.add(t);
            }
            while(!queue1.isEmpty()){
                TreeNode t = queue1.poll();
                if(t.left != null){
                    queue2.add(t.left);
                }
                if(t.right != null){
                    queue2.add(t.right);
                }
            }
            if(res.size() % 2 == 0){
                Collections.reverse(list);
            }
            res.add(new ArrayList<Integer>(list));
        }
//        HashMap
        return res;
    }
}
