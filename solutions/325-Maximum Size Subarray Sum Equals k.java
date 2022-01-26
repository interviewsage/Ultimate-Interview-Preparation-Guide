// LeetCode Question URL: https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using hashmap to store the first index of the cumulative sum.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 */
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0] == k ? 1 : 0;
        }

        // Key = Sum, Value = First index at which this sum was seen.
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);
        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < len; i++) {
            sum += nums[i];
            Integer foundIdx = prefixSumMap.get(sum - k);
            if (foundIdx != null) {
                /**
                 * Checking if sum - k is available in map. If yes, then the length of sub array
                 * will be i - map.get(sum-k). sum-k will be give the index before the valid sub
                 * array.
                 */
                maxLen = Math.max(maxLen, i - foundIdx);
            }

            // Saving first index at which this sum was seen.
            prefixSumMap.putIfAbsent(sum, i);
        }

        return maxLen;
    }
}
