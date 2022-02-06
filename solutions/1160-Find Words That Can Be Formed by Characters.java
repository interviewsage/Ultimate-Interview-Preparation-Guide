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
        if (words == null || words.length == 0 || chars == null || chars.length() == 0) {
            return 0;
        }

        int charsLen = chars.length();
        Map<Character, Integer> charsCountMap = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            charsCountMap.put(c, charsCountMap.getOrDefault(c, 0) + 1);
        }

        int result = 0;
        for (String w : words) {
            Map<Character, Integer> wCountMap = new HashMap<>();
            int len = w.length();
            if (len > charsLen) {
                continue;
            }

            int i = 0;
            while (i < len) {
                char c = w.charAt(i);
                Integer charsCount = charsCountMap.get(c);
                if (charsCount == null) {
                    break;
                }
                int count = wCountMap.getOrDefault(c, 0);
                if (count < charsCount) {
                    wCountMap.put(c, count + 1);
                } else {
                    break;
                }
                i++;
            }
            if (i == len) {
                result += len;
            }
        }

        return result;
    }
}
