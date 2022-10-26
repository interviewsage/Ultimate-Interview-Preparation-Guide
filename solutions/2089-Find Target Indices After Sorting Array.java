// LeetCode Question URL: https://leetcode.com/problems/find-target-indices-after-sorting-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/find-target-indices-after-sorting-array/discuss/1599963/Java-O(N)-single-loop-count
 *
 * Time Complexity: O(N + T)
 *
 * Space Complexity: O(1)
 *
 * N = Length of nums. T = Number of occurrences of target.
 */
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int equalToTarget = 0;
        int lessThanTarget = 0;

        for (int n : nums) {
            if (n < target) {
                lessThanTarget++;
            } else if (n == target) {
                equalToTarget++;
            }
        }

        List<Integer> result = new ArrayList<>();
        while (equalToTarget-- > 0) {
            result.add(lessThanTarget++);
        }
        return result;
    }
}
