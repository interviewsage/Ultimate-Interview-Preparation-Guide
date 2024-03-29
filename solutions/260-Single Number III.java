// LeetCode Question URL: https://leetcode.com/problems/single-number-iii/
// LeetCode Discuss URL:

/**
 * Two Pass solution
 *
 * In the first pass, we XOR all elements in the array, and get the XOR of the
 * two numbers we need to find. Note that since the two numbers are distinct, so
 * there must be a set bit (that is, the bit with value '1') in the XOR result.
 * Find out an arbitrary set bit (for example, the rightmost set bit).
 *
 * In the second pass, we find all numbers with the aforementioned bit set. XOR
 * all filtered numbers. This will provide us first number. Second number can be
 * found by XOR of this number and the original XOR value.
 *
 * Time Complexity: O(N) -> All numbers are visited twice.
 *
 * Space Complexity: O(1) -> Algorithm uses constant space.
 *
 * N = Length of the input array.
 */
class Solution {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length % 2 != 0) {
            throw new IllegalArgumentException("Input array is invalid");
        }

        int aXORb = 0;
        for (int n : nums) {
            aXORb ^= n;
        }

        int rightMostSetBit = aXORb & -aXORb;
        int[] result = new int[2];
        for (int n : nums) {
            if ((n & rightMostSetBit) != 0) {
                result[0] ^= n;
            }
        }

        result[1] = result[0] ^ aXORb;
        return result;
    }
}
