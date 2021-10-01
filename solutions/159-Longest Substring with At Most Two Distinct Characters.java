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
    class Solution {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s == null) {
                throw new IllegalArgumentException("Input is invalid");
            }

            int len = s.length();
            if (len <= 2) {
                return len;
            }

            LinkedHashMap<Character, Integer> idxMap = new LinkedHashMap<>();
            int start = 0;
            int end = 0;
            int maxLen = 0;

            while (end < len) {
                char eChar = s.charAt(end);
                idxMap.remove(eChar);
                idxMap.put(eChar, end);
                end++;

                if (idxMap.size() > 2) {
                    Map.Entry<Character, Integer> leftMostEntry = idxMap.entrySet().iterator().next();
                    idxMap.remove(leftMostEntry.getKey());
                    start = leftMostEntry.getValue() + 1;
                }

                maxLen = Math.max(maxLen, end - start);
            }

            return maxLen;
        }
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
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (len <= 2) {
            return len;
        }

        HashMap<Character, Integer> idxMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLen = 0;

        while (end < len) {
            char eChar = s.charAt(end);
            idxMap.put(eChar, end);
            end++;

            if (idxMap.size() > 2) {
                int idx = Collections.min(idxMap.values());
                idxMap.remove(s.charAt(idx));
                start = idx + 1;
            }

            maxLen = Math.max(maxLen, end - start);
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
