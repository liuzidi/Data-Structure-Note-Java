## 数据结构与算法刷题笔记

---

#### *- write by liuzidi*

---



### 一. 二叉树

#### 1.重建二叉树

**（限制条件：二叉树中不含有重复值的节点元素）**

通过前中序遍历或中后序遍历恢复二叉树，例如，给出前序遍历 preorder = [3,9,20,15,7]，中序遍历 inorder = [9,3,15,20,7]，返回如下的二叉树：

前序遍历特点：每棵树的第一个元素必是根节点，用来确定当前树根节点是哪个，存储的是指导根节点是哪个的信息

中序遍历特点：根节点的左边为左子树的内容，右边为右子树的内容，用来区分左右子树的内容，存储的是位置信息

**重建方法：拿着前序遍历查到的根节点，通过map在中序遍历查到位置，以及子树在中序数组中的范围left - root - right**

```shell
  3
 / \
9  20
  /  \
 15   7
```
```java
class Solution {
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
        // root： 前序数组中的根节点的位置（根节点的索引）
        // left：中序数组中的左边界  right ：中序数组中的右边界
        //即给定一个根节点，以及其子树所覆盖的左右边界。可以得出其左子树和右子树的边界，并将其连接
        if(left > right){
            return null;
        }
        int mid =  map.get(preorder[root]);//获取根节点在中序数组的位置
        TreeNode rootNode = new TreeNode(preorder[root]);//创建根节点
        //连接左右子树后返回树的根节点
        rootNode.left = helper(root + 1, left, mid - 1);
        rootNode.right = helper(root + 1 + mid - left, mid + 1, right);
        //可以理解为右根节点的位置为：左子树根节点位置（在前序数组中）向右移动左子树覆盖的长度
        return rootNode;
    }
}
```

#### 2.树的子结构

题目：

```shell
输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

     3
    / \
   4   5
  / \
 1   2
给定的树 B：

   4 
  /
 1
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
```

我的题解：

```java
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null){
            return false;
        }
        if(helper(A,B)){
            return true;
        }
        return isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }
    public boolean helper(TreeNode A, TreeNode B){
        if(B == null)
            return true;
        if(A == null && B != null)
            return false;
        if(A.val == B.val){
            return helper(A.left, B.left) && helper(A.right, B.right);
        }
        return false;
    }
}
```

优化版

```java
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null){
            return false;
        }
        if(helper(A,B)){
            return true;
        }
        return isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }
    boolean recur(TreeNode A, TreeNode B) {
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
```

#### 3.二叉树的镜像

题目：

```shell
请完成一个函数，输入一个二叉树，该函数输出它的镜像。
例如输入：
     4
   /   \
  2     7
 / \   / \
1   3 6   9
镜像输出：
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

题解：

```java
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
      
        return root;
    }
}
```

舒服的题解：

```java
public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftRoot = mirrorTree(root.right);
        TreeNode rightRoot = mirrorTree(root.left);
        root.left = leftRoot;
        root.right = rightRoot;
        return root;
 }
```

新建二叉树返回：

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode res = new TreeNode(root.val);
        res.left = invertTree(root.right);
        res.right = invertTree(root.left);
        return res;
    }
}
```



#### 4.判断二叉树对称

题目：

```shell
请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
    1
   / \
  2   2
   \   \
   3    3
```

题解：

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
        return true;
        return rec(root.left, root.right);
    }
    public boolean rec(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null || left.val != right.val)
            return false;
        return rec(left.left, right.right) && rec(left.right, right.left);
    }
}
```

简洁写法：

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }
    boolean recur(TreeNode L, TreeNode R) {
        if(L == null && R == null) return true;
        if(L == null || R == null || L.val != R.val) return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
    }
}
```

#### <span id="ppp">5.从上往下打印二叉树</span>

题目：（返回List）

```shell
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
例如:
给定二叉树: [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：
[
  [3],
  [9,20],
  [15,7]
]
```

题解：（采用队列）

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);  
        while(!queue.isEmpty()){
            int len = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left != null){
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
            res.add(list);
        }    
        return res; 
    }
}
```

题目：（返回int数组）

```shell
从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
例如:
给定二叉树: [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
返回：
[3,9,20,15,7]
```

题解：

```java
class Solution {
    public int[] levelOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left != null){
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
        }
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
```

题目（分奇偶不同顺序打印）

```shell
请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
例如:
给定二叉树: [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：
[
  [3],
  [20,9],
  [15,7]
]
```

题解：

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null){
            queue.add(root);
        } 
        while(!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if(res.size() % 2 == 0) 
                    list.addLast(node.val); // 偶数层 -> 队列头部
                else 
                    list.addFirst(node.val); // 奇数层 -> 队列尾部
                if(node.left != null) 
                    queue.add(node.left);
                if(node.right != null) 
                    queue.add(node.right);
            }
            res.add(list);
        }
        return res;
    }
}
```

#### 6.二叉树和为某值的路径

题目：

```shell
输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
示例:
给定如下二叉树，以及目标和 target = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:
[
   [5,4,11,2],
   [5,8,4,5]
]
```

题解：（前序遍历过程中完成任务）前序遍历，深度优先算法（DFS），回溯算法

```java
class Solution {
    private LinkedList<List<Integer>> res = new LinkedList<>();
    private LinkedList<Integer> temp = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        helper(root, target);
        return res;
    }
    public void helper(TreeNode root, int target){
        if(root == null) return;
        temp.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null & root.right == null){
            res.add(new LinkedList(temp));
        }
        helper(root.left,target);
        helper(root.right,target);
        temp.removeLast();//到最后一层后仍不符合要求，则删除一位向上回溯

    }
}
```

#### 7.二叉搜索树转双向链表

题目：

```shell
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
```

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png)

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png)

题解：（利用中序遍历）

```java
class Solution {
    Node head, pre;
    public Node treeToDoublyList(Node root) {
        if(root==null) return null;
        dfs(root);
        pre.right = head;
        head.left = pre;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
        return head;
    }
    public void dfs(Node root){
        if(root == null) return;
        dfs(root.left);
        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if(pre == null){//反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
            head = root;
        }else{
            pre.right = root;    
        }
        root.left = pre;
        pre = root;
        //pre指向当前的cur
        dfs(root.right);
        //全部迭代完成后，pre指向双向链表中的尾节点
    }
}
```

#### 8.合并二叉树

题目：

```shell
给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。。
```

题解：

```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        TreeNode res = new TreeNode(root1.val + root2.val);
       	res.left = mergeTrees(root1.left, root2.left);
        res.right = mergeTrees(root1.right, root2.right);
        return res;
    }
}
```

#### 9.二叉树的直径

题目：

```shell
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
```

题解：(在求深度的代码中增加了一行)

```java
class Solution {
    private int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; 
        }
        int L = depth(node.left); 
        int R = depth(node.right); 
        ans = Math.max(ans, L + R + 1); // 增加的代码
        return Math.max(L, R) + 1; 
    }
}
```

#### <span id = "diffTree">10.不同的二叉搜索树</span>

题目：

```shell
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
```

题解：动态规划
$$
G（n） = \sum_{i = 1}^{n}{G(i - 1)G(n - i)}
$$

```java
class Solution {
    public int numTrees(int n) {
        if(n < 1) return -1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
```

#### 11.实现前缀树（Trie）

题目：

```shell
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。

示例：

输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True

```

题解：（我的题解）内部类

```java
class Trie {
    class Node{
        //只要非空即存在，所在的字符值即为上一层的nTree数组的索引
        public Node[] nTree = new Node[26];//子节点
        public boolean isWord = false;//到此为止是否为单词
        public Node(){}
        public boolean isSonExit(char c){
            return nTree[c - 'a'] != null;
        }
    }
    Node head;
    /** Initialize your data structure here. */
    public Trie() {
        head = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        Node cur = head;
        for (char c : chars){
            if(cur.nTree[c - 'a'] == null)
                cur.nTree[c - 'a']= new Node();
            cur = cur.nTree[c - 'a'];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Node cur = head;
        for(char c : chars){
            if(cur.isSonExit((c))){
                cur = cur.nTree[c - 'a'];
            }
            else return false;
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Node cur = head;
        for(char c : chars){
            if(cur.isSonExit((c))){
                cur = cur.nTree[c - 'a'];
            }
            else return false;
        }
        return true;
    }
}
```

官方题解：

```java
class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
    
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
```

#### 12.二叉树最近公共祖先

题目：

```shell
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
```

题解：（2679ms）

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q) return root;
        if(isAncestor(root.left, p) && isAncestor(root.left, q)){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(isAncestor(root.right, p) && isAncestor(root.right, q)){
            return lowestCommonAncestor(root.right, p, q);
        }
        else return root;
    }
    private boolean isAncestor(TreeNode t, TreeNode son){
        if(t == son) return true;
        else if(t == null) return false;
        else return isAncestor(t.left, son) || isAncestor(t.right, son);
    }
}
```

官方题解：

核心思想，当p和q在某个根节点的异侧时，这个根节点位p和q的祖先，向下DFS，再往上回溯寻找最接近的祖先

![img](https://pic.leetcode-cn.com/0724b87055c4bc4d744ab64775e6eefa348777c0ea0b07a00ff917773f4b494e-Picture18.png)

递归解析：
终止条件：
当越过叶节点，则直接返回null ；
当 root 等于 p,q ，则直接返回root ；
递推工作：
开启递归左子节点，返回值记为left ；
开启递归右子节点，返回值记为right ；
返回值： 根据 left 和 right ，可展开为四种情况；
当left 和right 同时为空 ：说明root 的左 / 右子树中都不包含p,q ，返回null ；
当 left 和 right 同时不为空 ：说明 p,q 分列在root 的 异侧 （分别在 左 / 右子树），因此root 为最近公共祖先，返回root ；
当 left 为空 ，right 不为空 ：p,q 都不在root 的左子树中，直接返回 right 。具体可分为两种情况：
p,q 其中一个在root 的 右子树 中，此时 right 指向 p（假设为 p ）；
p,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
当 left 不为空 ，right 为空 ：与情况 3. 同理；
观察发现， 情况 1. 可合并至 3. 和 4. 内，详见文章末尾代码。

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null && right == null) return null; // 1.
        if(left == null) return right; // 3.
        if(right == null) return left; // 4.
        return root; // 2. if(left != null and right != null)
    }
}
```

