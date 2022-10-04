// LeetCode Question URL: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/

/**
 * Using StringBuilder as Stack. Iterative One Pass Solution
 *
 * Time Complexity: O(N + N) = O(N) -> N for Iterating over the string. N for
 * sb.toString()
 *
 * Space Complexity: O(N - D) -> Space used by the StringBuilder
 *
 * N = Length of input string. D = Number of duplicate pairs
 */
class Solution {
    public String removeDuplicates(String s) {
        if (s == null) {
            // Ask Interviewer about this base case. You can also return null here.
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int sbLen = sb.length();

            if (sbLen > 0 && sb.charAt(sbLen - 1) == c) {
                // If last character in StringBuilder is same as the current character, then
                // remove the last character from StringBuilder
                sb.setLength(sbLen - 1);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
