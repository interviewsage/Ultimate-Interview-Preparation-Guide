// LeetCode Question URL: https://leetcode.com/problems/expression-add-operators/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Backtracking
 *
 * https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
 *
 * <pre>
 * Refer for Time Complexity: https://leetcode.com/problems/expression-add-operators/solution/196080
 *
 * Since there are N-1 places where operators (+, - or *) can be inserted. At
 * each place, there are 4 choices: +, -, *, or no operator. Thus there can be
 * 4^(N-1) possible expressions.
 *
 * Each expression takes O(2*N - 1) to build in worst case (which is O(N)).
 *
 * Time Complexity: O(N * 3 * 4^(N-1)).
 *
 * Space Complexity: O(2N-1 + N) ==> String Builder + Recursion Depth.
 * </pre>
 *
 * N = Length of input string.
 */
class Solution1 {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }

        addOperatorsHelper(result, new StringBuilder(), num, 0, target, 0);

        return result;
    }

    private void addOperatorsHelper(List<String> result, StringBuilder sb, String num, int idx, long remaining,
            long prev) {
        if (idx == num.length()) {
            if (remaining == 0) {
                result.add(sb.toString());
            }
            return;
        }

        for (int i = idx; i < num.length(); i++) {
            if (i != idx && num.charAt(idx) == '0') {
                break;
            }

            long cur = Long.parseLong(num.substring(idx, i + 1));
            int curLen = sb.length();

            if (idx == 0) {
                sb.append(cur);
                addOperatorsHelper(result, sb, num, i + 1, remaining - cur, cur);
                sb.setLength(curLen);
            } else {
                sb.append("+").append(cur);
                addOperatorsHelper(result, sb, num, i + 1, remaining - cur, cur);
                sb.setLength(curLen);

                sb.append("-").append(cur);
                addOperatorsHelper(result, sb, num, i + 1, remaining + cur, -cur);
                sb.setLength(curLen);

                sb.append("*").append(cur);
                addOperatorsHelper(result, sb, num, i + 1, remaining + prev - prev * cur, prev * cur);
                sb.setLength(curLen);
            }
        }
    }
}

class Solution2 {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }

        helper(result, new StringBuilder(), num, target, 0, 0, 0);

        return result;
    }

    private void helper(List<String> result, StringBuilder sb, String num, int target, int idx, long eval, long prev) {
        if (num.length() == idx) {
            if (eval == target) {
                result.add(sb.toString());
            }
            return;
        }

        for (int i = idx; i < num.length(); i++) {
            if (i != idx && num.charAt(idx) == '0') {
                // A number cannot start with zero if it has more than 1 digits.
                break;
            }

            long cur = Long.parseLong(num.substring(idx, i + 1));
            int len = sb.length();

            if (idx == 0) {
                // Since this recursion starts are idx zero, thus no operator can be added.
                sb.append(cur);
                helper(result, sb, num, target, i + 1, cur, cur);
                sb.setLength(len);
            } else {
                // Adding '+'
                sb.append('+').append(cur);
                helper(result, sb, num, target, i + 1, eval + cur, cur);
                sb.setLength(len);

                // Adding '-'
                sb.append('-').append(cur);
                helper(result, sb, num, target, i + 1, eval - cur, -cur);
                sb.setLength(len);

                // Adding '*'
                sb.append('*').append(cur);
                helper(result, sb, num, target, i + 1, eval - prev + prev * cur, prev * cur);
                sb.setLength(len);
            }
        }
    }
}