#### 13.路径总和II

题目：

```shell
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
```

![img](数据结构与算法-LeetCode刷题.assets/pathsum3-1-tree.jpg)

题解：

```java
class Solution {
    private int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        DFS(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right,targetSum);
        return res;
    }
    private void DFS(TreeNode root, int target){
        if(root == null) return;
        target -= root.val;
        if(target == 0) res++;
        DFS(root.left, target);
        DFS(root.right, target);
    }
}
```

#### 14.二叉搜索树转换累加树

题目：

```shell
给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
提醒一下，二叉搜索树满足下列约束条件：
节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。
```

题解：

```java
class Solution {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root != null){
            convertBST(root.right);
            root.val += sum;
            sum = root.val;
            convertBST(root.left);
        }
        return root;
    }
}
```



### 二. 尾递归

#### 1.青蛙跳台阶

```shell
写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
```



```shell
一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
```

迭代写法

```java
class Solution {
    public int fib(int n) {         
        int a = 0, b = 1, sum;
        while(n >= 1){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
            n--;
        }
        return a;
    }
}
```

尾递归写法

```java
class Solution {
    public int fib(int n) {         
        return helper(n , 0 , 1);
    }
    public int helper(int n, int first, int second){
        if(n == 0){
            return first;
        }
        return helper(n - 1, second, (first + second) % 1000000007);
    }
}
```



### 三. 二分查找

例题：返回旋转有序数组的最小值

限制条件：只能在有序的数组中（或半有序）进行，且如果数组存在重复的数字，返回的结果不确定。

经典写法：

```java
 public static int BS(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){     //left可以和right相等，否则left，right，middle三值相等时无返回
            int middle = left + (right - left) / 2;
            int temp = nums[middle];
            if(temp == target){
                return middle;
            }else if(temp > target){
                right = middle - 1; //无论左右都需要变化，否则将陷入死循环
            }else{
                left = middle + 1;
            }
        }
        return -1;
    }
```



### 四. 深度优先（DFS）

#### 1.迷宫类：

##### 1.1.矩阵中的路径

```shell
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
```

题解：

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0)
        return false;
        char[] wordArr = word.toCharArray();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(helper(board, i, j, 0, wordArr)){
                    return true;
                }
            }
        }
        return false;      
    }
    public boolean helper(char[][] board, int row, int column, int index, char[] wordArr){
        if(row < 0 || column < 0 || row >= board.length || column >= board[0].length){
            return false;
        }
        if(board[row][column] == wordArr[index]){
            board[row][column] = ' ';
            if(index == wordArr.length - 1){
                return true;
            }else{
                boolean res = 
                    helper(board, row + 1, column, index + 1, wordArr) ||
                    helper(board, row, column + 1, index + 1, wordArr) ||
                    helper(board, row - 1, column, index + 1, wordArr) ||
                    helper(board, row, column - 1, index + 1, wordArr);
                board[row][column] = wordArr[index];
                return res;
            }           
        }
        return false;
    }
}
```

**DFS 解析：**

- 递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。-
- **终止条件：**
  1. 返回 falsefalse ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 
  2. 返回 truetrue ： k = len(word) - 1 ，即字符串 word 已全部匹配。

- **递推工作：**

1. 标记当前矩阵元素： 将 

   ```
   board[i][[j]
   ```

   修改为 空字符 '' ，代表此元素已访问过，防止之后搜索时重复访问。

2. 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res 。

3. 还原当前矩阵元素： 将 

   ```
   board[i][j]
   ```

   元素还原至初始值，即 word[k] 。

- **返回值**： 返回布尔量 res ，代表是否搜索到目标字符串。

##### 1.2.单词搜索

题目：

```shell
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
```

我的题解：

```java
class Solution {
    private boolean[][] visited;
    private char[][] board;
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        this.board = board;
        this.visited = new boolean[board.length][board[0].length];
        char[] wordChars = word.toCharArray();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(DFS(0, wordChars, i, j)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean DFS(int curIndex, char[] wordChars, int row, int column){
        if(row < 0 || column < 0 || row >= board.length || column >= board[0].length 
        || visited[row][column] || wordChars[curIndex] != board[row][column]) return false;

        if(curIndex == wordChars.length - 1) return true;

        visited[row][column] = true;
        boolean res;
        res = DFS(curIndex + 1, wordChars, row - 1, column)||
        DFS(curIndex + 1, wordChars, row + 1, column) ||
        DFS(curIndex + 1, wordChars, row, column - 1) ||
        DFS(curIndex + 1, wordChars, row, column + 1);
        visited[row][column] = false;
        curIndex--;
        return res;
    }
}
```

题解2：

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }
}
```

##### 1.3岛屿数量

题目：

```shell
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3
```

题解：

```java
class Solution {
    private int count = 0;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                DFS(grid, visited, i ,j, false);
            }
        }
        return count;
    }
    public void DFS(char[][] grid, boolean[][] visited, int row, int col, boolean flag){
        if(row < 0 || row >= visited.length || col < 0 || col >= visited[0].length || visited[row][col]
    || grid[row][col] == '0') return;
        if(!flag) count++;
        visited[row][col] = true;
        DFS(grid, visited, row + 1, col,true);
        DFS(grid, visited, row - 1, col,true);
        DFS(grid, visited, row, col + 1,true);
        DFS(grid, visited, row, col - 1,true);
    }
}
```

题解2：

```java
class Solution {
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }
        return num_islands;
    }
}
```



#### 2.排列组合类

##### 2.1.括号生成

题目：

```shell
数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合
```

题解：(我的)

```java
class Solution {
    private List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if(n <= 0) return res;
        helper(new StringBuffer(""), n, '(');
        return res;
    }
    private void helper(StringBuffer sb, int n, char c){   
        sb.append(c);
        String s = sb.toString();
        if(s.length() == 2 * n && isValid(s, n) == 0){
            res.add(s);
            return;
        }
        if(isValid(s, n) == -1){
            return;
        }
        if(isValid(s, n) == 1){
            helper(sb, n , '(');
            sb.deleteCharAt(sb.length() - 1);
            helper(sb, n, ')');
            sb.deleteCharAt(sb.length() - 1);
        }
        if(isValid(s, n) == 0){
            helper(sb, n , '(');
            sb.deleteCharAt(sb.length() - 1);
        }        
    }
    private int isValid(String s, int n){//返回-1：非法， 返回0：只能添加'('，返回1：两个都能添加
        char[] chars = s.toCharArray();
        int leftCount = 0;
        int rightCount = 0;
        for(char c : chars){
            if(c == '(') leftCount++;
            if(c == ')') rightCount++;
        }
        if(leftCount < rightCount || leftCount > n || rightCount > n){
            return -1;
        }else if(leftCount == rightCount){
            return 0;
        }else{
            return 1;
        }
    }
}
```

题解：

```java
class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if(n <= 0){
            return res;
        }
        getParenthesis("",n,n);
        return res;
    }

    private void getParenthesis(String str,int left, int right) {
        if(left == 0 && right == 0 ){
            res.add(str);
            return;
        }
        if(left == right){
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(str+"(",left-1,right);
        }else if(left < right){
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if(left > 0){
                getParenthesis(str+"(",left-1,right);
            }
            getParenthesis(str+")",left,right-1);
        }
    }
}
```

##### 2.2 组合总和

题目：

```shell
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的数字可以无限制重复被选取。
输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
```

题解：

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, new ArrayList<Integer>(), 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, List<Integer> combine, int idx) {
        if (idx >= candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 继续选择当前的数字进行添加
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], combine, idx);
            combine.remove(combine.size() - 1);
        }
        // 添加下一个数
        dfs(candidates, target, combine, idx + 1);
    }
}
```

##### 2.3 全排列

题目：

```shell
给定一个不含重复数字的数组nums，返回其所有可能的全排列。你可以按任意顺序返回答案。
示例：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

题解：（只适合不重复元素的排列，重复的排列考虑进去的话，详见字符串的排列）

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        DFS(nums, new ArrayList<Integer>(), new boolean[nums.length]);
        return this.res;
    }
    private void DFS(int[] nums, List<Integer> list, boolean[] visited){
        if(list.size() == nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            list.add(nums[i]);
            visited[i] = true;
            DFS(nums, list, visited);
            visited[i] = false;      
            list.remove(list.size() - 1);
        }
    }
}
```

题解：交换法

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
```

##### 2.4.子集

题目：

```shell
给你一个整数数组 nums，数组中的元素互不相同。返回该数组所有可能的子集（幂集）。
解集不能包含重复的子集。你可以按 任意顺序 返回解集。
```

题解1：迭代实现

{5，2，9}的子集

| 序列 | 子集    | 序列对应的二进制数 |
| :--: | ------- | ------------------ |
| 000  | {}      | 0                  |
| 001  | {9}     | 1                  |
| 010  | {2}     | 2                  |
| 011  | {2,9}   | 3                  |
| 100  | {5}     | 4                  |
| 101  | {5,9}   | 5                  |
| 110  | {5,2}   | 6                  |
| 111  | {5,2,9} | 7                  |

```java
class Solution {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }
}
```

题解2：回溯算法（每个数字不重复，因此都有选和不选的两种状态，选-下一个DFS ，不选-下一个DFS）

```java
class Solution {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}
```

