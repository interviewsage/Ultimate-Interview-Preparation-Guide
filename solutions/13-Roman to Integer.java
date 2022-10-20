// LeetCode Question URL: https://leetcode.com/problems/roman-to-integer/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input roman string.
 */
class Solution {

    private static final Map<Character, Integer> ROMAN_SYMBOL_MAP = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);

    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int result = ROMAN_SYMBOL_MAP.get(s.charAt(0));
        int pre = result;

        for (int i = 1; i < s.length(); i++) {
            int cur = ROMAN_SYMBOL_MAP.get(s.charAt(i));
            result += cur;
            if (pre < cur) {
                result -= 2 * pre;
            }
            pre = cur;
        }

        return result;
    }
}
