package sparseArray;

import org.jetbrains.annotations.NotNull;

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
    private int row;//行
    private int column;//列
    private int value;//行列对应的值
    private int sum = 0; //非0元素个数,初始化为0
    public void arrayToSparse(int[][]arr){
        //输出原始的数组
        System.out.println("初始数组为:");
        for(int[]row:arr){
            for(int data:row){
                System.out.print(data+"\t");
            }
            System.out.println();
        }
        //计算初始数组有几个非0元素，
    }

}
