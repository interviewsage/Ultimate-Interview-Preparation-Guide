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
class Solution1 {

    private static final Map<Character, Character> MATCH_MAP = Map.of('(', ')', '[', ']', '{', '}');
    private static final Set<Character> OPEN_SET = MATCH_MAP.keySet();
    private static final Set<Character> CLOSE_SET = new HashSet<>(MATCH_MAP.values());

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }

        int len = s.length();
        if (len == 0) {
            return true;
        }

        if (CLOSE_SET.contains(s.charAt(0)) || OPEN_SET.contains(s.charAt(len - 1))) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            Character matchingClose = MATCH_MAP.get(c);
            if (matchingClose != null) {
                stack.push(matchingClose);
                // Below condition can be simplified to `set.size() > (s.length() - i - 1)`
                // Number of chars left is not sufficient to close the current open brackets in
                // stack.
                if ((stack.size() - (len - i - 1)) > 0) {
                    return false;
                }
            } else {
                if (stack.isEmpty() || c != stack.pop()) {
                    return false;
                }
            }

        }

        return true;
    }
}

/**
 * Using Stack. Space Optimized solution with early exit conditions.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N/2 + 1) = O(N)
 *
 * N = Length of input string.
 */
class Solution2 {
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
