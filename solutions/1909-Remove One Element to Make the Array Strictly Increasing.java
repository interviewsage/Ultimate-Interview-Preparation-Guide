// LeetCode Question URL: https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
// LeetCode Discuss URL:

/**
 * One Pass
 *
 * Refer:
 * https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/discuss/1298443/C++-O(n)-one-pass-explained-multiple-solutions
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int len = nums.length;
        if (len <= 2) {
            return true;
        }

        int previousNum = nums[0];
        boolean oneNumRemoved = false;

        for (int i = 1; i < len; i++) {
            if (previousNum < nums[i]) {
                previousNum = nums[i];
                continue;
            }

            if (oneNumRemoved) {
                return false;
            }

            oneNumRemoved = true;
            if (i == 1 || nums[i - 2] < nums[i]) {
                previousNum = nums[i];
            }
        }

        return true;
    }
}
