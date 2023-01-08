// LeetCode Question URL: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
// LeetCode Discuss URL: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/discuss/1496838/Java-or-TC:-O(N)-or-SC:-O(K)-or-One-Pass-Sliding-Window-using-LinkedHashMap

import java.util.*;

/**
 * One-Pass Sliding Window using LinkedHashMap
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(K)
 *
 * N = Length of input string s
 */
class Solution1 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (len == 0 || k == 0) {
            return 0;
        }
        if (len <= k) {
            return len;
        }

        LinkedHashMap<Character, Integer> charIdxMap = new LinkedHashMap<>();
        int start = 0;
        int maxLen = 0;

        for (int end = 0; end < len; end++) {
            char eChar = s.charAt(end);
            charIdxMap.remove(eChar);
            charIdxMap.put(eChar, end);

            if (charIdxMap.size() > k) {
                Map.Entry<Character, Integer> oldestEntry = charIdxMap.entrySet().iterator().next();
                charIdxMap.remove(oldestEntry.getKey());
                start = oldestEntry.getValue() + 1;
            } else {
                maxLen = Math.max(maxLen, end - start + 1);
            }
        }

        return maxLen;
    }
}

/**
 * Sliding Window
 *
 * Time Complexity : O(N) - Each character is added and removed once.
 *
 * Space Complexity: O(k) - Map can grow to size k+1
 *
 * N = length of input string s
 *
 * Proof of Time Complexity:
 *
 * Suppose there are M distinct chars in input string, each char is repeating j
 * times on average. Thus total number of chars = M * j = N. Thus number of
 * operations:
 *
 * 1) To add into map = N,
 *
 * 2) M-k chars will be removed from map. thus (M-k)*j remove operations are
 * performed.
 *
 * Thus total operations = N + (M-k)*j. Here k is very small, thus total
 * operations in worst case = 2*N
 */
class Solution2 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (k == 0 || len == 0) {
            return 0;
        }
        if (len <= k) {
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

            while (countMap.size() > k) {
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
