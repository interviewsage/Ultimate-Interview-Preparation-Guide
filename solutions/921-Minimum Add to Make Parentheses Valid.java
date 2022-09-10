// LeetCode Question URL: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

/**
 * Similar to Valid Parentheses question on leetcode.
 *
 * Keep thr count of open brackets and keep subtracting 1 for closing brackets.
 * Whenever the count becomes less than zero, then we have to insert a bracket
 * at the position to make the string valid.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1).
 *
 * N = Length of the input array.
 */
class Solution {
    public int minAddToMakeValid(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int toBeAdded = 0;
        int remainingOpenCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                remainingOpenCount++;
            } else if (c == ')') {
                if (remainingOpenCount == 0) {
                    toBeAdded++;
                    continue;
                }
                remainingOpenCount--;
            }
        }

        return toBeAdded + remainingOpenCount;
    }
}
