package Tree;

/**
 * @author:liuzidi
 * @Description:
 * 实现一个中序线索化二叉树
 */
public class ThreadBinaryTree {
    ThreadBinaryTree left, right;
    int val;
    boolean isLeftNode, isRightNode;
    ThreadBinaryTree(int val){this.val = val;}
}

class TranslateBT2TBT{
    private ThreadBinaryTree t;
    /**
     *
     * @param head 二叉树的头结点
     * @return 对应的中序线索化二叉树
     */
    public static ThreadBinaryTree trans(TreeNode head){
        return null;
    }
    private void set(TreeNode head){

    }

    public ThreadBinaryTree getT() {
        return t;
    }
}

class CreateBT{
    private TreeNode t1;
    public CreateBT(){
        t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);

                                        //t1
                t1.left = t2;                               t1.right = t4;
        t2.left = t3;  t2.right = null;         t4.left = t5;           t4.right = t8;
                                        t5.left = t6;   t5.right = t7;

        //该树的中序遍历顺序为：(1,2,3,4,5,6,7,8)
    }

    public TreeNode getHead() {
        return t1;
    }
}
