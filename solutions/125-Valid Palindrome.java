// LeetCode Question URL: https://leetcode.com/problems/valid-palindrome/
// LeetCode Discuss URL: https://leetcode.com/problems/valid-palindrome/discuss/1496675/Java-or-TC:-O(N)-or-SC:-O(1)-or-One-Pass-Solution-using-two-pointers

/**
 * One Pass Solution using two pointers
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input string.
 */
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = s.length();
        if (len <= 1) {
            return true;
        }

        int start = 0;
        int end = len - 1;

        while (start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while (start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }

            if (Character.toLowerCase(s.charAt(start++)) != Character.toLowerCase(s.charAt(end--))) {
                return false;
            }
        }

        return true;
    }
}
