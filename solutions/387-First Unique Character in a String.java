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
        if (s == null || s.length() == 0) {
            return -1;
        }

        int len = s.length();
        if (len == 1) {
            return 0;
        }

        Map<Character, Integer> nonRepeatingChars = new LinkedHashMap<>();
        Set<Character> repeatingChars = new HashSet<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (repeatingChars.contains(c)) {
                continue;
            }
            if (nonRepeatingChars.containsKey(c)) {
                nonRepeatingChars.remove(c);
                repeatingChars.add(c);
            } else {
                nonRepeatingChars.put(c, i);
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
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input string.
 */
class Solution2 {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        if (s.length() == 1) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}
