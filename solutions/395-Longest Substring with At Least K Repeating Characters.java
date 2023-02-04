// LeetCode Question URL: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sliding Window (Using HashMap)
 *
 * Time Complexity: O(N + 2*N * D) = O(N * 26) = O(N)
 *
 * Space Complexity: O(D) = O(26) = O(1)
 *
 * N = Length of the input string S. D = Count of distinct characters in S,
 * bounded by 26
 */
class Solution1 {
    public int longestSubstring(String s, int k) {
        if (s == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (k <= 1 || len == 0) {
            return len;
        }
        if (len < k) {
            return 0;
        }

        int charsAtLeastK = 0;

        // Finding all distinct characters in input string.
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int count = countMap.getOrDefault(c, 0) + 1;
            if (count == k) {
                charsAtLeastK++;
            }
            countMap.put(c, count);
        }

        int uniqueChars = countMap.size();
        if (uniqueChars == charsAtLeastK) {
            return len;
        }
        uniqueChars = Math.min(uniqueChars, len / k);

        int maxLen = 0;
        for (int i = 1; i <= uniqueChars; i++) {
            countMap = new HashMap<>();
            int start = 0;
            int end = 0;
            charsAtLeastK = 0;

            while (end < len) {
                char eChar = s.charAt(end++);
                int eCount = countMap.getOrDefault(eChar, 0) + 1;
                if (eCount == k) {
                    charsAtLeastK++;
                }
                countMap.put(eChar, eCount);

                while (countMap.size() > i) {
                    char sChar = s.charAt(start++);
                    int sCount = countMap.get(sChar);
                    if (sCount == k) {
                        charsAtLeastK--;
                    }
                    if (sCount == 1) {
                        countMap.remove(sChar);
                    } else {
                        countMap.put(sChar, sCount - 1);
                    }
                }

                // if we found a string where the number of unique chars equals our target and
                // all those chars are repeated at least K times then update max
                if (countMap.size() == i && i == charsAtLeastK) {
                    maxLen = Math.max(maxLen, end - start);
                }
            }
        }

        return maxLen;
    }
}

/**
 * Sliding Window
 *
 * Time Complexity: O(N + 2*N * D) = O(N * 26) = O(N)
 *
 * Space Complexity: O(D) = O(26) = O(1)
 *
 * N = Length of the input string S. D = Count of distinct characters in S,
 * bounded by 26
 */
class Solution2 {
    public int longestSubstring(String s, int k) {
        if (s == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (k <= 1 || len == 0) {
            return len;
        }
        if (len < k) {
            return 0;
        }

        int charsAtLeastK = 0;
        int uniqueCharsInS = 0;
        int[] countMap = new int[26];

        for (int i = 0; i < len; i++) {
            int c = s.charAt(i) - 'a';
            countMap[c]++;
            if (countMap[c] == 1) {
                uniqueCharsInS++;
            }
            if (countMap[c] == k) {
                charsAtLeastK++;
            }
        }

        if (uniqueCharsInS == charsAtLeastK) {
            return len;
        }

        int maxLen = 0;
        uniqueCharsInS = Math.min(uniqueCharsInS, len / k);

        for (int i = 1; i <= uniqueCharsInS; i++) {
            countMap = new int[26];
            int start = 0;
            int end = 0;
            charsAtLeastK = 0;
            int uniqueChars = 0;

            while (end < len) {
                int eChar = s.charAt(end++) - 'a';
                countMap[eChar]++;
                if (countMap[eChar] == 1) {
                    uniqueChars++;
                }
                if (countMap[eChar] == k) {
                    charsAtLeastK++;
                }

                while (uniqueChars > i) {
                    int sChar = s.charAt(start++) - 'a';
                    if (countMap[sChar] == k) {
                        charsAtLeastK--;
                    }
                    countMap[sChar]--;
                    if (countMap[sChar] == 0) {
                        uniqueChars--;
                    }
                }

                // if we found a string where the number of unique chars equals our target and
                // all those chars are repeated at least K times then update max
                if (uniqueChars == i && i == charsAtLeastK) {
                    maxLen = Math.max(maxLen, end - start);
                }
            }
        }

        return maxLen;
    }
}

/**
 * Sliding Window
 *
 * Time Complexity: O(min(26,N) * 2*N) = O(N)
 *
 * Space Complexity: O(26) = O(1)
 *
 * N = Length of the input string S.
 */
class Solution3 {
    public int longestSubstring(String s, int k) {
        if (s == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (k <= 1 || len == 0) {
            return len;
        }
        if (len < k) {
            return 0;
        }

        int maxLen = 0;
        int maxTarget = Math.min(26, len / k);

        for (int i = 1; i <= maxTarget; i++) {
            int[] countMap = new int[26];
            int start = 0;
            int end = 0;
            int charsAtLeastK = 0;
            int uniqueChars = 0;

            while (end < len) {
                int eChar = s.charAt(end++) - 'a';
                countMap[eChar]++;
                if (countMap[eChar] == 1) {
                    uniqueChars++;
                }
                if (countMap[eChar] == k) {
                    charsAtLeastK++;
                }

                while (uniqueChars > i) {
                    int sChar = s.charAt(start++) - 'a';
                    if (countMap[sChar] == k) {
                        charsAtLeastK--;
                    }
                    countMap[sChar]--;
                    if (countMap[sChar] == 0) {
                        uniqueChars--;
                    }
                }

                // if we found a string where the number of unique chars equals our target and
                // all those chars are repeated at least K times then update max
                if (uniqueChars == i && i == charsAtLeastK) {
                    maxLen = Math.max(maxLen, end - start);
                }
            }
        }

        return maxLen;
    }
}
