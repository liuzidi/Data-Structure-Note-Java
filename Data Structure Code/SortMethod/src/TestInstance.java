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
        int len = 15000;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 2000000000); // 生成随机数
        }
//        for (int i = 0; i < len; i++) {
//            arr[i] = i; // 生成有序数组
//        }

        long startTime = System.currentTimeMillis();
        QuickSortRandom.quickSort(arr);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("随机快排运行时间为" + totalTime + "ms");

        startTime = System.currentTimeMillis();
        QuickSortNormal.quickSort(arr);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("普通快排运行时间为" + totalTime + "ms");

        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(arr);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("归并排序运行时间为" + totalTime + "ms");

        startTime = System.currentTimeMillis();
        InsertionSort.insertionSort(arr);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("选择排序运行时间为" + totalTime + "ms");

    }
}
