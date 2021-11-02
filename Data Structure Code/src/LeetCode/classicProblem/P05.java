package LeetCode.classicProblem;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 */
public class P05 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(7);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);

                                   //t1
            t1.left = t2;                               t1.right = t4;
        t2.left = t3;  t2.right = null;         t4.left = t5;           t4.right = t8;
        t5.left = t6;   t5.right = t7;

        List<Integer> list = findPath(t1, 7);
        for(int i : list){
            System.out.println(i);
        }

        //该树的中序遍历顺序为：(1,2,3,4,5,6,7,8)
    }
    public static List<Integer> findPath(TreeNode root, int target){
        List<Integer> res =  DFS(root, target);
        Collections.reverse(res);
        return res;
    }
    public static List<Integer> DFS(TreeNode root, int target){
        if(root == null){
            return null;
        }
        if(root.val == target){
            List<Integer> res = new ArrayList<>();
            res.add(root.val);
            return res;
        }
        List<Integer> listLeft = DFS(root.left, target);
        List<Integer> listRight = DFS(root.right, target);
        if(listLeft == null && listRight == null){
            return null;
        }
        if(listLeft == null){
            listRight.add(root.val);
            return listRight;
        }
        if(listRight == null){
            listLeft.add(root.val);
            return listLeft;
        }
        if(listLeft.size() < listRight.size()){
            listLeft.add(root.val);
            return listLeft;
        }else{
            listRight.add(root.val);
            return listRight;
        }
    }
}
