package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class topHot100_79 {
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
