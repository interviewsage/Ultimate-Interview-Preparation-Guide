// LeetCode Question URL: https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/discuss/1298443/C++-O(n)-one-pass-explained-multiple-solutions
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int len = nums.length;
        if (len == 1) {
            return true;
        }

        int previous = nums[0];
        boolean oneNumRemoved = false;

        for (int i = 1; i < len; i++) {
            if (previous < nums[i]) {
                previous = nums[i];
                continue;
            }

            if (oneNumRemoved) {
                return false;
            }
            oneNumRemoved = true;

            if (i == 1 || nums[i - 2] < nums[i]) {
                previous = nums[i];
            }
        }

        return true;
    }
}
