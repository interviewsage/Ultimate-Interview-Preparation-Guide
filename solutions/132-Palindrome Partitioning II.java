// LeetCode Question URL: https://leetcode.com/problems/palindrome-partitioning-ii/
// LeetCode Discuss URL:

/**
 * Using the concept of Expand Palindrome around the center.. 1st time with odd
 * length and 2nd time with even length.
 *
 * cuts[i] = Minimum Number of cuts needed for string of length i from the
 * start.
 *
 * Time Complexity: O(N + 2 * N * N/2) = O(N + N^2) = O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input string.
 */
class Solution {
    public int minCut(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return 0;
        }

        int[] cuts = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            cuts[i] = i - 1;
        }

        for (int i = 0; i < len; i++) {
            expandPalindrome(s, i, i, cuts);
            expandPalindrome(s, i, i + 1, cuts);
        }

        return cuts[len];
    }

    private void expandPalindrome(String s, int start, int end, int[] cuts) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            cuts[end + 1] = Math.min(cuts[end + 1], cuts[start] + 1);
            start--;
            end++;
        }
    }
}
