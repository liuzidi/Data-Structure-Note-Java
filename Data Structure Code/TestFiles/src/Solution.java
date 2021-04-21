import java.util.Arrays;

/**
 * @author:liuzidi
 * @Description:
 */
public class Solution {
    public  static TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        if(nums.length == 1){
            return new TreeNode(nums[0], null, null);
        }
        TreeNode root = new TreeNode();
        return SonTree(nums, 0 ,nums.length - 1, root);
    }
    public static TreeNode SonTree(int[] nums, int leftIndex, int rightIndex, TreeNode root){//原数组，子树的左索引，子树的右索引
        int middleIndex = (leftIndex + rightIndex) >> 1;
        if(leftIndex == rightIndex){
            return new TreeNode(nums[leftIndex]);
        }
        root = new TreeNode(nums[middleIndex]);
        if(middleIndex == leftIndex){
            root.left = null;
        }
        else{root.left = SonTree(nums, leftIndex, middleIndex - 1, root);}
        root.right = SonTree(nums, middleIndex + 1, rightIndex, root);
        return root;
    }

    public static void main(String[] args) {
        int[] nums ={1,3};
        TreeNode test = sortedArrayToBST(nums);
        Arrays.sort(nums);

    }

}
