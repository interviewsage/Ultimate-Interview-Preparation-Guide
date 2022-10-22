// LeetCode Question URL: https://leetcode.com/problems/sorting-the-sentence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N + N + N) = O(3*N)
 *
 * Space Complexity: O(N + 3*W)
 *
 * N = Length of input string. W = Number of words.
 */
class Solution {
    public String sortSentence(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len == 0) {
            return "";
        }

        Map<Integer, int[]> wordMap = new HashMap<>();
        int idx = 0;
        int maxWordIndex = 0;
        while (idx < len) {
            if (s.charAt(idx) == ' ') {
                idx++;
                continue;
            }

            int start = idx;
            while (idx < len && Character.isLetter(s.charAt(idx))) {
                idx++;
            }
            int end = idx;
            int wIdx = 0;
            while (idx < len && s.charAt(idx) != ' ') {
                wIdx = wIdx * 10 + (s.charAt(idx++) - '0');
            }

            wordMap.put(wIdx, new int[] { start, end });
            maxWordIndex = Math.max(maxWordIndex, wIdx);
            idx++;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= maxWordIndex; i++) {
            int[] word = wordMap.get(i);
            if (result.length() != 0) {
                result.append(' ');
            }
            result.append(s, word[0], word[1]);
        }

        return result.toString();
    }
}
