// LeetCode Question URL: https://leetcode.com/problems/hamming-distance/
// LeetCode Discuss URL:

/**
 * XOR of both numbers and and then count the set bits in XOR.
 *
 * The run time depends on the number of 1-bits in n. In the worst case, all
 * bits in nn are 1-bits. In case of a 32-bit integer, the run time is O(1)
 *
 * Time Complexity: O(32) = O(1)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int hammingDistance(int x, int y) {
        // XOR will provide us the bits that are different.
        x = x ^ y;
        int result = 0;
        while (x != 0) {
            x &= (x - 1);
            result++;
        }
        return result;
    }
}
