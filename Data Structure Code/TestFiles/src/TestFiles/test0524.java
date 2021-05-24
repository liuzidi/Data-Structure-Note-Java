package TestFiles;

/**
 * @author:liuzidi
 * @Description:
 */
public class test0524 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9= new TreeNode(9);
        t1.left = t2; t1.right = t3;
        t2.left = t4; t2.right = t5;
        t3.left = t6; t3.right = t7;
        t4.left = t8; t4.right = t9;

        TreeNode b1 = new TreeNode(4);
        TreeNode b2 = new TreeNode(8);
        TreeNode b3 = new TreeNode(9);
        b1.left = b2; b1.right = b3;

        System.out.println(Solution0524.isSubStructure(t1, t4));
    }


}
class Solution0524 {
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null){
            return false;
        }
        if(helper(A,B)){
            return true;
        }
        return helper(A.left,B) || helper(A.right,B);
    }
    public static boolean helper(TreeNode A, TreeNode B){
        if(A == null && B == null)
            return true;
        if(A == null || B == null)
            return false;
        if(A.val == B.val){
            return helper(A.left, B.left) && helper(A.right, B.right);
        }
        return false;
    }
}