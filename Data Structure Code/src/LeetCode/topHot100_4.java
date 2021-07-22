package LeetCode;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
 */
public class topHot100_4 {
    @Test
    public void test1(){
        int[] nums1 = {1,3,5,6,8};
        int[] nums2 = {2,3,4,5};
        double i = findMedianSortedArrays2(nums1, nums2);
    }
    public int sum(int n, int count){
        if(n == 0){
            return count;
        }
        count += n;
        return sum(n - 1,count);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int sum = nums1.length + nums2.length;
        int index1, index2,value1,value2;
        if(sum % 2 == 1){
            index1 = sum / 2;
            index2 = index1;
        }else{
            index1 = sum / 2 - 1;
            index2 = index1 + 1;
        }
        int[] map = new int[sum];
        int i1 = 0, i2 = 0,cnt = 0;
        while(i1 < nums1.length && i2 < nums2.length){
            if(nums1[i1] < nums2[i2]){
                map[cnt] = nums1[i1];
                i1++;
            }else{
                map[cnt] = nums2[i2];
                i2++;
            }
            cnt++;
        }
        if(i1 < nums1.length){
            System.arraycopy(nums1, i1, map, cnt, nums1.length - i1);
        }
        if(i2 < nums2.length){
            System.arraycopy(nums2, i2, map, cnt, nums2.length - i2);
        }
        value1 = map[index1];
        value2 = map[index2];
        return (double)(value1 + value2) / 2;
    }
    //二分法
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int sum = nums1.length + nums2.length;
        int index1 = (sum + 1)/2;
        int index2 = (sum + 2)/2;
        if(index1 == index2){
            return getKth(nums1,0,nums2,0,index1);
        }else return (double)(getKth(nums1,0,nums2,0,index1) +
                getKth(nums1,0,nums2,0,index2)) /2;
    }
    private int getKth(int[] nums1,int start1, int[] nums2, int start2, int k){
        if(start1 >= nums1.length){
            return nums2[start2 + k - 1];
        }
        if(start2 >= nums2.length){
            return nums1[start1 + k - 1];
        }
        if(k == 1){
            return Math.min(nums1[start1], nums2[start2]);
        }
        int halfK = k / 2;
        if(halfK + start1 > nums1.length){
            return getKth(nums1, start1,nums2, start2 + halfK, k -halfK);
        }
        if(halfK + start2 > nums2.length){
            return getKth(nums1, start1 + halfK, nums2,start2, k -halfK);
        }
        int value1 = nums1[halfK + start1 - 1];
        int value2 = nums2[halfK + start2 - 1];
        if(value1 < value2){
            return getKth(nums1, start1 + halfK, nums2,start2, k -halfK);
        }else{
            return getKth(nums1, start1,nums2, start2 + halfK, k -halfK);
        }
    }
}
