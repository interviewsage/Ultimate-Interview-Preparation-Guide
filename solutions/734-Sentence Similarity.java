// LeetCode Question URL: https://leetcode.com/problems/sentence-similarity
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(P + N*L)
 *
 * Space Complexity : O(4*P) --> We will only store the references of strings in
 * the map. New copy of string is NOT created.
 *
 * N = length of words1. P = size of pairs. L = Average length of each word
 */
class Solution {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1 == null || sentence2 == null || sentence1.length != sentence2.length) {
            return false;
        }

        int len = sentence1.length;
        if (len == 0) {
            return true;
        }

        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> pair : similarPairs) {
            map.putIfAbsent(pair.get(0), new HashSet<>());
            map.get(pair.get(0)).add(pair.get(1));
            map.putIfAbsent(pair.get(1), new HashSet<>());
            map.get(pair.get(1)).add(pair.get(0));
        }

        for (int i = 0; i < len; i++) {
            if (sentence1[i].equals(sentence2[i])) {
                continue;
            }
            Set<String> set = map.get(sentence1[i]);
            if (set == null || !set.contains(sentence2[i])) {
                return false;
            }
        }

        return true;
    }
}
