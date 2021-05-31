

## 数据结构与算法刷题笔记

---

#### *- write by liuzidi*

---



### 二叉树

#### 1.重建二叉树

**（限制条件：二叉树中不含有重复值的节点元素）**

通过前中序遍历或中后序遍历恢复二叉树，例如，给出前序遍历 preorder = [3,9,20,15,7]，中序遍历 inorder = [9,3,15,20,7]，返回如下的二叉树：

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
        // root： 前序数组中的索引（根节点的索引）
        // left：中序数组中的左边界  right ：中序数组中的右边界
        //即给定一个根节点，以及其子树所覆盖的左右边界。可以得出其左子树和右子树的边界，并将其连接
        if(left > right){
            return null;
        }
        int rootIndexInOrder =  map.get(preorder[root]);//获取根节点在中序数组的位置
        TreeNode rootNode = new TreeNode(preorder[root]);//创建根节点
        //连接左右子树后返回树的根节点
        rootNode.left = helper(root + 1, left, rootIndexInOrder - 1);
        rootNode.right = helper(root + 1 + rootIndexInOrder - left, rootIndexInOrder + 1, right);
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

```java
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



### 递归

#### 斐波那契数列

相关题型：青蛙跳台阶

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



### 二分查找

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



### 深度优先（DFS）

**迷宫类：**

例题：矩阵中的路径

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



### 广度优先（BFS）

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
        // int count = 0;
        // for(boolean[] a : board){
        //     for(boolean b : a){
        //         if(b){
        //             count++;
        //         }
        //     }
        // }
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



### 动态规划

例题：剪绳子I（限制条件 2 <= n <= 58 即总和不会越界）

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



例题：剪绳子II（限制条件 n <= 1000 即总和会越long界）

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



### 数学

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

#### 2.打印最大n位数

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



### 链表

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



### 双指针

题目：

```shell
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
```

**1.左右指针**

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

**2.快慢指针**

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

相关题型：

[链表的倒数第k个节点](#jump)

[合并两个有序链表](#jump1)

[最长不含重复字符的子字符串](#string0528)

### 栈

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



### 队列

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

### 矩阵

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

### 数组

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



### TOP k 问题

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

    // a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
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

### 动态规划

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



相关题型：

[数字翻译成字符串](#a0527)



### 字符串

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

#### 2.字符串的排列

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

我的题解：双指针

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        while(start <= end && end < chars.length){
            if(!set.add(chars[end])){
                maxLen = Math.max(maxLen, set.size());
                while(chars[start] != chars[end]){
                    set.remove(chars[start]);
                    start++;
                }
            }
            end++;
        }
        maxLen = Math.max(maxLen, set.size());
        return maxLen;
    }
}
```

题解2：双指针简洁版

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
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

