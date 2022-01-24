// LeetCode Question URL: https://leetcode.com/problems/longest-continuous-increasing-subsequence/
// LeetCode Discuss URL:

/**
 * Sliding Window
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input nums array.
 */
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int maxCount = 1;
        int curCount = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i]) {
                curCount++;
            } else {
                maxCount = Math.max(maxCount, curCount);
                curCount = 1;
            }
        }

        return Math.max(maxCount, curCount);
    }
}
