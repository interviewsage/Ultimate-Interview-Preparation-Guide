// LeetCode Question URL: https://leetcode.com/problems/integer-to-roman/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N/1000 + (N%1000)/900 + ((N%1000)%900)/500 + ...). Loose
 * upper bound is N.
 *
 * Space Complexity: O(1 * N/1000 + 2 * (N%1000)/900 + 1 * ((N%1000)%900)/500 +
 * ...). Loose upper bound is N.
 *
 * N = Input number.
 */
class Solution {

    private static final int[] VALUES = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    private static final String[] ROMAN_SYMBOL = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV",
            "I" };

    public String intToRoman(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (num > 0) {
            while (num >= VALUES[i]) {
                num -= VALUES[i];
                sb.append(ROMAN_SYMBOL[i]);
            }
            i++;
        }

        return sb.toString();
    }
}
