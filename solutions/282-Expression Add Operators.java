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
 * Time Complexity:
 * 1. To append in StringBuilder: O(2*N - 1)
 * 2. toString: O(2*N - 1)
 * 3. substring: O(N)
 * 4. parseLong: O(N)
 *
 * Total time complexity: O(2*(2N-1) + N + N) * (4^(N-1)) = O(N * 4^N)
 *
 * Space Complexity: O(2N-1 + N) ==> String Builder + Recursion Depth. = O(N)
 * </pre>
 *
 * N = Length of input string.
 */
class Solution1 {
    public List<String> addOperators(String num, int target) {
        if (num == null) {
            throw new IllegalArgumentException("Input is null");
        }

        List<String> result = new ArrayList<>();
        int len = num.length();
        if (len == 0) {
            return result;
        }
        if (len == 1) {
            if (target == Integer.parseInt(num)) {
                result.add(num);
            }
            return result;
        }

        addOperatorsHelper(result, new StringBuilder(), num, 0, target, 0);
        return result;
    }

    private void addOperatorsHelper(List<String> result, StringBuilder sb, String num, int idx, long remaining,
            long prev) {
        int numLen = num.length();
        if (idx == numLen) {
            if (remaining == 0) {
                result.add(sb.toString());
            }
            return;
        }

        int curSbLen = sb.length();
        boolean isIdxZeroChar = num.charAt(idx) == '0';
        for (int i = idx; i < numLen; i++) {
            if (isIdxZeroChar && i != idx) {
                // A number cannot start with zero if it has more than 1 digits.
                break;
            }

            String curString = num.substring(idx, i + 1);
            long curNum = Long.parseLong(num.substring(idx, i + 1));

            if (idx == 0) {
                // Since this recursion starts are idx zero, thus no operator can be added.
                sb.append(curString);
                addOperatorsHelper(result, sb, num, i + 1, remaining - curNum, curNum);
                sb.setLength(curSbLen);
            } else {
                sb.append('+').append(curString);
                addOperatorsHelper(result, sb, num, i + 1, remaining - curNum, curNum);
                sb.setLength(curSbLen);

                sb.append('-').append(curString);
                addOperatorsHelper(result, sb, num, i + 1, remaining + curNum, -curNum);
                sb.setLength(curSbLen);

                sb.append('*').append(curString);
                addOperatorsHelper(result, sb, num, i + 1, remaining + prev - prev * curNum, prev * curNum);
                sb.setLength(curSbLen);
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