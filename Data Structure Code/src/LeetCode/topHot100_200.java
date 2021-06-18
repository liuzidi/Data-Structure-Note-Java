package LeetCode;

/**
 * @author:liuzidi
 * @Description:
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

