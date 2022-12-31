// LeetCode Question URL: https://leetcode.com/problems/sum-of-two-integers/
// LeetCode Discuss URL:

/**
 * Refer: A summary: how to use bit manipulation to solve problems easily and efficiently
 * https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary:-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
 */

/**
 * Iterative solution
 *
 * Here addition is divided into 2 parts. First part is sum of each
 * corresponding bit without including the carry. Second part is carry.
 *
 * Carry = (a & b) << 1. Sum without carry = a ^ b. Now add these two in the
 * while loop with same steps.
 *
 * Keep calculating this sum and carry till either becomes zero.
 *
 * Time Complexity: O(32) = O(1). We are adding zeros from right in carry, and
 * there can be maximum 32 zeros. Thus after 32 iterations carry will definitely
 * become zero.
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int getSum(int a, int b) {
        while (a != 0 && b != 0) {
            int carry = (a & b) << 1;
            a ^= b;
            b = carry;
        }

        return a == 0 ? b : a;
    }
}

/**
 * Recursive solution
 *
 * Here addition is divided into 2 parts. First part is sum of each
 * corresponding bit without including the carry. Second part is carry.
 *
 * Carry = (a & b) << 1. Sum without carry = a ^ b. Now add these two by calling
 * same function.
 *
 * Keep calculating this sum and carry till either becomes zero.
 *
 * Time Complexity: O(32) = O(1). We are adding zeros from right in carry, and
 * there can be maximum 32 zeros. Thus after 32 iterations carry will definitely
 * become zero.
 *
 * Space Complexity: O(32) = O(1). Same explanation as time complexity.
 */
class Solution2 {
    public int getSum(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        return getSum(a ^ b, (a & b) << 1);
    }
}
