// LeetCode Question URL: https://leetcode.com/problems/counting-bits/

package leetcode;

/**
 * DP + Rightmost Set Bit
 *
 * Last set bit is the rightmost set bit. Setting that bit to zero with the bit
 * trick, x &= x - 1, leads to the following transition function:
 *
 * DP[i] = DP[i & (i - 1)] + 1;
 *
 * Isolate the right-most set bit (ex. 110100 -> 110000).
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1). Including the result, it will be O(N)
 */
class Solution1 {
    public int[] countBits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is negative");
        }

        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }

        return result;
    }
}

/**
 * DP + Least Significant Bit
 *
 * Remove the least significant bit. This will result into a number smaller than
 * the current number. Find the count of bits of this number from DP array and
 * then add 1 if the least significant was set.
 *
 * DP[i] = DP[i / 2] + (i % 2) or DP[i] = DP[i >> 1] + (i & 1)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1). Including the result, it will be O(N)
 */
class Solution2 {
    public int[] countBits(int num) {
        if (num < 0) {
            return new int[0];
        }

        int[] result = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }

        return result;
    }
}
