// LeetCode Question URL: https://leetcode.com/problems/move-zeroes/
// LeetCode Discuss URL: https://leetcode.com/problems/move-zeroes/discuss/1496643/Java-or-TC:O(N)-or-SC:-O(1)-or-One-Pass-with-Minimum-number-of-operations

/**
 * Find Non Zero element to swap. Keep index of Leftmost Zero.
 *
 * <pre>
 * Total number of write operations = 2 * Number of Non-Zero elements
 * Total number of read operations = N
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return;
        }

        int insertPos = 0;

        for (int i = 0; i < len; i++) {
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

/**
 * Find Non Zero element to swap. Keep index of Leftmost Zero.
 *
 * <pre>
 * Total number of write operations = N
 * Total number of read operations = N
 * </pre>
 *
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return;
        }

        int insertPos = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                if (insertPos != i) {
                    nums[insertPos] = nums[i];
                }
                insertPos++;
            }
        }

        while (insertPos < len) {
            nums[insertPos++] = 0;
        }
    }
}
