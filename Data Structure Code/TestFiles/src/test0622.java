/**
 * @author:liuzidi
 * @Description:
 */
public class test0622 {
    public static void main(String[] args) {
        QS qs = new QS();
        int[] nums = {6,75,45,25,4,1,5,82,15,82,5,3,41,2};
        qs.quickSort(nums,0,nums.length - 1);
        for(int num : nums){
            System.out.println(num);
        }
    }

}
class QS{
    public void quickSort(int[] nums, int l, int r){
        if(l >= r) return;
        int pivot = l + (int)((r - l + 1) * Math.random());
        int pValue = nums[pivot];
        swap(nums, l, pivot);
        int left = l, right = r;
        while(left < right){
            while(left < right && nums[right] >= pValue){
                right--;
            }
            while(left < right && nums[left] <= pValue){
                left++;
            }
            if(left < right){
                swap(nums, left, right);
            }
        }
        swap(nums, l, left);
        quickSort(nums, l, left - 1);
        quickSort(nums, left + 1, r);
    }
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
