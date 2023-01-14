// LeetCode Question URL: https://leetcode.com/problems/uncommon-words-from-two-sentences/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Time Complexity:
 * 1. Split = O(S1 + S2)
 * 2. Iterate over each word = O(W1 + W2)
 * 3. Last for loop in worst case if all words are distinct = O(W1 + W2)
 *
 * Thus, total time complexity = O(S1 + S2 + W1 + W2) = O(S1 + S2)
 *
 * Space Complexity:
 * 1. allWords = O(S1 + S2)
 * 2. distinctWords = O(W1 + W2)
 *
 * Thus, total space complexity = O(S1 + S2 + W1 + W2) = O(S1 + S2)
 * </pre>
 */
class Solution1 {
    public String[] uncommonFromSentences(String s1, String s2) {
        Set<String> allWords = new HashSet<>();
        Set<String> wordsSeenOnlyOnce = new HashSet<>();

        processWordsInSentence(s1, allWords, wordsSeenOnlyOnce);
        processWordsInSentence(s2, allWords, wordsSeenOnlyOnce);

        return wordsSeenOnlyOnce.toArray(new String[wordsSeenOnlyOnce.size()]);
    }

    private void processWordsInSentence(String s, Set<String> allWords, Set<String> wordsSeenOnlyOnce) {
        if (s == null || s.length() == 0) {
            return;
        }

        for (String w : s.split(" ")) {
            if (allWords.add(w)) {
                wordsSeenOnlyOnce.add(w);
            } else {
                wordsSeenOnlyOnce.remove(w);
            }
        }
    }
}

/**
 * IGNORE THIS SOLUTION
 *
 * <pre>
 * Time Complexity:
 * 1. Split = O(S1 + S2)
 * 2. Iterate over each word = O(W1 + W2)
 * 3. Last for loop in worst case if all words are distinct = O(W1 + W2)
 *
 * Thus, total time complexity = O(S1 + S2 + W1 + W2)
 *
 * Space Complexity:
 * 1. countMap = O(S1 + S2)
 * 2. distinctWords = O(S1 + S2)
 *
 * Thus, total time complexity = O(S1 + S2)
 * </pre>
 */
class Solution2 {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> countMap = new HashMap<>();

        if (s1 != null && s1.length() > 0) {
            for (String w : s1.split(" ")) {
                countMap.put(w, countMap.getOrDefault(w, 0) + 1);
            }
        }

        if (s2 != null && s2.length() > 0) {
            for (String w : s2.split(" ")) {
                countMap.put(w, countMap.getOrDefault(w, 0) + 1);
            }
        }

        List<String> distinctWords = new ArrayList<>();
        for (String w : countMap.keySet()) {
            if (countMap.get(w) == 1) {
                distinctWords.add(w);
            }
        }

        return distinctWords.toArray(new String[distinctWords.size()]);
    }
}
