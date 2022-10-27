// LeetCode Question URL: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// LeetCode Discuss URL: https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/1500874/Java-or-TC:-O(N)-or-SC:-O(1)-or-Sliding-Window-using-HashMap-and-Two-Pointers

import java.util.*;

/**
 * Use HashMap to keep char and its index map. When we find a repeating char
 * update the start point.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(2 * min(U, 26)) = O(1) since there are 26 alphabets.
 *
 * N = Length of input string S. U = Unique number of characters in S.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = s.length();
        if (len <= 1) {
            return len;
        }

        Map<Character, Integer> charMap = new HashMap<>();
        int start = 0;
        int maxLen = 0;
        for (int end = 0; end < len; end++) {
            char eChar = s.charAt(end);
            Integer idx = charMap.get(eChar);
            if (idx != null && start < idx + 1) {
                maxLen = Math.max(maxLen, end - start);
                start = idx + 1;
            }
            charMap.put(eChar, end);
        }

        return Math.max(maxLen, len - start);
    }
}
