// LeetCode Question URL: https://leetcode.com/problems/valid-anagram/
// LeetCode Discuss URL: https://leetcode.com/problems/valid-anagram/discuss/1555784/Java-TC:O(N)-or-SC:O(1)-or-Simple-and-Concise-HashMap-solution-(wo-Sorting)

import java.util.*;

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input string S or T.
 */
class Solution1 {
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

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(2*N) = O(N). In worst case if both strings have all
 * different characters, then map will have 2*N chars.
 *
 * N = Length of input string S or T.
 */
class Solution2 {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int len = s.length();
        // Finding the diff count of all characters in S & T
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            countMap.put(charS, countMap.getOrDefault(charS, 0) + 1);
            countMap.put(charT, countMap.getOrDefault(charT, 0) - 1);
        }

        if (countMap.size() > len) {
            return false;
        }

        // All Diff Counts should be zero.
        // If not, then return false.
        for (int count : countMap.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
