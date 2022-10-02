// LeetCode Question URL: https://leetcode.com/problems/continuous-subarray-sum/

import java.util.*;

/**
 * Use hashmap to save the remainder of the prefix sum at each index. If the
 * same remainder is found and the diff between indexes if 2 or more, then we
 * have found the subarray that sums up to a multiple of k.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(min(N, K)) when K > 0 because remainder can between 0 &
 * K-1. When K == 0, then SC=O(N)
 *
 * N = Length of the input array. K = input target.
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len < 2) {
            return false;
        }
        if (k == 1) {
            return true;
        }

        // Use "Long" for Sum if there can be overflow due to large integers in nums
        // array or large value of k.
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;

        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }

            Integer idx = map.get(sum);
            if (idx == null) {
                map.put(sum, i);
            } else {
                if (i - idx >= 2) {
                    return true;
                }
            }
        }

        return false;
    }
}
