// LeetCode Question URL: https://leetcode.com/problems/word-ladder/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Double ended BFS
 *
 * Refer: Why Bi-directional search is better?
 * https://leetcode.com/problems/word-ladder/discuss/40711/Two-end-BFS-in-Java-31ms./38609
 *
 * Questions to be asked to Interviewer: (1). If word ladder is "hot" -> "hit"..
 * is the result 1 or 2. (2). Is end word always part of wordList.
 *
 * Time Complexity: O(N + N*(L + 26*L^2)) = O(N * L^2)
 *
 * Space Complexity: O(N + N*L) = O(N*L) --> N to store references in wordSet.
 * N*L for storing new Strings in beginSet/endSet
 *
 * N = Length of wordList. L = Length of each word.
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int wLen = beginWord.length();
        if (wLen != endWord.length()) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }

        Set<String> wordSet = new HashSet<>();
        if (wordList != null) {
            for (String w : wordList) {
                if (w != null && w.length() == wLen) {
                    wordSet.add(w);
                }
            }
        }

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> beginSet = Set.of(beginWord);
        wordSet.remove(beginWord);
        Set<String> endSet = Set.of(endWord);
        wordSet.remove(endWord);

        int ladderLen = 1; // This is set to 1 as we have to include beginWord in the ladder length

        while (!beginSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> t = beginSet;
                beginSet = endSet;
                endSet = t;
            }

            ladderLen++;
            Set<String> nextLevel = new HashSet<>();
            for (String word : beginSet) {
                char[] w = word.toCharArray();
                for (int i = 0; i < wLen; i++) {
                    char old = w[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) {
                            continue;
                        }
                        w[i] = c;
                        String newWord = new String(w);
                        if (endSet.contains(newWord)) {
                            return ladderLen;
                        }
                        if (wordSet.remove(newWord)) {
                            nextLevel.add(newWord);
                        }
                    }
                    w[i] = old;
                }
            }
            beginSet = nextLevel;
        }

        // Transformation sequence not possible
        return 0;
    }
}
