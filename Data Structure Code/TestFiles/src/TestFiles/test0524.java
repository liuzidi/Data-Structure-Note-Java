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
        t1.left = t2;
    }


}
class Solution0524 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null){
            return false;
        }
        if(helper(A,B)){
            return true;
        }
        return helper(A.left,B) || helper(A.right,B);
    }
    public boolean helper(TreeNode A, TreeNode B){
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