[字符串的排列](#combine1)

[电话号码的组合](#combine2)

### 五. 广度优先（BFS）

例题：机器人的运动范围

```shell
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
```

我的解法：

```java
class Solution {
    public int movingCount(int m, int n, int k) {
        boolean[][] board = new boolean[m][n];
        helper(board, 0, 0, k);
        int count = 0;
        for(boolean[] a : board){
            for(boolean b : a){
                if(b){
                    count++;
                }
            }
        }
        return count;
    }
    public void helper(boolean[][] board, int row, int column, int value){
        if(row < 0 || column < 0 || row >= board.length || column >= board[0].length ||
         board[row][column] || !isBig(row,column,value)){
            return;
        }
        board[row][column] = true;
        helper(board, row + 1, column, value);
        helper(board, row - 1, column, value);
        helper(board, row, column + 1, value);
        helper(board, row, column - 1, value);
    }
    public boolean isBig(int m, int n, int value){
        int res = 0;
        while(m != 0 || n != 0){
            res += (m % 10) + (n % 10);
            m /= 10;
            n /= 10;
        }        
        return res <= value;
    }
}
```

优化后的解法：

```java
class Solution {
    private int res = 0;
    public int movingCount(int m, int n, int k) {
        boolean[][] board = new boolean[m][n];
        helper(board, 0, 0, k);
        return this.res;
    }
    public void helper(boolean[][] board, int row, int column, int value){
        if(row < 0 || column < 0 || row >= board.length || column >= board[0].length ||
         board[row][column] || !isBig(row,column,value)){
            return;
        }
        board[row][column] = true;
        res++;
        helper(board, row + 1, column, value);
        helper(board, row - 1, column, value);
        helper(board, row, column + 1, value);
        helper(board, row, column - 1, value);
    }
    public boolean isBig(int m, int n, int value){
        return m / 10 + m % 10 + n / 10 + n % 10 <= value;
    }
}
```



### 六. 数学

#### 1.快速幂

```shell
实现 [pow(x, n)]，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
```

题解：

```java
class Solution {
    public double myPow(double x, int n) {
       if(n == 0)
       return 1;
       long longN = n;//当一个数是整个int时，最大负数转正数时会越界，需要long型进行接收
       if(n < 0){
           x = 1 / x;
           longN = -longN; //错误写法：longN = -n（n为最小的负数右边会直接越界变为0）
       }
       double res = 1.0;
       while(longN > 0){
           if(longN % 2 == 1){
               res *= x;
           }
           x *= x;
           longN /= 2;
       }
       return res;
    }
}
```

```shell
复杂度分析：
时间复杂度 O(log n):二分的时间复杂度为对数级别。
空间复杂度 O(1) : 变量占用常数大小额外空间。
```

#### <span id= "topninarr">2.打印最大n位数</span>

例题：

```shell
输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
```

不考虑大数解法：

```java
class Solution {
    public int[] printNumbers(int n) {
        int len = 0;
        for(int i = 0; i < n; i++){
            len *= 10;
            len += 9;
        }      
        int[] res = new int[len];
        for(int i = 1; i <= len; i++){
            res[i - 1] = i;
        }
        return res;
    }
}
```

考虑大数算法（用String作为输出）

```java
class Solution {
    StringBuilder res;
    int count = 0, n;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public String printNumbers(int n) {
        this.n = n;
        res = new StringBuilder(); // 数字字符串集
        num = new char[n]; // 定义长度为 n 的字符列表
        dfs(0); // 开启全排列递归
        res.deleteCharAt(res.length() - 1); // 删除最后多余的逗号
        return res.toString(); // 转化为字符串并返回
    }
    void dfs(int x) {
        if(x == n) { // 终止条件：已固定完所有位
            res.append(String.valueOf(num) + ","); // 拼接 num 并添加至 res 尾部，使用逗号隔开
            return;
        }
        for(char i : loop) { // 遍历 ‘0‘ - ’9‘
            num[x] = i; // 固定第 x 位为 i
            dfs(x + 1); // 开启固定第 x + 1 位
        }
    }
}
```

#### 3.数字队列中某一位的数字

题目：

```shell
数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

请写一个函数，求任意第n位对应的数字。
```

我的题解：

(n - 1) % digit表示第几位

(n - 1) / digit 表示从start开始第几个数

Long.toString(num).charAt((n - 1) % digit) - '0' 快速索引

```java
class Solution {
    public int findNthDigit(int n) {
         if(n < 10){
             return n;
         }
         int bit = 1;//位数
         while(n / Math.pow(10, bit - 1) / bit / 9 > 1){
             n -= Math.pow(10, bit - 1) * bit * 9;
             bit++;
         }
         int num = ((n - 1) / bit) + (int)(Math.pow(10, bit - 1));//处于这个数中的某个数
         int fig = n % bit;//这个数的第几位
         if(fig == 0) fig = bit;
         for(int i = 0; i < bit - fig; i++){
             num /= 10;
         }
         return (num % 10);
    }
}
```

参考题解：

```java
class Solution {
    public int findNthDigit(int n) {
        int digit = 1; //位数
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 所在数字
        return Long.toString(num).charAt((n - 1) % digit) - '0'; //n - 1 % digit表示第几位
    }
}
```

#### 4.丑数

题目：

```shell
我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
```

我的题解：（暴力解法）

```java
class Solution {
    public int nthUglyNumber(int n) {
        int index = 0;
        int res = 1;
        while(index != n){
            int temp = res;
            while(temp % 2 == 0) temp /= 2;
            while(temp % 3 == 0) temp /= 3;
            while(temp % 5 == 0) temp /= 5;
            if(temp == 1){
                index++;
            }
            res++;
        }
        return res - 1;
    }
}
```

题解2：（空间换时间，采用一段数组来保存丑数）三指针法，三路归并算法

所有丑数的排列，必定就是上面ABC3个数组的合并结果然后去重得到的，那么这就转换成了三个有序数组的无重复元素合并的问题。

```java
class Solution {
    public int nthUglyNumber(int n) {
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        int nextIndex = 1;
        int[] res = new int[n];
        res[0] = 1;
        while(nextIndex < n){
            int temp = min(res[index2] * 2, res[index3] * 3, res[index5] * 5);
            if(res[index2] * 2 == temp) index2++;
            if(res[index3] * 3 == temp) index3++;
            if(res[index5] * 5 == temp) index5++;
            res[nextIndex] = temp;
            nextIndex++;
        }
        return res[n - 1];
    }
    private int min(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
}
```

题解3：动态规划

```java
class Solution {
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n - 1];
    }
}
```

#### 5.三数之和

题目：

```shell
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。
```

题解：

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int k = 0; k < nums.length - 2; k++){
            if(nums[k] > 0) break;
            if(k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;
            while(i < j){
                int sum = nums[k] + nums[i] + nums[j];
                if(sum < 0){
                    while(i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while(i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while(i < j && nums[i] == nums[++i]);
                    while(i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }
}
```

#### 6.下一个排列

题目：

```shell
实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
必须 原地 修改，只允许使用额外常数空间。

示例 1：
输入：nums = [1,2,3]
输出：[1,3,2]

示例 2：
输入：nums = [3,2,1]
输出：[1,2,3]

示例 3：
输入：nums = [1,1,5]
输出：[1,5,1]

示例 4：
输入：nums = [1]
输出：[1]
```

我的题解：（交换 + 快排）

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int left = - 1;
        int right = nums.length - 1;
        for(int i = nums.length - 1; i >= 1; i--){
            if(nums[i] > nums[i - 1]){
                left = i - 1;
                break;
            }
        }
        if(left != -1){
            for(int i = left + 1; i < nums.length; i++){
                if(nums[i] <= nums[left]){
                    right = i - 1;
                    break;
                }
            }
            swap(left, right, nums);
        }
        sortPart(left + 1, nums.length - 1, nums);
    }
    public void sortPart(int left, int right, int[] nums){
        if(left >= right) return;
        int pivot = left + (int)(Math.random() * (right - left + 1));
        int l = left;
        int r = right;
        int value = nums[pivot];
        swap(left, pivot, nums);
        while(l < r){
            while(l < r && nums[r] >= value) r--;
            while(l < r && nums[l] <= value) l++;
            if(l < r){
                swap(l, r, nums);
            }
        }
        swap(left, l, nums);
        sortPart(left, l - 1, nums);
        sortPart(l + 1, right, nums);
    }
    private void swap(int index1, int index2, int[] nums){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
```

官方题解：（由于要排序的一定是倒序，所以直接反转就是排序）

```java
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int firstIndex = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstIndex = i;
                break;
            }
        }
        if (firstIndex == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int secondIndex = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[firstIndex]) {
                secondIndex = i;
                break;
            }
        }
        swap(nums, firstIndex, secondIndex);
        reverse(nums, firstIndex + 1, nums.length - 1);
        return;

    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }
}
```

#### 7.除自身以外数组地乘积

题目：

```shell
给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积
```

题解：

上三角，下三角

| res         |        |        |        |            |            |
| ----------- | :----- | ------ | ------ | ---------- | ---------- |
| res[0]=     | 1      | num[1] | ...... | num[n - 2] | num[n - 1] |
| res[1]=     | num[0] | 1      | ...... | num[n - 2] | num[n - 1] |
| ......      | ...... | ...... | ...... | ......     | ......     |
| res[n - 2]= | num[0] | num[1] | ...... | 1          | num[n - 1] |
| res[n - 1]= | num[0] | num[1] | ...... | num[n - 2] | 1          |



```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int p = 1, q = 1;
        //延迟乘法，先让其等于 1.再进行合乘
        for (int i = 0; i < nums.length; i++) {
            res[i] = p;
            p *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0 ; i--) {
            res[i] *= q;
            q *= nums[i];
        }
        return res;
    }
}
```

8.完全平方数

题目：

```shell
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
```

题解:动态规划

边界条件dp[0] = 0,虽然不存在，但是可以定义

```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0 
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) { 
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }
}
```

题解2：数学公式

四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。这给出了本题的答案的上界。

```

```



### 七. 链表

#### 1.删除节点

题目：

```shell
给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
```

题解：（考虑删除头结点即可）

```java
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val)
        return head.next;
        ListNode cur = head;
        ListNode prev = null;
        while(cur.val != val){
            prev = cur;
            cur = cur.next;
        }
        prev.next = cur.next;
        return head;
    }
}
```



#### <span id="jump">2.链表的倒数第k个节点</span>

题目：

```shell
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
```

我的解法（普通思想）

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        int count = 0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            count++;
        }
        cur = head;
        for(int i = 0; i < count - k; i++){
            cur = cur.next;
        }
        return cur;
    }
}
```

题解：（不需要知道链表长度，指针1先走k-1步，然后指针2和指针1同时前进，当指针1指向链表最后一个元素时，指针2即为所求。）

```java
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode low = head;
        for(int i = 0; i < k; i++){
            fast = fast.next;
        }
        //快指针先前进k步
        while(fast != null){
            low = low.next;
            fast = fast.next;
        }
        return low;
    }
}
```

#### 3.反转链表

题目：

```shell
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
```

题解：

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
```

#### <span id="jump1">4.合并两个有序链表</span>

题目：

```shell
输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
```

我的题解：（三指针，归并思想）

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode newhead = new ListNode();
        ListNode res = newhead;
        while(cur1 != null && cur2 != null){
            if(cur1.val < cur2.val){
                newhead.next = cur1;
                cur1 = cur1.next;
            }else{
                newhead.next = cur2;
                cur2 = cur2.next;
            }
            newhead = newhead.next;
        }
        if(cur1 != null){
            newhead.next = cur1;
        }
        if(cur2 != null){
            newhead.next = cur2;
        }
        res = res.next;
        return res;
    }
}
```

简洁版

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }
}
```

