package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_437 {
    private int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        DFS(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right,targetSum);
        return res;
    }
    private void DFS(TreeNode root, int target){
        if(root == null) return;
        target -= root.val;
        if(target == 0) res++;
        DFS(root.left, target);
        DFS(root.right, target);
    }
}

