package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_124_02 {

    @Test
    public void test1(){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1; t2.right = t3;
        System.out.println(maxPathSum(t2));

    }

    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return max;
    }
    public int maxGain(TreeNode node){
        if(node == null) return 0;
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        max = Math.max(max, leftGain + rightGain + node.val);
        return Math.max(leftGain, rightGain) + node.val;
    }


}
