// LeetCode Question URL: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
// LeetCode Discuss URL: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/discuss/1495123/Java-or-TC:-O(1)-or-SC:-O(N)-or-Using-ArrayList-and-HashMap

import java.util.*;

/**
 * Using ArrayList & HashMap.
 *
 * Time Complexity: All function have average O(1)
 *
 * Space Complexity: O(N)
 *
 * N = Number of values currently stored in the data structure.
 */
class RandomizedCollection {

    Map<Integer, Set<Integer>> map;
    List<Integer> nums;
    Random random;

    public RandomizedCollection() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        Set<Integer> idxSet = map.get(val);
        if (idxSet == null) {
            idxSet = new HashSet<>();
            map.put(val, idxSet);
        }
        idxSet.add(nums.size());
        nums.add(val);

        return idxSet.size() == 1;
    }

    public boolean remove(int val) {
        Set<Integer> idxSet = map.get(val);
        if (idxSet == null) {
            return false;
        }

        int lastIdx = nums.size() - 1;
        int lastNum = nums.remove(lastIdx);
        // If the input val is same as last number, then we can just remove the lastIdx.
        int idxToBeRemoved = lastNum == val ? lastIdx : idxSet.iterator().next();

        if (idxSet.size() == 1) {
            map.remove(val);
        } else {
            idxSet.remove(idxToBeRemoved);
        }

        if (idxToBeRemoved != lastIdx) {
            Set<Integer> lastNumIdxSet = map.get(lastNum);
            lastNumIdxSet.remove(lastIdx);
            lastNumIdxSet.add(idxToBeRemoved);
            nums.set(idxToBeRemoved, lastNum);
        }

        return true;
    }

    public int getRandom() {
        int size = nums.size();
        if (size == 0) {
            throw new NoSuchElementException("Empty Set");
        }
        return nums.get(random.nextInt(size));
    }
}

// Your RandomizedCollection object will be instantiated and called as such:
// RandomizedCollection obj = new RandomizedCollection();
// boolean param_1 = obj.insert(val);
// boolean param_2 = obj.remove(val);
// int param_3 = obj.getRandom();
