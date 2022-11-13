// LeetCode URL: https://leetcode.com/problems/shortest-word-distance-iii/

/**
 * Refer :
 * https://leetcode.com/problems/shortest-word-distance-iii/discuss/67097/12-16-lines-Java-C++
 *
 * Time Complexity: O(L * N)
 *
 * Space Complexity: O(1)
 *
 * L = Average length of a word in words array. N = Number words in the input
 * array.
 */
class Solution1 {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        if (wordsDict == null || word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = wordsDict.length;
        if (len < 2) {
            return -1;
        }

        boolean isSame = word1.equals(word2);
        int word1Idx = -1;
        int word2Idx = -1;
        int minDist = len;

        for (int i = 0; i < len && minDist > 1; i++) {
            if (word1.equals(wordsDict[i])) {
                if (word2Idx != -1) {
                    minDist = Math.min(minDist, i - word2Idx);
                }
                word1Idx = i;
                if (isSame) {
                    word2Idx = word1Idx;
                }
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

class Solution2 {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        if (wordsDict == null || word1 == null || word2 == null) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int len = wordsDict.length;
        if (len < 2) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        int word1Idx = -1;
        int word2Idx = -1;
        boolean isWordSame = word1.equals(word2);

        for (int i = 0; i < len && res > 1; i++) {
            if (word1.equals(wordsDict[i])) {
                if (isWordSame) {
                    if (word1Idx != -1) {
                        res = Math.min(res, i - word1Idx);
                    }
                } else if (word2Idx != -1) {
                    res = Math.min(res, i - word2Idx);
                }
                word1Idx = i;
            } else if (word2.equals(wordsDict[i])) {
                if (word1Idx != -1) {
                    res = Math.min(res, i - word1Idx);
                }
                word2Idx = i;
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
