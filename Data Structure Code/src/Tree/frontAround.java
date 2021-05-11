package Tree;

/**
 * @author:liuzidi
 * @Description:
 * 递归写法
 */
public class frontAround {
    public static void PreOrderTraverse(TreeNode t){
        if(t == null) return;
        System.out.println(t.val);
        PreOrderTraverse(t.left);
        PreOrderTraverse(t.right);
    }
    public static void InOrderTraverse(TreeNode t){
        if(t == null) return;
        InOrderTraverse(t.left);
        System.out.println(t.val);
        InOrderTraverse(t.right);
    }

    public static void PostOrderTraverse(TreeNode t){
        if(t == null) return;
        PostOrderTraverse(t.left);
        PostOrderTraverse(t.right);
        System.out.println(t.val);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode8 = new TreeNode(8);


        treeNode5.left = treeNode3;
        treeNode5.right = treeNode6;
        treeNode3.left = treeNode2;
        treeNode3.right = treeNode4;
        treeNode2.left = treeNode1;
        treeNode6.right = treeNode8;

        PostOrderTraverse(treeNode5);
    }
}
