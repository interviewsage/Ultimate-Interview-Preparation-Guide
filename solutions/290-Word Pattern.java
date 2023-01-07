// LeetCode Question URL: https://leetcode.com/problems/word-pattern/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Find each word and map it to a Character. If the word or character is
 * repeated, make sure they map to same character or word respectively.
 *
 * <pre>
 * Time Complexity:
 * 1. Split --> O(S)
 * 2. Comparison --> O(P)
 * 3. Thus Time Complexity: O(S + P)
 *
 * Space Complexity: O(S + 2*P) --> Save each word in map. Save each character in map and set.
 * </pre>
 *
 * S = Length of str string. P = Length of pattern string.
 */
class Solution1 {
    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int pLen = pattern.length();
        if (pLen > s.length()) {
            return false;
        }

        String[] strArr = s.split(" ");
        if (pLen != strArr.length) {
            return false;
        }

        Map<String, Character> wordCharMap = new HashMap<>();
        Set<Character> charSeenSet = new HashSet<>();

        for (int i = 0; i < pLen; i++) {
            char pChar = pattern.charAt(i);
            Character charFound = wordCharMap.get(strArr[i]);
            if (charFound == null) {
                if (!charSeenSet.add(pChar)) {
                    return false;
                }
                wordCharMap.put(strArr[i], pChar);
            } else if (charFound != pChar) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Assign index to each word and char combination. Make sure the index is same
 * if the word and pattern combination is seen again.
 *
 * Time Complexity: O(S + P)
 *
 * Space Complexity: O(S + 4*P)= O(S + P)
 *
 * S = Length of str string. P = Length of pattern string.
 */
class Solution2 {
    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int pLen = pattern.length();
        if (pLen > s.length()) {
            return false;
        }

        String[] strArr = s.split(" ");
        if (pLen != strArr.length) {
            return false;
        }

        Map<String, Integer> wordIdxMap = new HashMap<>();
        Map<Character, Integer> charIdxMap = new HashMap<>();

        for (int i = 0; i < pLen; i++) {
            Integer oldCharIdx = charIdxMap.put(pattern.charAt(i), i);
            Integer oldWordIdx = wordIdxMap.put(strArr[i], i);
            if (!Objects.equals(oldCharIdx, oldWordIdx)) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Find each word and map it to a Character. If the word or character is
 * repeated, make sure they map to same character or word respectively.
 *
 * <pre>
 * Time Complexity:
 * 1. Here we will iterate over whole S and P together. Since S is longer, we will need O(S) time for iteration.
 * 2. Also, We will need O(S) for all substrings to generate each word.
 * 3. Thus Time Complexity: O(2*S) = O(S)
 *
 * Space Complexity: O(S + 2*P) --> Save each word in map. Save each character in map and set.
 * </pre>
 *
 * S = Length of str string. P = Length of pattern string.
 */
class Solution3 {
    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null || s.length() < pattern.length()) {
            return false;
        }

        int pLen = pattern.length();
        int sLen = s.length();
        int sIdx = 0;
        int pIdx = 0;

        HashMap<String, Character> map = new HashMap<>();
        HashSet<Character> visitedP = new HashSet<>();

        while (sIdx < sLen && pIdx < pLen) {
            int wordStart = sIdx;
            while (sIdx < sLen && s.charAt(sIdx) != ' ') {
                sIdx++;
            }

            String word = s.substring(wordStart, sIdx++);
            char curP = pattern.charAt(pIdx++);

            Character oldP = map.put(word, curP);
            if (oldP == null) {
                if (!visitedP.add(curP)) {
                    return false;
                }
            } else if (!oldP.equals(curP)) {
                return false;
            }
        }

        return pIdx == pLen && sIdx == sLen + 1;
    }
}
