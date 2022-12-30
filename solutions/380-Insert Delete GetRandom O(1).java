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

    Map<Integer, Integer> map;
    List<Integer> nums;
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        map.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        Integer idx = map.remove(val);
        if (idx == null) {
            return false;
        }

        int lastIdx = nums.size() - 1;
        int lastNum = nums.remove(lastIdx);
        if (idx != lastIdx) {
            nums.set(idx, lastNum);
            map.put(lastNum, idx);
        }
        return true;
    }

    public int getRandom() throws NoSuchElementException {
        int size = nums.size();
        if (size == 0) {
            throw new NoSuchElementException("Empty Set");
        }
        return nums.get(random.nextInt(size));
    }
}

// Your RandomizedSet object will be instantiated and called as such:
// RandomizedSet obj = new RandomizedSet();
// boolean param_1 = obj.insert(val);
// boolean param_2 = obj.remove(val);
// int param_3 = obj.getRandom();
