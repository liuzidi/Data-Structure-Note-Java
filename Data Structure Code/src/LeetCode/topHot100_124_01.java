package LeetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_124_01 {

    @Test
    public void test1(){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1; t2.right = t3;
        System.out.println(maxPathSum(t2));

    }

    private Map<TreeNode, TreeNode> map = new HashMap<>();
    private int max;
    public int maxPathSum(TreeNode root) {
        map.put(root, null);
        max = root.val;
        DFS(root);
        DFS2(root);
        return this.max;
    }
    public void DFS(TreeNode root){
        if(root == null) return;
        if(root.left != null)
        map.put(root.left, root);
        if(root.right != null)
        map.put(root.right, root);
        DFS(root.left);
        DFS(root.right);
    }
    public void recurse(TreeNode root, Set<TreeNode> set, int count){
        if(root == null || set.contains(root)) return;
        set.add(root);
        count += root.val;
        max = Math.max(count, max);

        TreeNode father = map.get(root);
        recurse(father, set, count);
        recurse(root.left, set, count);
        recurse(root.right, set, count);
    }
    public void DFS2(TreeNode root){
        if(root == null) return;
        Set<TreeNode> set = new HashSet<>();
        recurse(root,set,0);
        DFS2(root.left);
        DFS2(root.right);
    }
}
