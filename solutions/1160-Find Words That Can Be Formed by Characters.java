// LeetCode Question URL: https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(CL + Total Number of Chars in words)
 *
 * Space Complexity: O(2 * CL)
 *
 * CL = Length of chars string.
 */
class Solution {
    public int countCharacters(String[] words, String chars) {
        if (words == null || chars == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int charsLen = chars.length();
        if (charsLen == 0 || words.length == 0) {
            return 0;
        }

        Map<Character, Integer> charsCountMap = new HashMap<>();
        for (int i = 0; i < charsLen; i++) {
            char c = chars.charAt(i);
            charsCountMap.put(c, charsCountMap.getOrDefault(c, 0) + 1);
        }

        int result = 0;

        for (String w : words) {
            int wLen = w.length();
            if (wLen > charsLen) {
                continue;
            }

            Map<Character, Integer> wCountMap = new HashMap<>();
            int i = 0;
            while (i < wLen) {
                char c = w.charAt(i);
                Integer charsCount = charsCountMap.get(c);
                if (charsCount == null) {
                    break;
                }
                int wCount = wCountMap.getOrDefault(c, 0) + 1;
                if (wCount > charsCount) {
                    break;
                }
                wCountMap.put(c, wCount);
                i++;
            }
            if (i == wLen) {
                result += wLen;
            }
        }

        return result;
    }
}
