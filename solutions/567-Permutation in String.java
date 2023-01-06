// LeetCode Question URL: https://leetcode.com/problems/permutation-in-string/
// LeetCode Discuss URL: https://leetcode.com/problems/permutation-in-string/discuss/1500902/Java-or-TC:-O(S2)-or-SC:-O(1)-or-Constant-space-Sliding-Window-solution

import java.util.*;

/**
 * Sliding Window
 *
 * Time Complexity: O(S1 + S2)
 *
 * Space Complexity: O(min(S1, M))
 *
 * S1 = Length of input string s1. S2 = Length of input string s2. M = Range of
 * character set which is 26 in this case.
 */
class Solution1 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0) {
            return true;
        }
        if (len1 > len2) {
            return false;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            char c = s1.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int toBeMatched = countMap.size();

        for (int i = 0; i < len2; i++) {
            char c = s2.charAt(i);
            Integer count = countMap.get(c);
            if (count != null) {
                if (count == 1) {
                    toBeMatched--;
                }
                countMap.put(c, count - 1);
            }

            if (i >= len1) {
                c = s2.charAt(i - len1);
                count = countMap.get(c);
                if (count != null) {
                    if (count == 0) {
                        toBeMatched++;
                    }
                    countMap.put(c, count + 1);
                }
            }

            if (toBeMatched == 0) {
                return true;
            }
        }

        return false;
    }
}

/**
 * Sliding Window
 *
 * Time Complexity: O(S1 + (S2-S1)) = O(S2)
 *
 * Space Complexity: O(M) = O(26) = O(1)
 *
 * S1 = Length of input string s1. S2 = Length of input string s2. M = Range of
 * character set which is 26 in this case.
 */
class Solution2 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 == 0) {
            return true;
        }
        if (l2 < l1) {
            return false;
        }

        int[] countMap = new int[26];

        for (int i = 0; i < l1; i++) {
            // Adding Characters of S1 in the window
            countMap[s1.charAt(i) - 'a']++;
            // Removing Characters of S2 in the window
            countMap[s2.charAt(i) - 'a']--;
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            // Counting the characters which have count as zero.
            // Either these characters are not present in the window or appear same number
            // of times in the window.
            if (countMap[i] == 0) {
                count++;
            }
        }
        // If count is 26, all S1 characters appear same number of times in S2.
        if (count == 26) {
            return true;
        }

        for (int i = l1; i < l2; i++) {
            // Adding new character in the window.
            int r = s2.charAt(i) - 'a';
            countMap[r]--;
            if (countMap[r] == 0) {
                count++;
            } else if (countMap[r] == -1) {
                count--;
            }

            // Removing old character from the window.
            int l = s2.charAt(i - l1) - 'a';
            countMap[l]++;
            if (countMap[l] == 0) {
                count++;
            } else if (countMap[l] == 1) {
                count--;
            }

            if (count == 26) {
                return true;
            }
        }

        return false;
    }
}
