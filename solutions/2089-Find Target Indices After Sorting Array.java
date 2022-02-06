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
 * N = Length of nums. T = Number of occurences of target.
 */
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int countLessThanTarget = 0;
        int countEqualToTarget = 0;
        for (int n : nums) {
            if (n < target) {
                countLessThanTarget++;
            } else if (n == target) {
                countEqualToTarget++;
            }
        }

        // for (int i = 0; i < countEqualToTarget; i++) {
        // result.add(countLessThanTarget + i);
        // }

        while (countEqualToTarget-- > 0) {
            result.add(countLessThanTarget++);
        }

        return result;
    }
}
