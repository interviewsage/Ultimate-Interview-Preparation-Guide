// LeetCode Question URL: https://leetcode.com/problems/reverse-integer/
// LeetCode Discuss URL: https://leetcode.com/problems/reverse-integer/discuss/1539232/Java-or-TC:-O(log10-N)-or-SC:-O(1)-or-Reverse-digit-by-digit-and-check-for-overflow

/**
 * Reverse digit by digit. For each reversal check if the result is greater than
 * MAX_VALUE
 *
 * Refer:
 * https://leetcode.com/articles/reverse-integer/#approach-1-pop-and-push-digits-check-before-overflow
 *
 * Time Complexity: O(log10 N) or O(Number of digits in N)
 *
 * Space Complexity: O(1)
 *
 * N = Input number.
 */
class Solution {
    public int reverse(int x) {
        if (x >= -9 && x <= 9) {
            return x;
        }
        if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            return 0;
        }

        int sign = x < 0 ? -1 : 1;
        // Case of Integer.MIN_VALUE will be handled here as the absolute value of
        // Integer.MIN_VALUE is Integer.MIN_VALUE. Thus the x will remain less than zero
        // and it will not enter the while loop.
        x = Math.abs(x);
        int result = 0;

        while (x > 0) {
            int digit = x % 10;
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            result = result * 10 + digit;
            x /= 10;
        }

        return sign * result;
    }
}
