// LeetCode Question URL: https://leetcode.com/problems/valid-anagram/
// LeetCode Discuss URL: https://leetcode.com/problems/valid-anagram/discuss/1555784/Java-TC:O(N)-or-SC:O(1)-or-Simple-and-Concise-HashMap-solution-(wo-Sorting)

import java.util.*;

/**
 * <pre>
 * Time Complexity: O(N + Size of CharacterSet) ==> LenS == LenT
 *                  O(1) ==> LenS != LenT
 *
 * Space Complexity: O(2 * Size of CharacterSet).
 *
 * N = Length of input string S or T.
 * Size of CharacterSet will be constant. Thus TC will be O(N) & SC will be O(1)
 * </pre>
 */
class Solution1 {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int lenS = s.length();
        if (lenS != t.length()) {
            return false;
        }
        if (lenS == 0) {
            return true;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < lenS; i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            countMap.put(charS, countMap.getOrDefault(charS, 0) + 1);
            countMap.put(charT, countMap.getOrDefault(charT, 0) - 1);
            if (countMap.size() > lenS) {
                return false;
            }
        }

        for (int count : countMap.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}

/**
 * <pre>
 * Time Complexity: O(N + Size of CharacterSet) ==> LenS == LenT
 *                  O(1) ==> LenS != LenT
 *
 * Space Complexity: O(2 * Size of CharacterSet).
 *
 * N = Length of input string S or T.
 * Size of CharacterSet will be constant. Thus TC will be O(N) & SC will be O(1)
 * </pre>
 */
class Solution2 {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int len = s.length();
        // Finding the count of each character in string S
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        // Subtracting the each character in string T.
        // If an extra char is found, we can return false.
        for (int i = 0; i < len; i++) {
            char c = t.charAt(i);
            Integer count = countMap.get(c);
            if (count == null) {
                return false;
            }
            if (count.equals(1)) {
                countMap.remove(c);
            } else {
                countMap.put(c, count - 1);
            }
        }

        return true;
    }
}
