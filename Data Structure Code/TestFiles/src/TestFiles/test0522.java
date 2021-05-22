package TestFiles;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liuzidi
 * @Description:
 */
public class test0522 {
}
class Solution0522 {
    public int[] preorder;
    public Map<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        this.preorder = preorder;
        map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);//记录每个节点在前序数组的位置，用来索引根节点的位置
        }
        return helper(0, 0, inorder.length - 1);

    }
    //使根节点连接左子树和右子树的根节点的递归函数
    //中序数组是为了帮助前序区分左子树和右子树的分界线的工具
    //前序是用来寻找根节点的工具：每颗子树的最左边元素即为根节点
    public TreeNode helper(int root, int left, int right){
        // root： 前序数组中的索引（根节点的索引）
        // left：中序数组中的左边界  right ：中序数组中的右边界
        //即给定一个根节点，以及其子树所覆盖的左右边界。可以得出其左子树和右子树的边界，并将其连接
        if(left > right){
            return null;
        }
        int rootIndexInOrder =  map.get(preorder[root]);
        TreeNode rootNode = new TreeNode(preorder[root]);
        rootNode.left = helper(root + 1, left, rootIndexInOrder - 1);
        rootNode.right = helper(root + rootIndexInOrder - left + 1, rootIndexInOrder + 1, right);
        //可以理解为右根节点的位置为：左子树根节点位置（在前序数组中）向右移动左子树覆盖的长度
        return rootNode;
    }
}
