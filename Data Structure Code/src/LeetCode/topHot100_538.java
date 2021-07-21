package LeetCode;

/**
 * @author:liuzidi
 * @Description:把二叉搜索树转换为累加树
 */
public class topHot100_538 {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root != null){
            convertBST(root.right);
            root.val += sum;
            sum = root.val;
            convertBST(root.left);
        }
        return root;
    }
}
