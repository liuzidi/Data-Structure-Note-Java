package Tree;

import java.util.List;
import java.util.ArrayList;

public class Instance1 {
    public static int kthLargest(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        InOrderTraverse(root, list);
        return list.get(k - 1).val;
    }
    public static void InOrderTraverse(TreeNode t, List<TreeNode> list){
        if(t == null) return;
        InOrderTraverse(t.left,list);
        list.add(t);
        InOrderTraverse(t.right,list);
    }
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);


        treeNode5.left = treeNode3;
        treeNode5.right = treeNode6;
        treeNode3.left = treeNode2;
        treeNode3.right = treeNode4;
        treeNode2.left = treeNode1;
        treeNode6.right = treeNode7;

        System.out.println(kthLargest(treeNode5, 7));
    }
}
