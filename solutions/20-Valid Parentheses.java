// LeetCode Question URL: https://leetcode.com/problems/valid-parentheses/
// LeetCode Discuss URL: https://leetcode.com/problems/valid-parentheses/discuss/1529337/Java-or-TC:-O(N)-or-SC:-O(N2)-or-Optimized-Stack-solution-w-Early-Exit-Conditions

import java.util.*;

/**
 * Using Stack. Space Optimized solution with early exit conditions.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N/2 + 1) = O(N)
 *
 * N = Length of input string.
 */
class Solution {
    public boolean isValid(String s) {
        // If the length is odd, return false
        if (s == null || s.length() % 2 != 0) {
            return false;
        }

        int sLen = s.length();
        if (sLen == 0) {
            return true;
        }

        // First Char cannot be closing bracket
        char firstChar = s.charAt(0);
        if (firstChar == ')' || firstChar == '}' || firstChar == ']') {
            return false;
        }
        // Last Char cannot be open bracket
        char lastChar = s.charAt(sLen - 1);
        if (lastChar == '(' || lastChar == '{' || lastChar == '[') {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }

            // Since there are more characters in stack than remaining characters in S, we
            // can early exit.
            if (stack.size() > sLen - i) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
