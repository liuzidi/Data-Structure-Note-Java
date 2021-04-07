package sparseArray;

/**
 * @author:liuzidi
 * @Description:
 */
public class SparseArrayTest {
    public static void main(String[] args) {
        //随机初始化原先的数组
        int[][]chess=new int[7][8];
        chess[0][5]=3;
        chess[1][6]=23;
        chess[2][5]=58;
        chess[3][4]=86;
        chess[4][5]=48;
        chess[6][0]=5;


        SparseArray s =new SparseArray();
        s.arrayToSparse(chess);

    }
}
