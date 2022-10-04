// LeetCode Question URL: https://leetcode.com/problems/strobogrammatic-number/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input num.
 */
class Solution1 {
    public boolean isStrobogrammatic(String num) {
        if (num == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int start = 0;
        int end = num.length() - 1;

        while (start <= end) {
            char s = num.charAt(start++);
            char e = num.charAt(end--);

            if ((s == e && (s == '0' || s == '1' || s == '8'))
                    || (s == '6' && e == '9')
                    || (s == '9' && e == '6')) {
                continue;
            }

            return false;
        }

        return true;
    }
}

/**
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input num.
 */
class Solution2 {
    private static final Map<Character, Character> MATCH_MAP = Map.of('0', '0', '1', '1', '8', '8', '6', '9', '9', '6');

    public boolean isStrobogrammatic(String num) {
        if (num == null) {
            return false;
        }

        int start = 0;
        int end = num.length() - 1;

        while (start <= end) {
            Character matchingChar = MATCH_MAP.get(num.charAt(start++));
            if (matchingChar == null || !matchingChar.equals(num.charAt(end--))) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Do not refer this solution.
 *
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input num.
 */
class Solution3 {
    public boolean isStrobogrammatic(String num) {
        if (num == null) {
            return false;
        }

        int left = 0;
        int right = num.length() - 1;

        while (left <= right) {
            if (!"00 11 88 696".contains(num.charAt(left) + "" + num.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
