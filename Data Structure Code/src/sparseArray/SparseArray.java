package sparseArray;


/**
 @author:liuzidi
 @Description:

 数据结构-稀疏数组:
 1.应用情形：当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 2.处理方法：
 1)	记录数组一共有几行几列，有多少个不同的值
 2)	把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 3.实现功能：
 实现数组和稀疏数组可以互相转换;(以数组类型为int为例子)
 */
public class SparseArray {


    /**
     *
     * @param arr:原始数组
     * @return sparseArray：对应的稀疏数组
     * 功能：将原始数组转化为对应的稀疏数组并返回
     */
    public int[][] arrayToSparse(int[][]arr){
        int sum = 0; //非0元素个数,初始化为0
        //输出原始的数组，并记录初始数组有sum个非0元素
        System.out.println("初始数组为:");
        for(int[]row:arr){
            for(int data:row){
                System.out.print(data+"\t");
                if(data!=0){
                    sum++;
                }
            }
            System.out.println();
        }
        //创建稀疏二维数组，int[sum+1][3]sparseArray
        int[][]sparseArray=new int[sum+1][3];
        sparseArray[0][0]=arr.length;//行数
        sparseArray[0][1]=arr[0].length;//列数
        sparseArray[0][2]=sum;//非0个数
        int curColumn =1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if(arr[i][j]!=0){
                    sparseArray[curColumn][0]=i;
                    sparseArray[curColumn][1]=j;
                    sparseArray[curColumn][2]=arr[i][j];
                    curColumn++;
                }
            }
        }
        //输出稀疏数组
        System.out.println("对应的稀疏数组为:");
        System.out.println("row\t"+"column\t"+"value");
        for(int[]row:sparseArray){
            for(int data:row){
                System.out.print(data+"\t");
            }
            System.out.println();
        }
        return sparseArray;
    }

    /**
     *
     * @param sparseArr
     * @return arr：展开后的数组
     *功能：将稀疏数组转化为原始数组并返回
     */
    public int[][]SparseToArray(int[][]sparseArr){
        //输出稀疏数组
        System.out.println("原稀疏数组为");
        System.out.println("row\t"+"column\t"+"value");
        for(int[]row:sparseArr){
            for(int data:row){
                System.out.print(data+"\t");
            }
            System.out.println();
        }
        //展开稀疏数组为数组
        //1.创建数组
        int[][]arr =new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.展开数组,其他默认为0
        for (int i = 1; i < sparseArr[0][0]; i++) {
                arr[sparseArr[i][0]] [sparseArr[i][1]]=sparseArr[i][2];
        }
        System.out.println("展开后的数组为：");
        for(int[]row:arr){
            for(int data:row){
                System.out.print(data+"\t");
            }
            System.out.println();
        }
        return arr;
    }

}
