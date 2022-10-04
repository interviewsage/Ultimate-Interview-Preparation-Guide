// LeetCode Question URL: https://leetcode.com/problems/valid-palindrome/

/**
 * Find the characters that are not matching and then execute iPalindrome on
 * (start+1, end) and (start, end+1).
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string.
 */
class Solution {
    public boolean validPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 2) {
            return true;
        }

        int left = 0;
        int right = len - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }

            left++;
            right--;
        }

        return left >= right || isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
