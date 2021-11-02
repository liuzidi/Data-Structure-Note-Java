package LeetCode.classicProblem;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author:liuzidi
 * @Description:
 */
public class P06 {
    public static void main(String[] args) {
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t4.left = t3; t4.right = t6;
        t3.left = t2; t6.left = t5; t6.right = t7;
        List<Integer> list = inorder(t4);
        System.out.println(list);
    }
    public static List<Integer> preorder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            list.add(cur.val);
            if(cur.right != null)
            stack.push(cur.right);
            if(cur.left != null)
            stack.push(cur.left);
        }
        return list;
    }

    /**
     * 每个栈顶的数代表这个数的左子树是空的，而且被取出来了
     * @param root
     * @return
     */
    public static List<Integer> inorder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root= root.right;
        }
        return list;
    }

    public static List<Integer> postorder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            list.add(cur.val);
            if(cur.left != null)
                stack.push(cur.left);
            if(cur.right != null)
                stack.push(cur.right);
        }
        return list;
    }



}