#### 5.复杂链表的复制

题目：

```shell
请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

题解1（哈希法）空间复杂度O（N），每个复制的新节点都对应原节点

```java
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        //  复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while(cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
```

题解2（合并分拆法）原地复制O（1）

```java
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while(cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while(cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }
}
```

#### <span id = "intersec">6.相交链表</span>

题目：

```shell
编写一个程序，找到两个单链表相交的起始节点。
```

题解：(双指针版)（从链表头一直走，直到相遇，A链表走完走B，B走完走A，如果有相交会相遇在相交节点）

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode head1 = headA;
        ListNode head2 = headB;

        while (head1 != head2) {
            if (head1 != null) {
                head1 = head1.next;
            } else {
                head1 = headB;
            }
            if (head2 != null) {
                head2 = head2.next;
            } else {
                head2 = headA;
            }
        }
        return head1;
    }
}
```

题解：（哈希表）low

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> hashSet = new HashSet<>();
        ListNode curNode = headA;
        while (curNode != null) {
            hashSet.add(curNode);
            curNode = curNode.next;
        }
        curNode = headB;
        while (curNode != null) {
            if(hashSet.contains(curNode)){
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }
}
```

#### 7.两数相加

题目：

```shell
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
```

题解：

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode res = new ListNode();
        ListNode resCur = res;
        while(cur1 != null || cur2 != null){
            int value;
            if(cur1 == null){
                value = cur2.val + carry;
                cur2 = cur2.next;
            }else if(cur2 == null){
                value = cur1.val + carry;
                cur1 = cur1.next;
            }
            else {
                value = cur1.val + cur2.val + carry;
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            carry = value > 9 ? 1 : 0;
            value %= 10;
            resCur.next = new ListNode(value);
            resCur = resCur.next;
        }
        if(carry == 1){
            resCur.next = new ListNode(1);
        }
        return res.next;
    }
}
```

简洁版：

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
```

#### 8.环形链表

题目：

```shell
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
```

题解：（哈希表）额外空间O(n)

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(!set.add(head)){
                return head;
            }
            head = head.next;
        }
        return null;
    }
}
```

题解：（双指针法）额外空间O（1）

我们使用两个指针fast 与slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，而fast 指针向后移动两个位置。如果链表中存在环，则fast 指针最终将再次与slow 指针在环中相遇

一旦slow指针进入环形结构，则一定会在慢指针不到一圈内的环内某个位置相遇



从相遇点到入环
$$
a=c+(n−1)(b+c)
$$
点的距离加上 n-1 圈的环长，恰好等于从链表头部到入环点的距离。

![fig1](https://assets.leetcode-cn.com/solution-static/142/142_fig1.png)

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
```

#### 9.LRU缓存机制

题目：

```shell
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：
LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间

示例：
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4

```

**题解：(哈希表+双向链表)**

使用链表的原因：删除增加方便

使用哈希表原因：查询方便，不重复

使用双向链表的原因：我们需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)。

双向链表中为什么同时需要保存key和value的数值，因为删除的链表中元素时候需要同时删除哈希表中的映射，而value是无法找到key的，因此需要记录key，key：门牌号（不可变），value：有用的信息（可变化）

```java
class LRUCache {
    private Map<Integer, Node> map;//哈希表
    private Node head,tail;//双向链表中的头尾节点
    private int capacity;//最大容量
    private int size;//当前大小
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(){}
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if(map.containsKey(key)){
            removeNode(map.get(key));
        }else{
            if(size == capacity){
                removeLast();
            }else{
                size++;
            }
        }
        addFirst(node);
    }

    private void removeNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        map.remove(node.key);
    }
    private void addFirst(Node node){
        Node second = head.next;
        head.next = node;
        node.prev = head;
        node.next = second;
        second.prev = node;
        map.put(node.key, node);
    }
    private void removeLast(){
        Node secLast = tail.prev;
        map.remove(secLast.key);
        tail.prev = secLast.prev;
        secLast.prev.next = tail;
    }
}
```

#### 10.排序链表

题目：

```shell
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
```

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)

题解：（利用List中的排序）

```java
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null) return null;
        List<ListNode> res = new ArrayList<>();
        while(head != null){
            res.add(head);
            head = head.next;
        }
        res.sort((x, y) -> x.val - y.val);
        ListNode h = new ListNode();
        ListNode temp = h;
        for(ListNode node : res){
            temp.next = node;
            temp = temp.next;
        }
        temp.next = null;
        return h.next;
    }
}
```

题解：（分治算法）归并思想

```java
class Solution {
	public ListNode sortList(ListNode head) {
        // 1、递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 3、当前层业务操作（合并有序链表）
        return mergeTwoLists(left, right);
    }
    
    //  找到链表中间节点（876. 链表的中间结点）
    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 合并两个有序链表（21. 合并两个有序链表）
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }
}
```



### 八. 双指针

快慢指针：1快1慢

1.一个符合条件往前，一个一直往前

2.一个走两步，一个走一步

#### 1.调整数组顺序使奇数位于偶数前面

题目：

```shell
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
```

**题解1.左右指针**

左右交换奇数和偶数，直到全部遍历

```java
class Solution {
    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            if((nums[left] & 1) == 1 && (nums[right] & 1) == 0){
                left++; 
                right--;
            }
            else if((nums[left] & 1) == 0 && (nums[right] & 1) == 1){
                swap(left++, right--, nums);
            }
            else if((nums[left] & 1) == 1){
               left++; 
            } 
            else{
               right--;
            }
        }
        return nums;
    }
     public void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
```

**题解2.快慢指针**

low，fast指针

low遇到奇数跳过，遇到偶数停止，保存下一个奇数需要交换的位置；

fast在low位置后的第一个奇数处停止，fast寻找下一个奇数；

```java
class Solution {
public:
    public int[] exchange(int[] nums) {
        int low = 0;
        int fast = 0;
        while (fast < nums.length) {
            if ((nums[fast] & 1) == 1) {
                swap(nums,fast,low);
                low ++;
            }
            fast ++;
        }
        return nums;
    }
     public void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
```

我的做法：

```java
class Solution {
    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            if((nums[left] & 1) == 1){//左边奇数，left右移
                left++;
                continue;
            }
            if((nums[right] & 1) == 0){//左边偶数，如果右边为奇数则交换
                swap(nums,left,right);
                continue;
            }
            while(left < right && (nums[right] & 1) == 0){//左边偶数，右边左移直到寻找到一个奇数
                right--;
            }
        }
        return nums;
    }
    public void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
```

#### 2.盛最多水的容器

题目：

```shell
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
```

题解：只有移动往里面短板，才有可能得到最大的面积

```java
class Solution {
    public int maxArea(int[] height) {
        if(height.length < 2){
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while(left < right){
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
           if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
```

#### 3.颜色分类

（不用sort方法）

题目：

```shell
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
```

题解1：头尾指针

p0指向需要放0的地方，一旦遇到0就把0换到p0位置，p0加一

p2指向需要放2的地方，一旦遇到2就换到p2位置，此时需要循环到直至i指针不为2为止，因为i向前就会遗漏

```java
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }
}
```

题解2：快慢指针

```java
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }
}
```



相关题型：

[链表的倒数第k个节点](#jump)

[合并两个有序链表](#jump1)

[最长不含重复字符的子字符串](#string0528)

[相交链表](#intersec)

### 九. 栈

#### 1.最小栈

双栈实现可读最小值的功能

题目：

```shell
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
```

题解：

```java
class MinStack {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    public MinStack() {}   
    public void push(int x) {
        if(stack2.isEmpty() || x <= stack2.peek()){
            stack2.push(x);
        }
        stack1.push(x);
    }    
    public void pop() {
        if(stack1.pop().equals(stack2.peek())){//Integer类型不能用==
            stack2.pop();
        }
    } 
    public int top() {
        return stack1.peek();
    }
    
