// LeetCode Question URL: https://leetcode.com/problems/length-of-last-word/
// LeetCode Discuss URL:

/**
 * Iterate in reverse order till you find a word.
 *
 * Time Complexity: O(N) --> In worst case whole string has to be searched.
 *
 * Space Complexity: O(1).
 *
 * N = Length of input string.
 */
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int idx = s.length() - 1;
        int result = 0;

        while (idx >= 0 && s.charAt(idx) == ' ') {
            idx--;
        }
        while (idx >= 0 && s.charAt(idx) != ' ') {
            result++;
            idx--;
        }
        return result;
    }
}
