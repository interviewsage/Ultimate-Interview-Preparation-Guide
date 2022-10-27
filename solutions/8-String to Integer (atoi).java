// LeetCode Question URL: https://leetcode.com/problems/string-to-integer-atoi/
// LeetCode Discuss URL: https://leetcode.com/problems/string-to-integer-atoi/discuss/1545121/Java-or-TC:-O(N)-or-SC:-O(1)-or-Simple-and-Concise-One-Pass-solution-with-Explanations

/**
 * Ignoring the leading whitespace and then iterate over the remaining to find
 * digits
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string.
 */
class Solution {

    private static final int INT_MAX_DIV_10 = Integer.MAX_VALUE / 10;
    private static final int INT_MAX_MOD_10 = Integer.MAX_VALUE % 10;

    public int myAtoi(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        int idx = 0;
        while (idx < len && s.charAt(idx) == ' ') {
            idx++;
        }
        if (idx == len) {
            return 0;
        }

        int sign = 1;
        if (s.charAt(idx) == '-' || s.charAt(idx) == '+') {
            if (s.charAt(idx++) == '-') {
                sign = -1;
            }
        }

        int num = 0;
        while (idx < len && Character.isDigit(s.charAt(idx))) {
            int digit = s.charAt(idx++) - '0';
            if (num > INT_MAX_DIV_10 || (num == INT_MAX_DIV_10 && digit > INT_MAX_MOD_10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
        }

        return sign * num;
    }
}
