package LeetCode;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_221 {
    public static void main(String[] args) {
        char[][] chars = new char[][]{{'0','1'},{'1','0'}};
        int i = maximalSquare(chars);
    }
    public static int maximalSquare(char[][] matrix) {
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
