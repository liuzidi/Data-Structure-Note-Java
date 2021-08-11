package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 */
public class SwordOffer_37 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        return serDFS(root, "");
    }
    public String serDFS(TreeNode root, String s){
        if(root == null){
            s += "null,";
            return s;
        }
        s += root.val + ",";
        s = serDFS(root.left, s);
        s = serDFS(root.right, s);
        return s ;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] sArr = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(sArr));
        return deserDFS(list);
    }

    public TreeNode deserDFS(List<String> list){
        if(list.get(0).equals("null")){
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        TreeNode leftTree = deserDFS(list);
        TreeNode rightTree = deserDFS(list);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

}
