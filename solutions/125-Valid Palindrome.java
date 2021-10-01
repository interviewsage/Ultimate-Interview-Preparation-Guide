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
            throw new IllegalArgumentException("Input string is null");
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
