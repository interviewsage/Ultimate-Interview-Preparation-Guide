// LeetCode Question URL: https://leetcode.com/problems/palindromic-substrings/
// LeetCode Discuss URL: https://leetcode.com/problems/palindromic-substrings/discuss/1539193/Java-or-TC:-O(N2)-or-SC:-O(1)-or-Optimized-solution-for-continuous-repeating-chars

/**
 * Optimized Solution for continuous repeating characters. Find the center and
 * expand palindrome around the center.
 *
 * Refer:
 * https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome/223769
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string S.
 */
class Solution1 {
    public int countSubstrings(String s) {
        if (s == null) {
            return 0;
        }

        int len = s.length();
        if (len <= 1) {
            return 1;
        }

        int result = 0;
        int i = 0;
        while (i < len) {
            int start = i++;
            // Finding the continuous repeating characters
            // We can optimize by finding all such chars and treat them as the palindrome
            // center for the next expandPalindrome call.
            while (i < len && s.charAt(start) == s.charAt(i)) {
                i++;
            }

            // Adding number of possible substrings of the center string
            // https://www.geeksforgeeks.org/number-substrings-string/
            int centerLen = i - start;
            result += centerLen * (centerLen + 1) / 2;

            result += expandPalindrome(s, start - 1, i);
        }

        return result;
    }

    private int expandPalindrome(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }
}

/**
 * Find the center and expand palindrome around the center. No Optimizations in
 * this solution.
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string S.
 */
class Solution2 {
    public int countSubstrings(String s) {
        if (s == null) {
            return 0;
        }

        int len = s.length();
        if (len <= 1) {
            return 1;
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            result += expandPalindrome(s, i, i);
            result += expandPalindrome(s, i, i + 1);
        }

        return result;
    }

    private int expandPalindrome(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }
}
