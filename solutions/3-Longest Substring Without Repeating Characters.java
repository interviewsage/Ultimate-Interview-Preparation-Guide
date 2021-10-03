// LeetCode Question URL: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// LeetCode Discuss URL: https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/1500874/Java-or-TC:-O(N)-or-SC:-O(1)-or-Sliding-Window-using-HashMap-and-Two-Pointers

import java.util.*;

/**
 * Use HashMap to keep char and its index map. When we find a repeating char
 * update the start point.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(min(M,N)) = O(1) since there are 26 alphabets.
 *
 * N = Length of input string. M = Size of the character set
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return len;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLen = 0;

        for (int end = 0; end < len; end++) {
            char eChar = s.charAt(end);
            if (map.containsKey(eChar)) {
                start = Math.max(start, map.get(eChar) + 1);
            }
            map.put(eChar, end);
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }
}
