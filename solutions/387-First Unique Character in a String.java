// LeetCode Question URL: https://leetcode.com/problems/first-unique-character-in-a-string/
// LeetCode Discuss URL:

import java.util.*;

/**
 * One Pass Solution.
 *
 * Using LinkedHashMao to maintain the insertion order. Using HashSet to check
 * if the char was already seen.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input string.
 */
class Solution1 {
    public int firstUniqChar(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return 0;
        }

        Set<Character> charsSeenSoFar = new HashSet<>();
        Map<Character, Integer> nonRepeatingChars = new LinkedHashMap<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (charsSeenSoFar.add(c)) {
                nonRepeatingChars.put(c, i);
            } else {
                nonRepeatingChars.remove(c);
            }
        }

        return nonRepeatingChars.size() == 0 ? -1 : nonRepeatingChars.values().iterator().next();
    }
}

/**
 * Two Pass Solution.
 *
 * Save the count of each character and then find the first char that occurs
 * only once.
 *
 * Time Complexity: O(N + U) = O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input string. U = Unique chars in the string.
 */
class Solution2 {
    public int firstUniqChar(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return 0;
        }

        Map<Character, Integer> idxMap = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            Integer idx = idxMap.putIfAbsent(c, i);
            if (idx != null && idx != -1) {
                idxMap.put(c, -1);
            }
        }

        for (int idx : idxMap.values()) {
            if (idx != -1) {
                return idx;
            }
        }

        return -1;
    }
}
