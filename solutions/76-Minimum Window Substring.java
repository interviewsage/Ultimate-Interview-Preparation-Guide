// LeetCode Question URL: https://leetcode.com/problems/minimum-window-substring/
// LeetCode Discuss URL: https://leetcode.com/problems/minimum-window-substring/discuss/1496754/Java-or-TC:-O(S+T)-or-SC:-O(T)-or-Space-optimized-Sliding-Window-using-Two-Pointers

import java.util.*;
import javafx.util.*;

/**
 * Space-optimized Sliding Window using Two Pointers
 *
 * Time Complexity: O(S + T)
 *
 * Space Complexity: O(T)
 *
 * S = length of String s. T = length of String t
 */
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen || tLen == 0) {
            return "";
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int minStart = 0;
        int minLen = sLen + 1;
        int start = 0;
        int end = 0;

        while (end < sLen) {
            char eChar = s.charAt(end++);
            Integer eCount = countMap.get(eChar);
            if (eCount != null) {
                if (eCount > 0) {
                    tLen--;
                }
                countMap.put(eChar, eCount - 1);
            }

            while (tLen == 0) {
                if (minLen > end - start) {
                    minStart = start;
                    minLen = end - start;
                }
                char sChar = s.charAt(start++);
                Integer sCount = countMap.get(sChar);
                if (sCount != null) {
                    // Here sCount can be negative or zero. As soon as the count becomes greater
                    // than zero, the window is not valid.
                    if (sCount == 0) {
                        tLen++;
                    }
                    countMap.put(sChar, sCount + 1);
                }
            }
        }

        return minLen == sLen + 1 ? "" : s.substring(minStart, minStart + minLen);
    }
}

/**
 * Sliding Window. This solution adds an optimization for very long string S
 * when compared to string T. Also string S contains lot of characters not in T.
 *
 * Time Complexity: O(S + T)
 *
 * Space Complexity: O(S + T)
 *
 * S = length of String S. T = length of String T
 */
class Solution2 {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<>();
        // Adding all characters in string T in the map with their count.
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        ArrayList<Pair<Integer, Character>> filteredS = new ArrayList<>();
        // Creating a new list with characters in S that are present in T too.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                filteredS.add(new Pair<Integer, Character>(i, c));
            }
        }

        int charTLeft = t.length();
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int start = 0;
        int end = 0;

        // Sliding Window
        while (end < filteredS.size()) {
            Pair<Integer, Character> ePair = filteredS.get(end);
            int eIdx = ePair.getKey();
            char eChar = ePair.getValue();

            if (map.get(eChar) > 0) {
                charTLeft--;
            }
            map.put(eChar, map.get(eChar) - 1);
            end++;

            while (charTLeft == 0) {
                Pair<Integer, Character> sPair = filteredS.get(start);
                int sIdx = sPair.getKey();
                char sChar = sPair.getValue();

                if (minLen > eIdx - sIdx + 1) {
                    minLen = eIdx - sIdx + 1;
                    minStart = sIdx;
                }

                map.put(sChar, map.get(sChar) + 1);
                if (map.get(sChar) > 0) {
                    charTLeft++;
                }
                start++;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(minStart, minStart + minLen);
    }
}
