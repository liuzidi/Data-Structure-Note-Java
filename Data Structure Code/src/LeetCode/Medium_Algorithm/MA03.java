package LeetCode.Medium_Algorithm;
/**
 * @author:liuzidi
 * @Description:
 */
public class MA03 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    public Node connect(Node root) {
        DFS(root);
        return root;
    }
    public void DFS(Node root){
        if(root == null) return;
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
            else
                root.right.next = null;
        }

        DFS(root.left);
        DFS(root.right);
    }
}
