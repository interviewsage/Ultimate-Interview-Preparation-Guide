// LeetCode URL: https://leetcode.com/problems/random-pick-index/

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
        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int idx = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
                if (random.nextInt(count) == 0) {
                    idx = i;
                }
            }
        }

        return idx;
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
        random = new Random();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        if (!map.containsKey(target)) {
            return -1;
        }
        List<Integer> curList = map.get(target);
        return curList.get(random.nextInt(curList.size()));
    }
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(nums);
// int param_1 = obj.pick(target);
