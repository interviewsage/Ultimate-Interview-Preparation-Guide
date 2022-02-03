// LeetCode Question URL: https://leetcode.com/problems/longest-valid-parentheses/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/articles/longest-valid-parentheses/#approach-4-without-extra-space
 *
 * Time Complexity: O(2 * N). 2 Pass
 *
 * Space Complexity: O(1)
 *
 * N = Length of input string.
 */
class Solution1 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int count = 0;
        int len = s.length();
        int maxLen = 0;
        int lastInvalidIdx = -1;
        int lastValidIdx = -1;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else {
                count--;
                if (count == 0) {
                    maxLen = Math.max(maxLen, i - lastInvalidIdx);
                    lastValidIdx = i;
                } else if (count < 0) {
                    lastInvalidIdx = i;
                    count = 0;
                }
            }
        }

        count = 0;
        int end = Math.max(lastInvalidIdx, lastValidIdx);
        lastInvalidIdx = len;
        for (int i = len - 1; i > end; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                count--;
            } else {
                count++;
                if (count == 0) {
                    maxLen = Math.max(maxLen, lastInvalidIdx - i);
                } else if (count > 0) {
                    lastInvalidIdx = i;
                    count = 0;
                }
            }
        }

        return maxLen;
    }
}

class Solution2 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int open = 0;
        int close = 0;
        int maxLen = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(maxLen, open * 2);
            } else if (close > open) {
                open = 0;
                close = 0;
            }
        }

        open = 0;
        close = 0;

        for (int i = len - 1; i >= 0; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(maxLen, open * 2);
            } else if (open > close) {
                open = 0;
                close = 0;
            }
        }

        return maxLen;
    }
}

/**
 * Using stack
 *
 * Refer: 1)
 * https://leetcode.com/problems/longest-valid-parentheses/discuss/14126/My-O(n)-solution-using-a-stack
 * 2)
 * https://leetcode.com/articles/longest-valid-parentheses/#approach-3-using-stack
 *
 * Time Complexity: O(N). 1 Pass
 *
 * Space Complexity: O(N). Worst case is when all are open brackets.
 *
 * N = Length of input string.
 */
class Solution3 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
