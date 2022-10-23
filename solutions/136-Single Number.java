// LeetCode Question URL: https://leetcode.com/problems/single-number/
// LeetCode Discuss URL:

/**
 * XOR of all numbers. XOR result is the answer.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }

        return xor;
    }
}