    public int min() {
        return stack2.peek();
    }
}
```



#### 2.栈的压入弹出序列

题目：

```shell
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
```

我的题解：双指针

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length == 0 || popped.length == 0)
            return true; 
        Stack<Integer> stack = new Stack<>();
        int pushedIndex = 0;
        int poppedIndex = 0;
        while(poppedIndex < popped.length && pushedIndex <= pushed.length){
            if(!stack.isEmpty() && stack.peek() == popped[poppedIndex]){
                stack.pop();
                poppedIndex++;
                continue;
            }
            if(pushedIndex < pushed.length)
                stack.push(pushed[pushedIndex]);
            pushedIndex++;         
        }
        return stack.isEmpty();
    }
}
```

简洁版：

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed) {
            stack.push(num); 
            while(!stack.isEmpty() && stack.peek() == popped[i]) { 
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
```

#### 3.字符串解码

题目：

```shell
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入
```

题解：（双栈辅助法）

```java
class Solution {    
    public String decodeString(String s) {
        char[] charArr = s.toCharArray();
        StringBuilder res = new StringBuilder();
        Stack<Integer> curNumStack = new Stack<>();
        Stack<String> curStringStack =new Stack<>();
        int curNum = 0;
        for (char c : charArr) {
            if (Character.isDigit(c)) {//若是数字
                curNum = curNum * 10 + c - '0';
            } else if (c == '[') {//若是 [
                curNumStack.push(curNum);
                curStringStack.push(res.toString());
                curNum = 0;
                res= new StringBuilder();
            } else if (Character.isLetter(c)) {//若是字母
                res.append(c);
            } else {//若是 ]
                StringBuilder temp = new StringBuilder();
                if (!curNumStack.isEmpty()) {
                    int multi = curNumStack.pop();
                    for (int j = 0; j < multi; j++) {
                        temp.append(res);
                    }
                    String curString = curStringStack.pop();
                    res = new StringBuilder(curString + temp.toString());
                }
            }
        }
        return res.toString();
    }
}
```



### 十. 队列

滑动窗口的最大值

题目：

```shell
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

我的题解：（优先队列）

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) return new int[0];
        Queue<Integer> pq = new PriorityQueue<>(k, Comparator.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < k; i++){
            pq.add(nums[i]);
        }
        res[0] = pq.peek();
        for(int i = k; i < nums.length; i++){
            pq.remove(nums[i - k]);
            pq.add(nums[i]);
            res[i - k + 1] = pq.peek();
        }
        return res;
    }
}
```

题解2：（构造单调队列）队列为单调递减，队首为最大值，队尾为最小值，每个元素都会入队，保持队列中的元素个数不大于滑动窗口的总个数（原数组上采用left，right指针进行判断即可），right的元素选择入列，并使整个队列一直保持为单调队列的形态。下面用存储下标的方式进行。           

```java
 public int[] maxSlidingWindow(int[] nums, int k) {
        // 窗口个数
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        // 遍历数组中元素，right表示滑动窗口右边界
        for(int right = 0; right < nums.length; right++) {
            // 如果队列不为空且当前考察元素大于等于队尾元素，则将队尾元素移除。
            // 直到，队列为空或当前考察元素小于新的队尾元素
            while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            // 存储元素下标
            queue.addLast(right);
            // 计算窗口左侧边界
            int left = right - k +1;
            // 当队首元素的下标小于滑动窗口左侧边界left时
            // 表示队首元素已经不再滑动窗口内，因此将其从队首移除
            if (queue.peekFirst() < left) {
                queue.removeFirst();
            }
            // 由于数组下标从0开始，因此当窗口右边界right+1大于等于窗口大小k时
            // 意味着窗口形成。此时，队首元素就是该窗口内的最大值
            if (right +1 >= k) {
                res[left] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
```

题解3：

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        // 形成窗口后
        for(int i = k; i < nums.length; i++) {
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
}
```



[从上往下打印二叉树][#qqq]

### 十一. 矩阵

#### 1.顺时针打印矩阵

题目：

```shell
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
```

题解：

```java
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return new int[0];
        }
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0;
        while(left <= right && top <= bottom){
            for(int i = left; i <= right; i++){
                res[index++] = matrix[top][i];
            }
            for(int i = top + 1; i <= bottom; i++){
                res[index++] = matrix[i][right];
            }
            if(left < right && top < bottom){ //作用是防止最后一层只有一行时，重复打印
                for(int i = right - 1; i >= left; i--){
                    res[index++] = matrix[bottom][i];
                }
                for(int i = bottom - 1; i >= top + 1; i--){
                    res[index++] = matrix[i][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
}
```

#### 2.矩阵置零

题目：

```shell
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

进阶：
一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个仅使用常量空间的解决方案吗？
```

题解1：(采用O(m + n)空间)

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] isZeros = new boolean[m + n];//前m个用来表示row，后n个用来表示column
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    isZeros[i] = true;
                    isZeros[m + j] = true;
                }
            }
        }
        for(int i = 0; i < m; i++){
            if(isZeros[i]){
                setRows(matrix, i);
            }
        }
        for(int i = m; i < m + n; i++){
            if(isZeros[i]){
                setColumns(matrix, i - m);
            }
        }
    }
    public void setRows(int[][] matrix, int row){
        for(int i = 0; i < matrix[row].length; i++){
            matrix[row][i] = 0;
        }
    }
    public void setColumns(int[][] matrix, int column){
        for(int i = 0; i < matrix.length; i++){
            matrix[i][column] = 0;
        }
    }    
}
```

题解2：采用O(1)空间

关键思想: 用matrix第一行和第一列记录该行该列是否有0,作为标志位

但是对于第一行,和第一列要设置一个标志位,为了防止自己这一行(一列)也有0的情况.注释写在代码里,直接看代码很好理解。

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        Set<Integer> row_zero = new HashSet<>();
        Set<Integer> col_zero = new HashSet<>();
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) { i
                if (matrix[i][j] == 0) {
                    row_zero.add(i);
                    col_zero.add(j);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (row_zero.contains(i) || col_zero.contains(j)) matrix[i][j] = 0;
            }
        }  
    }
}
```

3.搜索二维矩阵

题目：

```shell
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
```

题解：

从右上角的元素看作根节点，类似于二叉搜索树

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = 0, column = matrix[0].length - 1;
        while(row < matrix.length  && column >= 0){
            if(matrix[row][column] == target){
                return true;
            }else if(matrix[row][column] < target){
                row++;
            }else{
                column--;
            }
        }
        return false;
    }
}
```



### 十二. 数组

#### 1.寻找超过一半的数字

题目：寻找超过一半的数字

```shell
数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。
示例 1:
输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2
```

题解：我的解法：哈希表

```java
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int pivot = nums.length / 2 + 1;
        for(int num : nums){
            int value = map.getOrDefault(num, 0);
            map.put(num, ++value);
            if(value == pivot){
                return num;
            }
        }
        return 0;
    }
}
```

题解：摩尔投票

```java
class Solution {
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for(int num : nums){
            if(votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}
```

题解：排序

```java
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
```

#### 2.把数组排成最小的数

题目：

```shell
输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
```

题解：（快排，重写comparator）

```java
class Solution {
    public String minNumber(int[] nums) {
        String[] nums1 = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(nums1, ((String o1, String o2) ->{
            return (o1 + o2).compareTo(o2 + o1);
        }));
        StringBuilder res = new StringBuilder();
        for(String s :nums1){
            res.append(s);
        }
        return res.toString();
    }
}
```

#### 3.找到数组中所有消失的数字（数目大于1）

题目：

```shell
给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
```

题解：

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1) % n] += n;//不交换，只是做一个标记，这里有人了
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
```

4.跳跃游戏

题目：

```shell
给定一个非负整数数组 nums，你最初位于数组的 第一个下标 。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个下标。
```

我的题解：（O（n））

```java
class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int max = -1;
        for(int i = 0; i < nums.length - 1; i++){
            max--;
            max = Math.max(max, nums[i]);
            if(max == 0 && nums[i] == 0){
                return false;
            }
        }
        return true;
    }
}
```

题解:

```java
public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

#### 4.合并空间

题目：

```shell
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
```

我的题解：（排序 + 合并）

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return null;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0];
        });
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int low =  res.get(res.size() - 1)[0];
            int high = res.get(res.size() - 1)[1];
            int newlo = intervals[i][0];
            int newhi = intervals[i][1];
            if(newhi > high && newlo <= high){
                res.get(res.size() - 1)[1] = newhi;
            }
            if(newlo > high){ //此时newhi一定大于 high
                res.add(new int[]{newlo, newhi});
            }        
        }
        return res.toArray(new int[res.size()][]);
    }
}
```

#### 5.最长连续序列

题目：

```shell
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
```

我的题解：（排序后动态规划）

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int res = 1;
        int max = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] - nums[i - 1] == 1){
                res++;
            }else if(nums[i] == nums[i - 1]){
                continue;
            }else{
                res = 1;
            }
            max = Math.max(max, res);
        }
        return max;
    }
}
```

官方题解：（O（n）哈希表动态规划）

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestStreak = 0;
        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
```

#### 6.寻找重复的数

题目：

```
给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
```

题解：

与环形链表思路一致。以i->num[i]的映射关系形成的链一定存在环结构，可以用快慢指针进行求解



slow和fast会在环中相遇，先假设一些量：起点到环的入口长度为m，环的周长为c，在fast和slow相遇时slow走了n步。则fast走了2n步，fast比slow多走了n步，而这n步全用在了在环里循环（n%c==0）。
当fast和last相遇之后，我们设置第三个指针finder，它从起点开始和slow(在fast和slow相遇处)同步前进，当finder和slow相遇时，就是在环的入口处相遇，也就是重复的那个数字相遇。

