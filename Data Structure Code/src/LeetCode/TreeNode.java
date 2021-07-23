package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
        public TreeNode(int x, TreeNode left, TreeNode right){
            val = x;
            this.left = left;
            this.right = right;
        }
        public TreeNode(){}
}
