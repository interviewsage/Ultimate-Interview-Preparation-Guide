// LeetCode Question URL: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int maxDepth(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int depth = 0;
        int maxDepth = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                maxDepth = Math.max(maxDepth, ++depth);
            } else if (c == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
}
