// LeetCode Question URL: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int maxDepth(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        int open = 0;
        int maxDepth = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                maxDepth = Math.max(maxDepth, ++open);
            } else if (c == ')') {
                if (open-- == 0) {
                    throw new IllegalArgumentException("Input string is not a valid parentheses string");
                }
            }
        }

        if (open > 0) {
            throw new IllegalArgumentException("Input string is not a valid parentheses string");
        }
        return maxDepth;
    }
}