为什么 finder 和 slow 相遇在入口
fast 和 slow 相遇时，slow 在环中行进的距离是n-m，其中 n%c==0。这时我们再让 slow 前进 m 步——也就是在环中走了 n 步了。而 n%c==0 即 slow 在环里面走的距离是环的周长的整数倍，就回到了环的入口了，而入口就是重复的数字。
我们不知道起点到入口的长度m，所以弄个 finder 和 slow 一起走，他们必定会在入口处相遇。

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int finder= 0;
        while(finder != slow){
            finder = nums[finder];
            slow = nums[slow];
        }
        return finder;
    }
}
```

#### 7.根据身高重建队列

题目：

```shell
假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。

请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
```

题解：

```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        int m = people[0].length;
        Arrays.sort(people,(o1,o2)->(o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]));
        List<int[]> list = new ArrayList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }
        return list.toArray(new int[list.size()][2]);
    }
}
```

#### 8.目标和

题目：

```shell
给你一个整数数组 nums 和一个整数 target 。
向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
```

题解：深度优先搜索

```java
class Solution {
    private int res = 0;
    public int findTargetSumWays(int[] nums, int target) {
        DFS(nums,target,0,0);
        return res;
    }
    private void DFS(int[] nums, int target, int index, int count){
        if(index == nums.length) {
            if(target == count){
                res++;
            }
            return;
        }
        DFS(nums, target, index + 1, count + nums[index]);
        DFS(nums, target, index + 1, count - nums[index]);
    }
}
```

题解2：动态规划：

```java
class Solution {
public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];   
    }
}
```

#### 9.和为K的子数组

题目：

```
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
```

题解：前缀和+哈希优化

```java
public class topHot100_560 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        map.put(0,1);//记录从第一个元素开始满足的解，因为是不计算第一个元素开始的
        for(int num : nums){
            sum += num;
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
```

#### 10.最短无序连续子数组

题目：

```shell
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
请你找出符合题意的 最短子数组，并输出它的长度。
```

题解：双指针快慢指针，寻找到区域的最小（大）值所在位置

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int minNum = nums[nums.length - 1];
        int left = nums.length;
        for (int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] > minNum){
                left = i;
            }
            minNum = Math.min(minNum, nums[i]);
        }
        int maxNum = nums[0];
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < maxNum){
                right = i;
            }
            maxNum = Math.max(maxNum, nums[i]);
        }
        return Math.max(right - left + 1, 0);
    }
}
```



### 十三. TOP k 问题

题目：（不分顺序）

```shell
输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4
```

#### 1.优先队列

我们可以使用一个大小为 k 的最大堆（大顶堆），将数组中的元素依次入堆，当堆的大小超过 k 时，便将多出的元素从堆顶弹出，这样，由于每次从堆顶弹出的数都是堆中最大的，最小的 k 个元素一定会留在堆里。这样，把数组中的元素全部入堆之后，堆中剩下的 k 个元素就是最大的 k 个数了。

备注：

```java
Integer.compare(x, y)
//Returns: 
//the value 0 if x == y; a value less than 0 if x < y; and a value greater than 0 if x > y 
```



```java
public int[] getLeastNumbers(int[] arr, int k) {
    if (k == 0) {
        return new int[0];
    }
    // 使用一个最大堆（大顶堆）
    // Java 的 PriorityQueue 默认是小顶堆，添加 comparator 参数使其变成最大堆
    Queue<Integer> heap = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));

    for (int e : arr) {
        // 当前数字小于堆顶元素才会入堆
        if (heap.isEmpty() || heap.size() < k || e < heap.peek()) {
            heap.offer(e);
        }
        if (heap.size() > k) {
            heap.poll(); // 删除堆顶最大元素
        }
    }

    // 将堆中的元素存入数组
    int[] res = new int[heap.size()];
    int j = 0;
    for (int e : heap) {
        res[j++] = e;
    }
    return res;
}
```



#### 2.快速排序分治法

Top K 问题的另一个解法就比较难想到，需要在平时有算法的积累。实际上，“查找第 k 大的元素”是一类算法问题，称为选择问题。找第 k 大的数，或者找前 k 大的数，有一个经典的 quick select（快速选择）算法。这个名字和 quick sort（快速排序）看起来很像，算法的思想也和快速排序类似，都是分治法的思想。

让我们回顾快速排序的思路。快速排序中有一步很重要的操作是 partition（划分），从数组中随机选取一个枢纽元素 v，然后原地移动数组中的元素，使得比 v 小的元素在 v 的左边，比 v 大的元素在 v 的右边，

![partiition](https://pic.leetcode-cn.com/6ec72e221775fad0d67a787be280257749612ca3ad8eb1799209cd0f1f12ed14.jpg)

这个 partition 操作是原地进行的，需要 O(n)O(n) 的时间，接下来，快速排序会递归地排序左右两侧的数组。而快速选择（quick select）算法的不同之处在于，接下来只需要递归地选择一侧的数组。快速选择算法想当于一个“不完全”的快速排序，因为我们只需要知道最小的 k 个数是哪些，并不需要知道它们的顺序。

```java
public int[] getLeastNumbers(int[] arr, int k) {
    if (k == 0) {
        return new int[0];
    } else if (arr.length <= k) {
        return arr;
    }    
    // 原地不断划分数组
    partitionArray(arr, 0, arr.length - 1, k);    
    // 数组的前 k 个数此时就是最小的 k 个数，将其存入结果
    int[] res = new int[k];
    for (int i = 0; i < k; i++) {
        res[i] = arr[i];
    }
    return res;
}

void partitionArray(int[] arr, int lo, int hi, int k) {
    // 做一次 partition 操作
    int m = partition(arr, lo, hi);
    // 此时数组前 m 个数，就是最小的 m 个数
    if (k == m) {
        // 正好找到最小的 k(m) 个数
        return;
    } else if (k < m) {
        // 最小的 k 个数一定在前 m 个数中，递归划分
        partitionArray(arr, lo, m-1, k);
    } else {
        // 在右侧数组中寻找最小的 k-m 个数
        partitionArray(arr, m+1, hi, k);
    }
}

int partition(int[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    int v = a[lo];
    while (true) { 
        while (a[++i] < v) {
            if (i == hi) {
                break;
            }
        }
        while (a[--j] > v) {
            if (j == lo) {
                break;
            }
        }
        if (i >= j) {
            break;
        }
        swap(a, i, j);
    }
    swap(a, lo, j);
    return j;
}

void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}
```

简洁版

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0)  //特判
            return new int[0];
        return Arrays.copyOf(arr ,quicklySort(arr, 0, arr.length - 1, k) + 1);//因为返回的是下标，所以最后要加上1
    }
    int quicklySort(int[] a, int l, int r, int k) {
        if(l >= r) return l;	//代表找到了第k个数，直接返回下标。
        int x = a[l + r >> 1];	//确定分界点
        int i = l - 1, j = r + 1;	//方便后面统一处理
        while(i < j) {			//使得x左边都小于等于x，右边都大于等于x
            while(a[++i] < x);
            while(a[--j] > x);
            if(i < j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int s = j - l + 1;	//计算左区间的个数
        if(s >= k)		//如果左区间个数比k大，代表第k个数在左区间
            return quicklySort(a, l, j, k);
        else
            return quicklySort(a, j + 1, r, k - s);
    }
}
```

**两种方法的优劣性比较**
在面试中，另一个常常问的问题就是这两种方法有何优劣。看起来分治法的快速选择算法的时间、空间复杂度都优于使用堆的方法，但是要注意到快速选择算法的几点局限性：

第一，算法需要修改原数组，如果原数组不能修改的话，还需要拷贝一份数组，空间复杂度就上去了。

第二，算法需要保存所有的数据。如果把数据看成输入流的话，使用堆的方法是来一个处理一个，不需要保存数据，只需要保存 k 个元素的最大堆。而快速选择的方法需要先保存下来所有的数据，再运行算法。当数据量非常大的时候，甚至内存都放不下的时候，就麻烦了。所以当数据量大的时候还是用基于堆的方法比较好。



#### 3.数组中的第k个最大元素

题目：

```shell
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
```

题解：

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int target = nums.length - k;
        while(true){
            int index = quickSort(nums, left, right);
            if(index == target){
                return nums[target];
            }else if(index < target){
                left = index + 1;
            }else{
                right = index - 1;
            }
        }
    }
    public int quickSort(int[] nums, int l, int r){
        int pivot = l + (int)((r - l + 1) * Math.random());
        int pValue = nums[pivot];
        swap(nums, l, pivot);
        int left = l, right = r;
        while(left < right){
            while(left < right && nums[right] >= pValue){
                right--;
            }
            while(left < right && nums[left] <= pValue){
                left++;
            }
            if(left < right){
                swap(nums, left, right);
            }
        }
        swap(nums, l, left);
        return left;
    }
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
```

#### 4.前K个高频元素

题目：

```shell
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
```

题解：（基于大顶堆）

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> occurrences = new HashMap<>();
    for (int num : nums) {
        occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
    }

    // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
    PriorityQueue<int[]> queue = new PriorityQueue<int[]>((m, n)-> m[1] - n[1]);
    for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
        int num = entry.getKey(), count = entry.getValue();
        if (queue.size() == k) {
            if (queue.peek()[1] < count) {
                queue.poll();
                queue.offer(new int[]{num, count});
            }
        } else {
            queue.offer(new int[]{num, count});
        }
    }
    int[] ret = new int[k];
    for (int i = 0; i < k; ++i) {
        ret[i] = queue.poll()[0];
    }
    return ret;
}
```

题解：（基于快速排序）

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);
        
        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }
}
```

相关题型：

