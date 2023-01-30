// LeetCode Question URL: https://leetcode.com/problems/contiguous-array
// LeetCode Discuss URL:

import java.util.*;

/**
 * This question is similar to: 325. Maximum Size Subarray Sum Equals k
 *
 * In this approach, we make use of a sum variable, which is used to store the
 * relative number of ones and zeros encountered so far while traversing the
 * array. The sum variable is incremented by one for every 1 encountered and the
 * same is decremented by one for every 0 encountered.
 *
 * We start traversing the array from the beginning. If at any moment, the sum
 * becomes zero, it implies that we've encountered equal number of zeros and
 * ones from the beginning till the current index of the array(i). Not only
 * this, another point to be noted is that if we encounter the same sum twice
 * while traversing the array, it means that the number of zeros and ones are
 * equal between the indices corresponding to the equal count values.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N). Maximum size of the map will be n, if all the
 * elements are either 1 or 0.
 *
 * N = Length of the input array.
 */
class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return 0;
        }

        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < len; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            Integer foundIdx = prefixSumMap.get(sum);
            if (foundIdx != null) {
                maxLen = Math.max(maxLen, i - foundIdx);
            }
            prefixSumMap.putIfAbsent(sum, i);
        }

        return maxLen;
    }
}
