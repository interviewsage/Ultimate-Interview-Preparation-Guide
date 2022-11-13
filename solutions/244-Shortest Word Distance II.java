// LeetCode Question URL: https://leetcode.com/problems/shortest-word-distance-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Good Link to refer:
 * https://leetcode.com/problems/shortest-word-distance-ii/discuss/67028/Java-Solution-using-HashMap/69053
 *
 * Create a map of each word and its index in words array. Now compare this
 * index lists of 2 words to find the minimum distance.
 *
 * Time Complexity: WordDistance Constructor = O(L * N). shortest function = O(I
 * + J) = O(N). I = Length of indexList1. J = Length of indexList2. In worst
 * case sum of I & J will be N.
 *
 * Space Complexity: O(2 * N). Size of the map.
 *
 * L = Average length of a word in words array. N = Number words in the input
 * array.
 */
class WordDistance {

    Map<String, List<Integer>> wordIndexMap;

    public WordDistance(String[] wordsDict) {
        if (wordsDict == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        wordIndexMap = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            wordIndexMap.putIfAbsent(wordsDict[i], new ArrayList<>());
            wordIndexMap.get(wordsDict[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        List<Integer> word1Idx = wordIndexMap.get(word1);
        if (word1Idx == null) {
            return -1;
        }

        int minDist = Integer.MAX_VALUE;

        if (word1.equals(word2)) {
            int cnt = word1Idx.size();
            if (cnt < 2) {
                return -1;
            }
            for (int i = 1; i < cnt && minDist > 1; i++) {
                minDist = Math.min(minDist, word1Idx.get(i) - word1Idx.get(i - 1));
            }
            return minDist;
        }

        List<Integer> word2Idx = wordIndexMap.get(word2);
        if (word2Idx == null) {
            return -1;
        }

        int i = 0;
        int j = 0;
        while (i < word1Idx.size() && j < word2Idx.size() && minDist > 1) {
            int idx1 = word1Idx.get(i);
            int idx2 = word2Idx.get(j);
            if (idx1 < idx2) {
                minDist = Math.min(minDist, idx2 - idx1);
                i++;
            } else {
                minDist = Math.min(minDist, idx1 - idx2);
                j++;
            }
        }

        return minDist;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 *
 * WordDistance obj = new WordDistance(words);
 *
 * int param_1 = obj.shortest(word1,word2);
 */