[打印最大n位数](#topninarr)

### 十四. 动态规划

#### 1.连续子数组的最大和

题目：

```shell
输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。
示例1:

输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大为 6。
```

题解：（用空间复杂度O（n）来解决）

```java
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int temp = dp[0];
        for(int i = 1; i < nums.length; i++){
            temp = Math.max(0, temp) + nums[i];//保证temp从第一个正数开始加起，并加上当前的值
            //如果temp为负，说明i索引之前的所有的子数组之和都小于0，temp即不断的判断连续数组的和作为一个临时变量
            dp[i] = Math.max(dp[i - 1],temp);
        }
        return dp[nums.length - 1];
    }
}
```

题解：(用空间复杂度O（1）来解决)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int temp = dp;
        for(int i = 1; i < nums.length; i++){
            temp = Math.max(0, temp) + nums[i];
            dp = Math.max(dp,temp);
        }
        return dp;
    }
}
```

#### 2.礼物的最大价值

题目：

```shell
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
```

题解

```java
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int j = 1; j < n; j++) // 初始化第一行
            grid[0][j] += grid[0][j - 1];
        for(int i = 1; i < m; i++) // 初始化第一列
            grid[i][0] += grid[i - 1][0];
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++) 
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        return grid[m - 1][n - 1];
    }
}
```

我的题解：

```java
class Solution {
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < grid.length; i++){
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for(int i = 1; i < grid[0].length; i++){
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for(int i = 1; i < grid.length; i++){
            for(int j = 1; j < grid[0].length; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
```

#### 3.不同路径

题目：

```shell
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？
```

题解：

```java
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0){
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i][j -1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

#### 4.最小路径和

题目：

```shell
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。
```

题解：

```java
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++){
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++){
            for (int j = 1; j < grid[0].length; j++){
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
```

题解2：原地变化

```java
class Solution {
    public int minPathSum(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
```

#### 5.剪绳子I

（限制条件 2 <= n <= 58 即总和不会越界）

```shell
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
```

题解：

```java
class Solution {
    public int cuttingRope(int n) {   
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++){  //i - j >= 2
            for (int j = 1; j <= i - 2; j++){
                //有时候可能是dp[i]被逼无奈还不如不剪短的时候大
                int temp = Math.max(dp[i - j], i - j) * j;
                dp[i] = Math.max(temp, dp[i]);
            }
        }
        return dp[n];
    }
}
```

#### 6.剪绳子II

（限制条件 n <= 1000 即总和会越long界）

```shell
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
```

题解：贪心算法

```java
class Solution {
    public int cuttingRope(int n) {
        if(n < 4){
            return n - 1;
        }
        long res = 1;
        while(n > 4){
            res  = res * 3 % 1000000007;
            n -= 3;
        }
        return (int) (res * n % 1000000007);
    }
}
```

总结：剪绳子问题，实质为找规律题：尽量把绳子分为尽可能多的3，如果剩余1则和最后一个3合并为4，如果剩余2，则原来基础上乘以2即可得到最大；

- **推论一：** 将绳子以相等的长度等分为多段 ，得到的乘积最大。
- **推论二：** 尽可能将绳子以长度 33 等分为多段时，乘积最大。

#### 7.乘积最大子数组

题目：（思路和股票II的动态规划一致）

```shell
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
```

题解：

我们可以根据正负性进行分类讨论。

考虑当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，并且我们希望这个积尽可能「负得更多」，即尽可能小。如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。于是这里我们可以再维护一个f min(i)，它表示以第i个元素结尾的乘积最小子数组的乘积，那么我们可以得到这样的动态规划转移方程：

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}
```

#### 8.最大正方形

题目：

```shell
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：4

输入：matrix = [["0","1"],["1","0"]]
输出：1
```

题解：

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int max = 0;
        boolean valid = true;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                int i0 = i - max;
                int j0 = j - max;
                if(matrix[i][j] == '1' && i0 >= 0 && j0 >= 0) {
                    for(int i1 = i0; i1 <= i; i1++){
                        for(int j1 = j0; j1 <= j; j1++){
                            if(matrix[i1][j1] == '0'){
                                valid = false;
                                break;
                            }
                        }
                        if(!valid) break;
                    }
                    if(valid) max++;
                    else valid = true;
                }
            }
        }
        return max * max;
    }
}
```

官方题解：
$$
dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
$$


```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
```

#### 9.最长递增子序列

题目：

```shell
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
```

题解：动态规划+暴力查找

维护一个数组，保存第i个数作为最大值时的最大子序列的长度

```Java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 1;
        for(int i = 0; i < nums.length; i++){
            int cur = nums[i];
            dp[i] = 1;
            int temp = 1;
            for(int j = i - 1; j >= 0; j--){
                if(nums[j] < cur){
                    dp[i] = Math.max(temp + dp[j], dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

题解：贪心+二分查找

维护一个数组，保存长度为i的最大子序列的末尾元素的最小值，这个数组是有序数列，可以进行二分查找

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
```

#### 10.买卖股票含冷冻期

题目：

```shell
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
```

题解：

```java
public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
		//每卖出就不算收益
        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
```

#### 11.零钱兑换

题目:

```shell
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

你可以认为每种硬币的数量是无限的。
```

题解：

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        int[] memo = new int[amount + 1];
        for(int i = 1; i <= amount; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j++){
                if(i - coins[j] >= 0 && memo[i - coins[j]] < min){
                    min = memo[i - coins[j]] + 1;
                }
            }
            memo[i] = min;
        }
        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}
```

#### 12.打家劫舍（树）

题目：

```shell
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
```

题解：

```java
class Solution {
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }
}
```

优化后题解：

```java
class Solution {
    public int rob(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }
}
```

相关题型：

[数字翻译成字符串](#a0527)

[不同的二叉搜索树](#diffTree)



### 十五. 字符串

#### 1.第一个只出现一次的字符

题目：

```shell
在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
示例:
s = "abaccdeff"
返回 "b"

s = "" 
返回 " "
```

题解：（用数组替代哈希）

```java
class Solution {
    public char firstUniqChar(String s) {
        if(s.length() == 0) return ' ';
        char[] cArr = s.toCharArray();
        int[] count = new int[26];
        for(int num : count){
            num = 0;
        }
        for(char c : cArr){
            count[c - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < count.length; i++){
            if(count[i] == 1){
                res = Math.min(res, s.indexOf(i + 'a'));
            }
        }
        if(res == Integer.MAX_VALUE) return ' ';
        else return s.charAt(res);  
    }
}
```

优化版：（直接遍历字符串数组，而不是遍历哈希数组）//**哈希表的遍历不是按照输入顺序的**

```java
class Solution {
    public char firstUniqChar(String s) {
        if(s.length() == 0) return ' ';
        char[] cArr = s.toCharArray();
        int[] count = new int[26];
        for(int num : count){
            num = 0;
        }
        for(char c : cArr){
            count[c - 'a']++;
        }
        for(char c : cArr){
            if(count[c - 'a'] == 1){
                return c;
            }
        }
        return ' ';
    }
}
```

直接用哈希map，用boolean存储状态

```java
class Solution {
    public char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc)
            dic.put(c, !dic.containsKey(c));
        for(char c : sc)
            if(dic.get(c)) return c;
        return ' ';
    }
}
```

#### <span id="combine1">2.字符串的排列</span>

题目：（DFS）

```shell
输入一个字符串，打印出该字符串中字符的所有排列。

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

示例:
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```

题解：

不交换，时间复杂度O（n）

```java
private Set<String> res = new HashSet<>();
private char[] chars;
public String[] permutation(String s) {
    chars = s.toCharArray();
    backtrack("", new boolean[s.length()]);
    return res.toArray(new String[res.size()]);
}

private void backtrack(String temp, boolean[] visited) {
    //边界条件判断，当选择的字符长度等于原字符串长度的时候，说明原字符串的字符都已经
    //选完了
    if (temp.length() == chars.length) {
        res.add(temp);
        return;
    }
    //每一个节点我们都要从头开始选
    for (int i = 0; i < chars.length; i++) {
        //已经选择过的就不能再选了
        if (visited[i])
            continue;
        //表示选择当前字符
        visited[i] = true;
        //把当前字符选择后，到树的下一层继续选
        backtrack(temp + chars[i], visited);
        //递归往回走的时候要撤销选择
        visited[i] = false;
    }
}
```

#### <span id= "a0527">3.数字翻译成字符串 </span>

题目：

```shell
给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法
```

题解：（动态规划）（倒推相关表达式）

![Picture1.png](https://pic.leetcode-cn.com/e231fde16304948251633cfc65d04396f117239ea2d13896b1d2678de9067b42-Picture1.png)

我的题解：

```java
class Solution {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        String sArr[] = new String[len];
        for(int i = 0; i < len; i++){
            sArr[i] = Character.toString(s.charAt(i));
        }
        if(len <= 1)
            return 1;
        int[] dp = new int[len];
        dp[0] = 1;
        if((sArr[0] + sArr[1]).compareTo("10") >= 0 && (sArr[0] + sArr[1]).compareTo("25") <= 0){
            dp[1] = 2;
        }else{
            dp[1] = 1;
        }
        for(int i = 2; i < len; i++){
            String temp = sArr[i - 1] + sArr[i];
            if(temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0){
                dp[i] = dp[i - 2] + dp[i - 1];
            }else{
                dp[i] = dp[i - 1];
            }
        }
        return dp[len - 1];
    }
}
```

简洁写法：（dp数组预留一位）

```java
class Solution {
    public static int translateNum(int num) {
        char[] ch = String.valueOf(num).toCharArray();
        int len = ch.length;
        int[] dp = new int[len + 1];
        dp[0] = 1;//预留一位
        dp[1] = 1;
        for(int i = 2; i <= len; i++){
            int n = (ch[i - 2] - '0') * 10 + (ch[i - 1] - '0');
            if(n >= 10 && n <= 25){
                dp[i] = dp[i - 1] + dp[i - 2];
            }else{
                dp[i] = dp[i - 1];
            }
        }
            return dp[len];
     }
}
```

#### <span id = "string0528">4.最长不含重复字符串的子字符串</span>

题目：

```shell
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
```

题解：双指针简洁版（出现重复的最近指针的最大值作为left指针，和当前索引的差值进行判断到当时这个字符的最大长度）

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();//key:字符，value：记录上次出现的位置
        int i = -1, res = 0;
        for(int j = 0; j < s.length(); j++) {
            if(dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }
}
```

