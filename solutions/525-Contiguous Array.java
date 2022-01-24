// LeetCode Question URL: https://leetcode.com/problems/contiguous-array
// LeetCode Discuss URL:

import java.util.*;

/**
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
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Map<Integer, Integer> firstSeenMap = new HashMap<>();
        int sum = 0;
        firstSeenMap.put(0, -1);
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            Integer prevIdx = firstSeenMap.get(sum);

            if (prevIdx != null) {
                maxLen = Math.max(maxLen, i - prevIdx);
            } else {
                firstSeenMap.put(sum, i);
            }
        }

        return maxLen;
    }
}
