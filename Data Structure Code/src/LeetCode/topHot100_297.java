package LeetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 * 源数据：[1,2,3,null,null,4,5]
 */
public class topHot100_297 {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1;
        t2.right = t3;
        Codec ser = new Codec();
        Codec deser = new Codec();
        String s = ser.serialize(t2);
//        TreeNode ans = deser.deserialize("[1,2,3,null,null,4,5]");
//        String s = ser.serialize(ans);
    }

    public static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return serializeDFS(root, "");
        }
        public String serializeDFS(TreeNode root, String res){
            if (root == null){
                res += "null";
            }else {
                res += root.val + ",";
                res = serializeDFS(root.left, res);
                res = serializeDFS(root.right, res);
            }
            return res;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] dataString = data.split(",");
            List<String> list = new ArrayList<>(Arrays.asList(dataString));
            return deserializeDFS(list);
        }

        public TreeNode deserializeDFS(List<String> list){
            if(list.get(0).equals("null")){
                list.remove(0);
                return null;
            }
            TreeNode t = new TreeNode(Integer.parseInt(list.get(0)));
            list.remove(0);
            t.left = deserializeDFS(list);
            t.right = deserializeDFS(list);
            return t;
        }

    }
}
