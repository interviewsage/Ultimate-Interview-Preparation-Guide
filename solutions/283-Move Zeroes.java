// LeetCode Question URL: https://leetcode.com/problems/move-zeroes/
// LeetCode Discuss URL: https://leetcode.com/problems/move-zeroes/discuss/1496643/Java-or-TC:O(N)-or-SC:-O(1)-or-One-Pass-with-Minimum-number-of-operations

/**
 * Find Non Zero element to swap. Keep index of Leftmost Zero.
 *
 * <pre>
 * Total number of write operations = Number of Non-Zero elements that need to
 *                                    be moved
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        if (nums.length <= 1) {
            return;
        }

        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (insertPos != i) {
                    nums[insertPos] = nums[i];
                    nums[i] = 0;
                }
                insertPos++;
            }
        }
    }
}
