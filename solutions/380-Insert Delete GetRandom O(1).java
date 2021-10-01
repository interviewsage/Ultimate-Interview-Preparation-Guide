// LeetCode Question URL: https://leetcode.com/problems/insert-delete-getrandom-o1/
// LeetCode Discuss URL: https://leetcode.com/problems/insert-delete-getrandom-o1/discuss/1495119/Java-or-TC:-O(1)-or-SC:-O(N)-or-Using-ArrayList-and-HashMap

import java.util.*;

/**
 * Using ArrayList & HashMap
 *
 * Time Complexity: All function have average O(1)
 *
 * Space Complexity: O(N)
 *
 * N = Number of values currently stored in the data structure.
 */
class RandomizedSet {

    List<Integer> nums;
    Map<Integer, Integer> idxMap;
    Random random;

    public RandomizedSet() {
        nums = new ArrayList<>();
        idxMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (idxMap.containsKey(val)) {
            return false;
        }

        idxMap.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!idxMap.containsKey(val)) {
            return false;
        }

        int idx = idxMap.get(val);
        int lastIdx = nums.size() - 1;
        if (idx != lastIdx) {
            int lastVal = nums.get(lastIdx);
            nums.set(idx, lastVal);
            idxMap.put(lastVal, idx);
        }
        nums.remove(lastIdx);
        idxMap.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}

// Your RandomizedSet object will be instantiated and called as such:
// RandomizedSet obj = new RandomizedSet();
// boolean param_1 = obj.insert(val);
// boolean param_2 = obj.remove(val);
// int param_3 = obj.getRandom();
