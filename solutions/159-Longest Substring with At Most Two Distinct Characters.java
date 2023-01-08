// LeetCode Question URL: https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
// LeetCode Discuss URL: https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/discuss/1496840/Java-or-TC:-O(N)-or-SC:-O(1)-or-One-Pass-Sliding-Window-using-LinkedHashMap

import java.util.*;

/**
 * One-Pass Sliding Window using LinkedHashMap
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1). LinkedHashMap will contain maximum 3 entries.
 *
 * N = Length of input string s
 */
class Solution1 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 2) {
            return len;
        }

        LinkedHashMap<Character, Integer> charIdxMap = new LinkedHashMap<>();
        int start = 0;
        int maxLen = 0;

        for (int end = 0; end < len; end++) {
            char eChar = s.charAt(end);
            charIdxMap.remove(eChar);
            charIdxMap.put(eChar, end);

            if (charIdxMap.size() > 2) {
                Map.Entry<Character, Integer> oldestEntry = charIdxMap.entrySet().iterator().next();
                charIdxMap.remove(oldestEntry.getKey());
                start = oldestEntry.getValue() + 1;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }
}

/**
 * One-Pass Sliding Window using HashMap and Collections.min
 *
 * Time Complexity: O(N). HashMap will contain maximum 3 entries. Thus,
 * Collections.min will be a constant time operation.
 *
 * Space Complexity: O(1). HashMap will contain maximum 3 entries.
 *
 * N = Length of input string s
 */
class Solution2 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 2) {
            return len;
        }

        HashMap<Character, Integer> charIdxMap = new HashMap<>();
        int start = 0;
        int maxLen = 0;

        for (int end = 0; end < len; end++) {
            char eChar = s.charAt(end);
            charIdxMap.put(eChar, end);

            if (charIdxMap.size() > 2) {
                int idx = Collections.min(charIdxMap.values());
                charIdxMap.remove(s.charAt(idx));
                start = idx + 1;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }
}

/**
 * Sliding Window.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1) - Map will max grow to size 3.
 *
 * N = length of the input string.
 */
class Solution3 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (len <= 2) {
            return len;
        }

        HashMap<Character, Integer> countMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLen = 0;

        while (end < len) {
            char eChar = s.charAt(end);
            countMap.put(eChar, countMap.getOrDefault(eChar, 0) + 1);
            end++;

            while (countMap.size() > 2) {
                char sChar = s.charAt(start);
                int count = countMap.get(sChar);
                if (count == 1) {
                    countMap.remove(sChar);
                } else {
                    countMap.put(sChar, count - 1);
                }
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }
}
