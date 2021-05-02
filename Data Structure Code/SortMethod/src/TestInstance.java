/**
 * @author:liuzidi
 * @Description:
 */
public class TestInstance {
    public static void main(String[] args) {
//        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
//        QuickSortNormal.quickSort(nums);
//        for(int num : nums){
//            System.out.println(num);
//        }
        /**
         * test2
         */
        int len = 10000000;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * len); // 生成随机数
        }
//        for (int i = 0; i < len; i++) {
//            arr[i] = i; // 生成有序数组
//        }

        long startTime = System.currentTimeMillis();
        QuickSortRandom.quickSort(arr);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("随机快排运行时间为" + totalTime + "ms");

//        startTime = System.currentTimeMillis();
//        QuickSortNormal.quickSort(arr);
//        endTime = System.currentTimeMillis();
//        totalTime = endTime - startTime;
//        System.out.println("普通快排运行时间为" + totalTime + "ms");

        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(arr);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("归并排序运行时间为" + totalTime + "ms");

        startTime = System.currentTimeMillis();
        InsertionSort.insertionSort(arr);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("插入排序运行时间为" + totalTime + "ms");

        startTime = System.currentTimeMillis();
        ShellSort.shellSort(arr);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("希尔排序运行时间为" + totalTime + "ms");


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
 *
 */
