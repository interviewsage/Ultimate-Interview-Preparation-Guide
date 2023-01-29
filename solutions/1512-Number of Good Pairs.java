// LeetCode Question URL: https://leetcode.com/problems/number-of-good-pairs/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Use a count map
 *
 * Time Complexity: O(N) -- One Pass
 *
 * Space Complexity: O(2 * U)
 *
 * N = Length of input array. U = No. of unique numbers in inout array.
 */
class Solution {
    public int numIdenticalPairs(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = nums.length;
        if (len <= 1) {
            return 0;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        int result = 0;
        for (int n : nums) {
            int count = countMap.getOrDefault(n, 0);
            result += count;
            countMap.put(n, count + 1);
        }

        return result;
    }
}
