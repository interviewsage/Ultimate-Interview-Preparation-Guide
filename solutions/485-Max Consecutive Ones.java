// LeetCode Question URL: https://leetcode.com/problems/max-consecutive-ones/
// LeetCode Discuss URL: https://leetcode.com/problems/max-consecutive-ones/discuss/1507990/Java-or-TC:-O(N)-or-SC:-O(1)-or-Easy-and-Concise-solution

/**
 * Maintain current count and maximum count so far.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array
 */
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int curCount = 0;
        int maxCount = 0;

        for (int n : nums) {
            if (n == 1) {
                maxCount = Math.max(maxCount, ++curCount);
            } else {
                curCount = 0;
            }
        }

        return maxCount;
    }
}
