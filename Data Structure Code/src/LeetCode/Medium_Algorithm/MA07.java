package LeetCode.Medium_Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author:liuzidi
 * @Description:
 */
public class MA07 {
    class RandomizedSet {
        private Set<Integer> set = new HashSet<>();
        /** Initialize your data structure here. */
        public RandomizedSet() {

        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(set.contains(val)){
                return false;
            }else{
                set.add(val);
                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(set.contains(val)){
                set.remove(val);
                return true;
            }else{
                return false;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int random = (int)(Math.random() * (set.size() - 1));
            List<Integer> list = new ArrayList<Integer>(set);
            return list.get(random);
        }
    }
}
