package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 */
public class topHot100_200 {
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

//class Solution {
//    void dfs(char[][] grid, int r, int c) {
//        int nr = grid.length;
//        int nc = grid[0].length;
//        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
//            return;
//        }
//        grid[r][c] = '0';
//        dfs(grid, r - 1, c);
//        dfs(grid, r + 1, c);
//        dfs(grid, r, c - 1);
//        dfs(grid, r, c + 1);
//    }
//
//    public int numIslands(char[][] grid) {
//        if (grid == null || grid.length == 0) {
//            return 0;
//        }
//        int nr = grid.length;
//        int nc = grid[0].length;
//        int num_islands = 0;
//        for (int r = 0; r < nr; ++r) {
//            for (int c = 0; c < nc; ++c) {
//                if (grid[r][c] == '1') {
//                    ++num_islands;
//                    dfs(grid, r, c);
//                }
//            }
//        }
//        return num_islands;
//    }
//}

