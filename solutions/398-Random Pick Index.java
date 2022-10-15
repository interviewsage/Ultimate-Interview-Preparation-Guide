// LeetCode Question URL: https://leetcode.com/problems/random-pick-index/
// LeetCode Discuss URL: https://leetcode.com/problems/random-pick-index/discuss/1493137/Java-or-O(N)-pick-O(1)-space-OR-O(1)-pick-O(N)-space-or-Two-Approaches-or-Reservoir-Sampling-or-HashMap

import java.util.*;

/**
 * Using Reservoir Sampling
 *
 * Suppose the indexes of the target element in array are from 1 to N. You have
 * already picked i-1 elements. Now you are trying to pick ith element. The
 * probability to pick it is 1/i. Now you do not want to pick any future
 * numbers.. Thus, the final probability for ith element = 1/i * (1 - 1/(i+1)) *
 * (1 - 1/(i+2)) * .. * (1 - 1/N) = 1 / N.
 *
 * <pre>
 * Time Complexity:
 * 1) Solution() Constructor -> O(1)
 * 2) pick() -> O(N)
 * </pre>
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {

    int[] nums;
    Random random;

    public Solution1(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums array is null");
        }

        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
                if (random.nextInt(count) == 0) {
                    index = i;
                }
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("Target not found");
        }
        return index;
    }
}

/**
 * Preprocessing input using HashMap
 *
 * <pre>
 * Time Complexity:
 * 1) Solution() Constructor -> O(N)
 * 2) pick() -> O(1)
 * </pre>
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution2 {

    Map<Integer, List<Integer>> map;
    Random random;

    public Solution2(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums array is null");
        }

        map = new HashMap<>();
        random = new Random();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indexes = map.get(target);
        if (indexes == null) {
            throw new NoSuchElementException("Target not found");
        }

        return indexes.get(random.nextInt(indexes.size()));
    }
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(nums);
// int param_1 = obj.pick(target);
