// LeetCode Question URL: https://leetcode.com/problems/reverse-words-in-a-string-ii/
// LeetCode Discuss URL:

/**
 * Reverse each word and then reverse the whole sentence.
 *
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution {
    public void reverseWords(char[] s) {
        if (s == null) {
            throw new IllegalArgumentException("Input char array is null");
        }

        int len = s.length;
        if (len <= 1) {
            return;
        }

        int idx = 0;
        while (idx < len) {
            int start = idx;
            while (idx < len && s[idx] != ' ') {
                idx++;
            }
            reverseRange(s, start, idx - 1);
            idx++;
        }

        reverseRange(s, 0, len - 1);
    }

    private void reverseRange(char[] s, int start, int end) {
        while (start < end) {
            char t = s[start];
            s[start++] = s[end];
            s[end--] = t;
        }
    }
}
