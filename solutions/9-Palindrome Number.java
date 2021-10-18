// LeetCode Question URL: https://leetcode.com/problems/palindrome-number/
// LeetCode Discuss URL: https://leetcode.com/problems/palindrome-number/discuss/1527951/Java-or-TC:-O((log10-N)2)-or-SC:-O(1)-or-Optimal-Reverse-Half-and-Compare

/**
 * Reverse Half & Compare
 *
 * Refer:
 * https://leetcode.com/articles/palindrome-number/#approach-1-revert-half-of-the-number
 *
 * Time Complexity: O((log10 N) / 2)
 *
 * Space Complexity: O(1)
 *
 * N = Number of digits in input number.
 */
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        int reverse = 0;
        while (reverse < x) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        /**
         * If input number has even number of digits then check `x == reverse`.
         *
         * If input number has odd number of digits then check `x == reverse / 10`. This
         * is because, reverse will have one extra digit. Middle digit of original
         * number will be least significant digit of reverse.
         */
        return reverse == x || reverse / 10 == x;
    }
}
