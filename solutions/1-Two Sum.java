// LeetCode Question URL: https://leetcode.com/problems/two-sum/
// LeetCode Discuss URL:

import java.util.*;

/**
 * One Pass using a hashmap
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(2 * N)
 *
 * N = Length of input array.
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len < 2) {
            throw new NoSuchElementException("Input array is too small");
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null) {
                return new int[] { idx, i };
            }
            map.put(nums[i], i);
        }

        throw new NoSuchElementException("Result not found");
    }
}
