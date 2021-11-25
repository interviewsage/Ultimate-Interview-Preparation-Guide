// LeetCode Question URL: https://leetcode.com/problems/reverse-words-in-a-string-ii/
// LeetCode Discuss URL:

/**
 * Reverse each word and then reverse the whole sentence.
 *
 * Time Complexity: O(3*N) = O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */

class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }

        int len = s.length;
        int i = 0;

        while (i < len) {
            int start = i;
            while (i < len && s[i] != ' ') {
                i++;
            }
            reverseRange(s, start, i - 1);
            i++;
        }

        reverseRange(s, 0, len - 1);
    }

    private void reverseRange(char[] charArr, int start, int end) {
        while (start < end) {
            char tmp = charArr[start];
            charArr[start] = charArr[end];
            charArr[end] = tmp;
            start++;
            end--;
        }
    }
}
