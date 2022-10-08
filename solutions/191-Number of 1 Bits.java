// LeetCode Question URL: https://leetcode.com/problems/number-of-1-bits/
// LeetCode Discuss URL:

/**
 * Keep removing rightmost set bit.
 *
 * The run time depends on the number of 1-bits in n. In the worst case, all
 * bits in n are 1-bits. In case of a 32-bit integer, the run time is O(1)
 *
 * TC: O(Number of set bits) = O(32) = O(1)
 *
 * SC: O(1)
 */
class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int setBits = 0;

        while (n != 0) {
            n &= n - 1;
            setBits++;
        }

        return setBits;
    }
}
