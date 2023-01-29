// LeetCode Question URL: https://leetcode.com/problems/reverse-words-in-a-string-iii/
// LeetCode Discuss URL:

/**
 * Uses one only one StringBuilder.
 *
 * Find the start & end of each word and then reverse the chars in each word.
 *
 * Time Complexity: O(2.5 * N)
 *
 * Space Complexity: O(N)
 */
class Solution {
    public String reverseWords(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        StringBuilder result = new StringBuilder(len);
        int idx = 0;

        while (idx < len) {
            if (s.charAt(idx) == ' ') {
                result.append(s.charAt(idx++));
                continue;
            }

            int start = idx;
            while (idx < len && s.charAt(idx) != ' ') {
                result.append(s.charAt(idx++));
            }
            reverseRange(result, start, idx - 1);
        }

        return result.toString();
    }

    private void reverseRange(StringBuilder sb, int start, int end) {
        while (start < end) {
            char t = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, t);
        }
    }
}
