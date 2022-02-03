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
 * Thus, total time complexity = O(S1 + S2 + W1 + W2)
 *
 * Space Complexity:
 * 1. allWords = O(S1 + S2)
 * 2. distinctWords = O(S1 + S2)
 *
 * Thus, total time complexity = O(S1 + S2)
 * </pre>
 */
class Solution1 {
    public String[] uncommonFromSentences(String s1, String s2) {
        Set<String> allWords = new HashSet<>();
        Set<String> distinctWords = new HashSet<>();

        if (s1 != null && s1.length() > 0) {
            for (String w : s1.split(" ")) {
                if (allWords.add(w)) {
                    distinctWords.add(w);
                } else {
                    distinctWords.remove(w);
                }
            }
        }

        if (s2 != null && s2.length() > 0) {
            for (String w : s2.split(" ")) {
                if (allWords.add(w)) {
                    distinctWords.add(w);
                } else {
                    distinctWords.remove(w);
                }
            }
        }

        String[] result = new String[distinctWords.size()];
        int i = 0;
        for (String w : distinctWords) {
            result[i++] = w;
        }

        return result;
    }
}

/**
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
