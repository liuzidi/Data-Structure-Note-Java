/**
 * @author:liuzidi
 * @Description:
 */
public class TestInstance {
    public static void main(String[] args) {
        int len = 50000;
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        int[] arr3 = new int[len];
        int[] arr4 = new int[len];
        int[] arr5 = new int[len];
        int[] arr6 = new int[len];
        for (int i = 0; i < len; i++) {
            arr1[i] = (int) (Math.random() * 10); // 生成随机数
            arr2[i] = arr1[i];
            arr3[i] = arr2[i];
            arr4[i] = arr3[i];
            arr5[i] = arr4[i];
            arr6[i] = arr5[i];
        }
        /**
         * 有序数组
         */

//        for (int i = 0; i < len; i++) {
//            arr[i] = i; // 生成有序数组
//        }

        long startTime1 = System.currentTimeMillis();
        QuickSortDoubleRoad.quickSort(arr1);
        long endTime1 = System.currentTimeMillis();
        long totalTime1 = endTime1 - startTime1;
        System.out.println("双路随机快排运行时间为" + totalTime1 + "ms");

//        startTime = System.currentTimeMillis();
//        QuickSortNormal.quickSort(arr);
//        endTime = System.currentTimeMillis();
//        totalTime = endTime - startTime;
//        System.out.println("普通快排运行时间为" + totalTime + "ms");

        long startTime2 = System.currentTimeMillis();
        MergeSort.mergeSort(arr2);
        long endTime2 = System.currentTimeMillis();
        long totalTime2 = endTime2 - startTime2;
        System.out.println("归并排序运行时间为" + totalTime2 + "ms");

//        long startTime3 = System.currentTimeMillis();
//        InsertionSort.insertionSort(arr3);
//        long endTime3 = System.currentTimeMillis();
//        long totalTime3 = endTime3 - startTime3;
//        System.out.println("插入排序运行时间为" + totalTime3 + "ms");

        long startTime4 = System.currentTimeMillis();
        ShellSort.shellSort(arr4);
        long endTime4 = System.currentTimeMillis();
        long totalTime4 = endTime4 - startTime4;
        System.out.println("希尔排序运行时间为" + totalTime4 + "ms");

        long startTime5 = System.currentTimeMillis();
        QuickSortRandom.quickSort(arr1);
        long endTime5 = System.currentTimeMillis();
        long totalTime5 = endTime5 - startTime5;
        System.out.println("普通随机快排运行时间为" + totalTime5 + "ms");


    }
}
/**
 * 改进前：
 * 生成的100000000随机数据个数组
 * 第一次：
 * 随机快排运行时间为12142ms
 * 归并排序运行时间为4080ms
 * 插入排序运行时间为96ms
 * 希尔排序运行时间为2180ms
 *
 * 第二次：
 * 随机快排运行时间为12238ms
 * 归并排序运行时间为4208ms
 * 插入排序运行时间为91ms
 * 希尔排序运行时间为2184ms
 *
 *
 *
 * 生成有序100000000数组(一开始就排好了)
 *
 * 第一次：
 * 随机快排运行时间为4458ms
 * 归并排序运行时间为3372ms
 * 插入排序运行时间为90ms
 * 希尔排序运行时间为2192ms
 *
 * 第二次：
 * 随机快排运行时间为5039ms
 * 归并排序运行时间为3257ms
 * 插入排序运行时间为91ms
 * 希尔排序运行时间为2217ms
 *
 * 生成的10000000（一千万）随机数据个数组：
 *
 * 随机快排运行时间为1072ms
 * 归并排序运行时间为456ms
 * 插入排序运行时间为12ms
 * 希尔排序运行时间为197ms
 *
 * 随机快排运行时间为1091ms
 * 归并排序运行时间为442ms
 * 插入排序运行时间为12ms
 * 希尔排序运行时间为196ms
 *
 * 优化后：
 * 随机快排运行时间为1067ms
 * 归并排序运行时间为460ms
 * 插入排序运行时间为12ms
 * 希尔排序运行时间为204ms
 *
 *
 */
