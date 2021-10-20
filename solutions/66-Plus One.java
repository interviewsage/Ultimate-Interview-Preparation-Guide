// LeetCode Question URL: https://leetcode.com/problems/plus-one/
// LeetCode Discuss URL: https://leetcode.com/problems/plus-one/discuss/1529365/Java-or-TC:-O(N)-or-SC:-O(1)-or-Optimized-Math-Addition-simulation-w-Early-Exits

/**
 * Simulate Math Addition
 *
 * Refer: https://leetcode.com/problems/plus-one/solution/
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1) --> Excluding the result space
 *
 * N = Length of input digits array.
 */
class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = digits.length;
        if (len == 0) {
            return new int[] { 1 };
        }

        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}
