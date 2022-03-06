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
 * Carry = (a & b) << 1. Sum without carry = a ^ b
 *
 * Keep adding this sum and carry till carry becomes zero.
 *
 * Time Complexity: O(32) = O(1) while loop can run maximum 32 times if all bit
 * are set to one in both the numbers.
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int getSum(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        while (b != 0) {
            int carry = (a & b) << 1;
            a ^= b;
            b = carry;
        }

        return a;
    }
}

/**
 * Recursive solution
 *
 * Here addition is divided into 2 parts. First part is sum of each
 * corresponding bit without including the carry. Second part is carry.
 *
 * Carry = (a & b) << 1. Sum without carry = a ^ b
 *
 * Keep adding this sum and carry till carry becomes zero.
 *
 * Time Complexity: O(32) = O(1) while loop can run maximum 32 times if all bit
 * are set to one in both the numbers.
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