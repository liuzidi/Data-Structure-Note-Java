package SortMethod;

/**
 * @author:liuzidi
 * @Description:
 * int[ ] nums数组的长度为length，
 * 则nums[ i ]的左子节点是nums[2 * i + 1]，右子节点是nums[2 * i + 2]
 * 第一个非叶子结点是nums[ length  / 2 - 1]
 */
public class HeapSort {

    public void sort(int[] nums){
        //构建大顶堆
        for(int i = nums.length / 2 - 1; i >= 0; i--){
            adjustHeap(nums, i, nums.length);
        }
        for(int i = nums.length - 1; i >= 0; i--){
            swap(nums, 0, i);
            adjustHeap(nums,0, i);
        }
    }

    public void adjustHeap(int[] nums, int parent, int length){
        int temp = nums[parent]; // temp保存当前父节点
        int child = 2 * parent + 1; // 先获得左孩子

        while (child < length) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < length && nums[child] < nums[child + 1]) {
                child++;
            }
            //至此child指的是两个孩子较大的哪个的下标，也就是可能要和父节点交换的下标
            if (temp >= nums[child]) {
                break;
            }
            // 把孩子结点的值赋给父结点
            nums[parent] = nums[child];
            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }

        //此时parent为交换的最下标
        nums[parent] = temp;
    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}

class ssddfdfd{
    public static void main(String[] args) {
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        ChooseSort.sort(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }
}



