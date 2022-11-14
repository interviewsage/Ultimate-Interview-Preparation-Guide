// LeetCode Question URL: https://leetcode.com/problems/generate-parentheses/
// LeetCode Discuss URL: https://leetcode.com/problems/generate-parentheses/discuss/1545154/Java-or-TC:-O(4Nsqrt(N))-or-SC:-O(N)-or-Backtracking-and-Iterative-Solutions

import java.util.*;

/**
 * Backtracking solution
 *
 * Refer:
 * https://leetcode.com/articles/generate-parentheses/#approach-2-backtracking
 *
 * Time Complexity: Upper Bound O(2N * 2^(2N)). As each place has only 2
 * options.
 *
 * Since number of valid combinations is equal to n-th Catalan number. n-th
 * Catalan number = 1/(n+1) * C(2n, n) which is bounded asymptotically by 4^N /
 * (N * sqrt(N)). Thus time complexity will be O(4^N / sqrt(N)).
 *
 * Space Complexity: O(N) = O(N) -> Recursion stack size + StringBuilder.
 * O(4^N / sqrt(N)) to save the valid combinations in result list.
 *
 * N = Input number.
 */
class Solution1 {
    public List<String> generateParenthesis(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input n is invalid");
        }

        List<String> result = new ArrayList<>();
        generateParenthesisHelper(result, n, n, new StringBuilder());
        return result;
    }

    private void generateParenthesisHelper(List<String> result, int open, int close, StringBuilder sb) {
        if (open == 0 && close == 0) {
            result.add(sb.toString());
            return;
        }

        if (open > 0) {
            sb.append('(');
            generateParenthesisHelper(result, open - 1, close, sb);
            sb.setLength(sb.length() - 1);
        }
        if (close > open) {
            sb.append(')');
            generateParenthesisHelper(result, open, close - 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}

class Solution2 {
    class State {
        String s;
        int open;
        int close;

        public State(String _s, int _open, int _close) {
            s = _s;
            open = _open;
            close = _close;
        }
    }

    public List<String> generateParenthesis(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input n is invalid");
        }

        List<String> result = new ArrayList<>();
        if (n == 0) {
            result.add("");
            return result;
        }

        Deque<State> stack = new ArrayDeque<>();
        stack.push(new State("", n, n));

        while (!stack.isEmpty()) {
            State cur = stack.pop();
            if (cur.open == 0 && cur.close == 0) {
                result.add(cur.s);
                continue;
            }

            if (cur.open > 0) {
                stack.push(new State(cur.s + '(', cur.open - 1, cur.close));
            }
            if (cur.close > cur.open) {
                stack.push(new State(cur.s + ')', cur.open, cur.close - 1));
            }
        }

        return result;
    }
}

/**
 * Iterative Solution
 *
 * Refer:
 * https://leetcode.com/problems/generate-parentheses/discuss/10127/An-iterative-method.
 *
 * <pre>
 * Time and Space Complexity
 *      = C0 + 2 (1*C1 + 2*C2 + 3*C3 + 4*C4 + ... + N*CN)
 *
 * Where, C0 -> 0th Catalan Number
 *        C1 -> 1st Catalan Number
 *        ...
 *        CN -> Nth Catalan Number = 1/(n+1) * C(2n, n)
 *              Nth Catalan Number is bounded asymptotically by 4^N / (N * sqrt(N))
 * </pre>
 *
 * N = Input number
 */
class Solution3 {
    public List<String> generateParenthesis(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }

        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));

        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String first : lists.get(j)) {
                    for (String second : lists.get(i - 1 - j)) {
                        StringBuilder sb = new StringBuilder("(");
                        sb.append(first).append(")").append(second);
                        list.add(sb.toString());
                    }
                }
            }
            lists.add(list);
        }

        return lists.get(n);
    }
}
