

## 数据结构与算法 - LeetCode刷题笔记

*- write by liuzidi*



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



### 斐波那契数列

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



### 深度优先算法/回溯算法

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



### 广度优先算法（BFS）

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

