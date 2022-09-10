// LeetCode URL: https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

import java.util.HashSet;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/solution/#approach-3-shortened-two-pass-string-builder
 *
 * Time Complexity : O(3 * N) = O(N)
 *
 * Space Complexity in worst case : O(N). If a index is added in HashSet, then
 * that character will not be added to StringBuilder.
 *
 * HashSet can only have N characters in worst case if all characters are
 * closing brackets
 *
 * N = Length of Input string
 */
class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len == 0) {
            return s;
        }

        int openSeen = 0;
        int balance = 0;
        HashSet<Integer> toBeRemoved = new HashSet<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                openSeen++;
                balance++;
            } else if (c == ')') {
                if (balance == 0) {
                    toBeRemoved.add(i);
                } else {
                    balance--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int openKeep = openSeen - balance;

        for (int i = 0; i < len; i++) {
            if (toBeRemoved.contains(i)) {
                continue;
            }

            char c = s.charAt(i);
            if (c == '(') {
                if (openKeep == 0) {
                    continue;
                } else {
                    openKeep--;
                }
            }

            sb.append(c);
        }

        return sb.toString();
    }
}
