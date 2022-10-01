// LeetCode Question URL: https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.*;

/**
 * Maintain a count of cumulative sum in a map.
 *
 * Refer: https://leetcode.com/problems/subarray-sum-equals-k/discuss/190674
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input nums array.
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);

        int sum = 0;
        int result = 0;

        for (int n : nums) {
            sum += n;
            result += prefixSumMap.getOrDefault(sum - k, 0);
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
}
