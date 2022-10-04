// LeetCode Question URL: https://leetcode.com/problems/verifying-an-alien-dictionary/

import java.util.*;

/**
 * Clarifying questions:
 * 1. All characters in the words array are present in order string, if not either throw an exception or return false.
 * 2. All the characters in the order should be unique. If not, then consider the input as invalid.
 * 3. What is the character set of the order String. Is it 26 (a - z)?
 */

/**
 * Save order in a map with index. Compare all words using a custom compare
 * function.
 *
 * Refer:
 * https://leetcode.com/problems/verifying-an-alien-dictionary/discuss/203185/JavaC++Python-Mapping-to-Normal-Order
 *
 * Time Complexity: O(O + N * L) = O(26 + N*L) = O(N * L)
 *
 * Space Complexity: O(O) = O(26) = O(1)
 *
 * O = Length of order string. N = Number of words. L = Average length of each
 * word.
 */
class Solution1 {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || order == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int numWords = words.length;
        if (numWords <= 1) {
            return true;
        }

        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        for (int i = 1; i < numWords; i++) {
            if (compareWords(words[i - 1], words[i], orderMap) > 0) {
                return false;
            }
        }

        return true;
    }

    private int compareWords(String word1, String word2, Map<Character, Integer> orderMap) {
        int word1Len = word1.length();
        int word2Len = word2.length();
        int minLen = Math.min(word1Len, word2Len);

        for (int i = 0; i < minLen; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if (c1 != c2) {
                return orderMap.get(c1) - orderMap.get(c2);
            }
        }

        return word1Len - word2Len;
    }
}

/**
 * Save order in a map with index. Compare all words using a custom compare
 * function.
 *
 * Refer:
 * https://leetcode.com/problems/verifying-an-alien-dictionary/discuss/203185/JavaC++Python-Mapping-to-Normal-Order
 *
 * Time Complexity: O(O + N * L) = O(26 + N*L) = O(N * L)
 *
 * Space Complexity: O(O) = O(26) = O(1)
 *
 * O = Length of order string. N = Number of words. L = Average length of each
 * word.
 */
class Solution2 {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || order == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int numWords = words.length;
        if (numWords <= 1) {
            return true;
        }

        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        for (int i = 1; i < numWords; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            int word1Len = word1.length();
            int word2Len = word2.length();
            int minLen = Math.min(word1Len, word2Len);
            int idx = 0;

            for (; idx < minLen; idx++) {
                char c1 = word1.charAt(idx);
                char c2 = word2.charAt(idx);
                if (c1 != c2) {
                    if (orderMap.get(c1) > orderMap.get(c2)) {
                        return false;
                    }
                    break;
                }
            }
            if (idx == minLen && word1Len > word2Len) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Save order in a map with index. Compare all words using a custom compare
 * function.
 *
 * Refer:
 * https://leetcode.com/problems/verifying-an-alien-dictionary/discuss/203185/JavaC++Python-Mapping-to-Normal-Order
 *
 * Time Complexity: O(26 + N * L) = O(N * L)
 *
 * Space Complexity: O(26) = O(1)
 *
 * O = Length of order string. N = Number of words. L = Average length of each
 * word.
 */
class Solution3 {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || order == null || order.length() != 26) {
            return false;
        }
        if (words.length < 2) {
            return true;
        }

        int[] oMap = new int[26];

        for (int i = 0; i < order.length(); i++) {
            oMap[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            if (compareWords(words[i - 1], words[i], oMap) > 0) {
                return false;
            }
        }

        return true;
    }

    private int compareWords(String w1, String w2, int[] oMap) {
        int l1 = w1.length();
        int l2 = w2.length();
        int l = Math.min(l1, l2);

        for (int i = 0; i < l; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                return oMap[w1.charAt(i) - 'a'] - oMap[w2.charAt(i) - 'a'];
            }
        }

        return l1 - l2;
    }
}