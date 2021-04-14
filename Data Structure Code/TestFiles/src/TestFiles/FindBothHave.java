package TestFiles;

import java.util.*;

/**
 * @author:liuzidi
 * @Description:
 */
public class FindBothHave {
        public static int[] intersect(int[] nums1, int[] nums2) {
            if( nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
                return null;
            }
            ArrayList<Integer> list =new ArrayList<>();
            Map<Integer, Integer> map1 =new HashMap<>();
            Map<Integer, Integer> map2 =new HashMap<>();
            for(int num1 : nums1){//num为key，count为value
                Integer count = map1.get(num1);
                count = count == null ? 1 : ++count;
                map1.put(num1, count);
            }
            for(int num2 : nums2){
                if(map1.get(num2) != null) {
                    Integer count = map2.get(num2);
                    count = count == null ? 1 : ++count;
                    map2.put(num2, count);
                    if (count <= map1.get(num2)) {
                        list.add(num2);
                    }
                }
            }
            int[] resArr;
            resArr = list.stream().mapToInt(Integer :: valueOf).toArray();
            return resArr;
        }

    public static void main(String[] args) {
        int[] nums1 = {2,2,2,2,3,8,32};
        int[] nums2 = {2,2,4,5,8,32};
        int[]resArr =intersect(nums1,nums2);
        for(int num : resArr){
            System.out.println(num);
        }
    }
}
