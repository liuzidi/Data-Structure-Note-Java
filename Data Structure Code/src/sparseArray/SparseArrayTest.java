package sparseArray;

/**
 * @author:liuzidi
 * @Description:稀疏数组方法测试，实现了稀疏数组和展开数组之间的转化
 */
public class SparseArrayTest {
    public static void main(String[] args) {
        //随机初始化原先的数组
        int[][]initArray=new int[7][8];
        initArray[0][5]=3;
        initArray[1][6]=23;
        initArray[2][5]=58;
        initArray[3][4]=86;
        initArray[4][5]=48;
        initArray[6][0]=5;
        //新建一个稀疏数组类对象并调用转化方法；
        SparseArray s =new SparseArray();
        int[][]sparseArray=s.arrayToSparse(initArray);
        int[][]arr=s.SparseToArray(sparseArray);
    }
}
