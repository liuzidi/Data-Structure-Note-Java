package Tree;

/**
 * @author:liuzidi
 * @Description:
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
    TreeNode(){}
    TreeNode(int x, TreeNode l, TreeNode r){
        val = x;
        this.left = l;
        this.right = r;
    }
}
