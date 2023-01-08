// LeetCode Question URL: https://leetcode.com/problems/check-if-the-sentence-is-pangram/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Time Complexity:
 * 1. Populate intCntMap for minLen word = O(avgLen)
 * 2. Visit all chars and update intCntMap = O((N-1) * avgLen)
 * 3. Populate result = O(min(26, avgLen) + avgLen)
 *
 * Total Time Complexity: O((N+1)*avgLen + min(26, avgLen))
 *
 * Space Complexity:
 * 1. intCntMap = (2 * min(26, avgLen))
 * 2. tempCntMap = (2 * min(26, avgLen))
 *
 * Total Space Complexity: O(4*min(26, avgLen))
 *
 * N = No. of words.
 * </pre>
 */
class Solution1 {
    public List<String> commonChars(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Input words array is null");
        }

        List<String> result = new ArrayList<>();
        int numWords = words.length;
        if (numWords == 0) {
            return result;
        }
        if (numWords == 1) {
            for (int i = 0; i < words[0].length(); i++) {
                result.add(String.valueOf(words[0].charAt(i)));
            }
            return result;
        }

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (int i = 0; i < words[0].length(); i++) {
            char c = words[0].charAt(i);
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (int k = 1; k < numWords; k++) {
            Map<Character, Integer> tempCharCountMap = new HashMap<>();
            String w = words[k];

            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                Integer count = charCountMap.get(c);
                if (count == null) {
                    continue;
                }
                int newCount = tempCharCountMap.getOrDefault(c, 0) + 1;
                if (newCount <= count) {
                    tempCharCountMap.put(c, newCount);
                }
            }

            charCountMap = tempCharCountMap;
        }

        for (char c : charCountMap.keySet()) {
            int count = charCountMap.get(c);
            String curChar = String.valueOf(c);
            while (count-- > 0) {
                result.add(curChar);
            }
        }

        return result;
    }
}

/**
 * Optimized Solution by finding minLen word, if there are a lot of duplicates
 *
 * <pre>
 * Time Complexity:
 * 1. Find MinLen + Create WordSet = O(N)
 * 2. Populate intCntMap for minLen word = O(minLen)
 * 3. Visit all chars and update intCntMap = O((U-1) * avgLen)
 * 4. Populate result = O(min(26, minLen) + minLen)
 *
 * Total Time Complexity: O(N + 2*minLen + (U-1)*avgLen + min(26, minLen))
 *
 * Space Complexity:
 * 1. wordSet = O(U) --> Only references will be saved here.
 * 2. intCntMap = (2 * min(26, minLen))
 * 3. tempCntMap = (2 * min(26, minLen))
 *
 * Total Space Complexity: O(U + 4*min(26, minLen))
 *
 * N = No. of words. U = No. of unique words. U = O(N)
 * </pre>
 */
class Solution2 {
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }

        if (words.length == 1) {
            for (int i = 0; i < words[0].length(); i++) {
                result.add(String.valueOf(words[0].charAt(i)));
            }
            return result;
        }

        /**
         * This HashSet is only required if there can be duplicate words in the input
         * array.
         */
        Set<String> wordSet = new HashSet<>();
        int minLen = words[0].length();
        String minLenStr = words[0];

        for (String w : words) {
            int len = w.length();
            if (len == 0) {
                return result;
            }
            if (wordSet.add(w)) {
                if (minLen > len) {
                    minLen = len;
                    minLenStr = w;
                }
            }
        }

        Map<Character, Integer> intCntMap = new HashMap<>();
        for (int i = 0; i < minLen; i++) {
            char c = minLenStr.charAt(i);
            intCntMap.put(c, intCntMap.getOrDefault(c, 0) + 1);
        }
        wordSet.remove(minLenStr);

        for (String w : wordSet) {
            Map<Character, Integer> tempCntMap = new HashMap<>();
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                Integer cnt = intCntMap.get(c);
                if (cnt == null) {
                    continue;
                }
                int newCnt = tempCntMap.getOrDefault(c, 0) + 1;
                if (newCnt <= cnt) {
                    tempCntMap.put(c, newCnt);
                }
            }
            if (tempCntMap.size() == 0) {
                return result;
            }
            intCntMap = tempCntMap;
        }

        for (char c : intCntMap.keySet()) {
            int cnt = intCntMap.get(c);
            String cur = String.valueOf(c);
            while (cnt-- > 0) {
                result.add(cur);
            }
        }

        return result;
    }
}