题解3：动态规划（效率低）

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for(int j = 0; j < s.length(); j++) {
            int i = j - 1;
            while(i >= 0 && s.charAt(i) != s.charAt(j)) i--; // 线性查找 i
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }
}
```

#### 5.正则表达式匹配

题目：

```
请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
```

题解：(动态规划）

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }
    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
```

#### 6.字母异位词分组

题目：

```shell
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

题解：

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(
            Arrays.stream(strs).collect(Collectors.groupingBy(
                str -> {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                return new String(array);
            })).values());
    }
}
```

题解：

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char)('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
```

#### 7.最长回文子串

题目：

```shell
给你一个字符串 s，找到 s 中最长的回文子串。
```

题解：（动态规划）

```shell
中心扩散的方法，其实做了很多重复计算。动态规划就是为了减少重复计算的问题。动态规划听起来很高大上。其实说白了就是空间换时间，将计算结果暂存起来，避免重复计算。作用和工程中用 redis 做缓存有异曲同工之妙。
我们用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。试想如果 dp[l][r] = true，我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符，是不是减少了很多重复计算。
进入正题，动态规划关键是找到初始状态和状态转移方程。
初始状态，l = r 时，此时 dp[l][r] = true。
状态转移方程，dp[l][r] = true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1] = true。
```

```java
public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度
        boolean[][] dp = new boolean[strLen][strLen];
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
```

题解2：（中心扩散法）遍历每一个字符，并以其为中心向左右扩散

```java
class Solution {
    public String longestPalindrome(String s) {
        if(s.length() < 2){
            return s;
        }
        char[] chars = s.toCharArray();
        String res = "";
        for(int i = 0; i < chars.length; i++){
            int left = i;
            int right = i;
            while(left > 0 && chars[left - 1] == chars[i]){
                left--;
            }
            while(right < chars.length - 1 && chars[right + 1] == chars[i]){
                right++;
            }
            while(left > 0 && right < chars.length - 1){
                if(chars[left - 1] == chars[right + 1]){
                    left--;
                    right++;
                }
                else break;
            }
            if(right - left + 1 > res.length()){
                res = s.substring(left, right + 1);
            }
        }
        return res;
    }
}
```

#### <span id="combine2">8.电话号码的字母组合</span>

题目：

```shell
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

提示：

0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。
```

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png" alt="img" style="zoom: 67%;" />

题解（回溯法）：当题目中出现 “所有组合” 等类似字眼时，我们第一感觉就要想到用回溯。

```java
class Solution {
    private String[] letterMap = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    private char[] digitChars;
    private List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {     
        if(digits.length() == 0) return res;
        digitChars = digits.toCharArray();
        helper(new StringBuffer(""));
        return res;
    }
    private void helper(StringBuffer s){
        if(s.length() == digitChars.length){
            res.add(s.toString());
            return;
        }
        int digit = digitChars[s.length()];
        char[] chars = letterMap[digit - '0'].toCharArray();
        for(int i = 0; i < chars.length; i++){
            s.append(chars[i]);
            helper(s);
            s.deleteCharAt(s.length() - 1);
        }
    }
}
```

#### 9.单词拆分

题目：

```shell
给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
```

题解：（动态规划）

我们可以得出如下转移方程：
dp[i]=dp[j] && check(s[j..i−1])

```java
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; //代表空字符串
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

#### 10.找到字符串所有异位词

题目：

```
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
异位词 指字母相同，但排列不同的字符串。s 和 p 仅包含小写字母
```

题解：1滑动窗口 2用数组代替哈希Map（仅包含小写字母的情况）

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()) return res;
        char[] sArr = s.toCharArray();
        int[] map = new int[26];
        int[] temp = new int[26];
        for(char c : p.toCharArray()){
            map[c - 'a']++;
        }
        int start = 0;
        int end = p.length() - 1;
        for(int i = start; i <= end; i++){
            temp[sArr[i] - 'a']++;
        }
        if(isEqual(map,temp)){
            res.add(start);
        }   
        while(end < sArr.length - 1){           
            temp[sArr[start] - 'a']--;
            start++;
            end++;
            temp[sArr[end] - 'a']++;
            if(isEqual(map, temp)){
                res.add(start);
            }
        }
        return res;
    }
    private boolean isEqual(int[] a, int[] b){
        for(int i = 0; i < 26; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
}
```



### 十六. 位运算

#### 1.比特位计数

题目：

```shell
给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回
```

题解：（普通遍历）

```java
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++){
            int temp = i;
            for(int j = 0; j < 32; j++){
                if((temp & 1) == 1){
                    res[i]++;
                }
                temp >>= 1;
            }
        }    
        return res;
    }
} 
```

题解：（动态规划： 最低有效位）

```java
class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
}
```



### 十七. 图

#### 1.课程表

题目：

```shell
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 
 
示例 2：
输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
```

**题解：(有向图的拓扑排序)**

给定一个包含 nn 个节点的有向图 GG，我们给出它的节点编号的一种排列，如果满足：
对于图 GG 中的任意一条有向边 (u, v)(u,v)，uu 在排列中都出现在 vv 的前面。
那么称该排列是图 GG 的「拓扑排序」。

对于图中的任意一个节点，它在搜索的过程中有三种状态，即：
「未搜索」：我们还没有搜索到这个节点；
「搜索中」：我们搜索过这个节点，但还没有回溯到该节点，即该节点还没有入栈，还有相邻的节点没有搜索完成）；
「已完成」：我们搜索过并且回溯过这个节点，即该节点已经入栈，并且所有该节点的相邻节点都出现在栈的更底部的位置，满足拓扑排序的要求。

通过上述的三种状态，我们就可以给出使用深度优先搜索得到拓扑排序的算法流程，在每一轮的搜索搜索开始时，我们任取一个「未搜索」的节点开始进行深度优先搜索。
我们将当前搜索的节点 u 标记为「搜索中」，遍历该节点的每一个相邻节点 v：
如果 v 为「未搜索」，那么我们开始搜索 v，待搜索完成回溯到 u；
如果 v为「搜索中」，那么我们就找到了图中的一个环，因此是不存在拓扑排序的；
如果 v 为「已完成」，那么说明 v 已经在栈中了，而 u 还不在栈中，因此 u 无论何时入栈都不会影响到 (u, v)(u,v) 之前的拓扑关系，以及不用进行任何操作。
当 u 的所有相邻节点都为「已完成」时，我们将 uu 放入栈中，并将其标记为「已完成」。

**DFS解法**

```java
class Solution {
    private List<List<Integer>> graph = new ArrayList<>();
    private int[] visited;
    private boolean valid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int[] info : prerequisites){
            graph.get(info[1]).add(info[0]);
        }
        for(int i = 0; i < numCourses && valid; i++){
            if(visited[i] == 0) DFS(i);
        }
        return valid;
    }
    // visited = 0 未访问过， = 1 访问过，但未搜索过其相邻的所有的几点， = 2 访问过，且所有相邻节点都搜索过
    //遍历过程中发现visited = 1,意味着还在搜索过程搜到一个还在处于搜索的元素，也就是形成环
    private void DFS(int i){
        visited[i] = 1;
        for(int value : graph.get(i)){
            if(visited[value] == 0){
                DFS(value);
                if(!valid){
                    return;
                }
            }else if(visited[value] == 1){
                valid = false;
                return;
            }
        }
        visited[i] = 2;
    }
}
```

**BFS题解：**

```java
class Solution {
	List<List<Integer>> graph = new ArrayList<>();
    int[] indegree;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        indegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int[] info : prerequisites){
            graph.get(info[1]).add(info[0]);
            ++indegree[info[0]];
        }
        Queue<Integer> queue= new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int visited = 0;
        while(!queue.isEmpty()){
            ++visited;
            int u = queue.poll();
            for(int value : graph.get(u)){
                --indegree[value];
                if(indegree[value] == 0){
                    queue.offer(value);
                }
            }
        }
        return visited == numCourses;//poll一定次数
    }
}
```

### 十八. 并查集

涉及元素的分组管理问题都可以考虑采用并查集进行维护

#### 1.除法求值

题目：

```shell
给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。

另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果
```

题解：

```java
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
          	// 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         *
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
```

### 十九. NP完全问题

题目：

```shell
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
```

题解：动态规划

```java
class Solution {
    public boolean canPartition(int[] nums) {
        if(nums.length < 2) return false;
        int plus = 0;
        for(int num: nums){
            plus += num;
        }
        if(plus % 2 != 0) return false;
        int target = plus / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        for(int i = 0; i < target + 1; i++){
            dp[0][i] = nums[0] == target;
        }
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < target + 1; j++){              
                if(dp[i - 1][j] || nums[i] == j){
                    dp[i][j] = true;
                }else{
                    if(j > nums[i])
                    dp[i][j] = dp[i - 1][j - nums[i]];
                    else 
                    dp[i][j] = false;
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
```

### 二十. 桶问题

#### 1.任务调度器

题目：

```shell
给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。。
```

题解：

```java
class Solution {
        public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;
        int[] map = new int[26];
        for(char task : tasks){
            map[task - 'A']++;
        }
        Arrays.sort(map);
        int cnt = 0;
        for(int m : map){
            if(m == map[25]) cnt++;
        }
        int max = map[map.length - 1];
        return Math.max(tasks.length,(max - 1) * (n + 1) + cnt);
    }
}
```

