// LeetCode Question URL: https://leetcode.com/problems/shortest-word-distance/
// LeetCode Discuss URL:

/**
 * Compare each word with word1 and word2 and store the minimum distance seen
 * till now.
 *
 * Time Complexity: O(L * N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of words in words array. L = Average length of a word in words
 * array.
 */
class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        if (wordsDict == null || word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = wordsDict.length;
        if (len < 2) {
            return -1;
        }

        int minDist = len;
        int word1Idx = -1;
        int word2Idx = -1;

        for (int i = 0; i < len && minDist > 1; i++) {
            if (word1.equals(wordsDict[i])) {
                if (word2Idx != -1) {
                    minDist = Math.min(minDist, i - word2Idx);
                }
                word1Idx = i;
            } else if (word2.equals(wordsDict[i])) {
                if (word1Idx != -1) {
                    minDist = Math.min(minDist, i - word1Idx);
                }
                word2Idx = i;
            }
        }

        return minDist == len ? -1 : minDist;
    }
}
