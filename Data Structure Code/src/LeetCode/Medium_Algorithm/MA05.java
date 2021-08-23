package LeetCode.Medium_Algorithm;

import LeetCode.TreeNode;

import java.util.LinkedList;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA05 {
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
}
