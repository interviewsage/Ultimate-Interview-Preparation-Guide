// LeetCode Question URL: https://leetcode.com/problems/palindrome-partitioning/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Backtracking + Memoization
 *
 * <pre>
 * Time Complexity:
 * Start Index  --> Time Taken
 *          0   --> N * 2^N
 *          1   --> (N-1) * 2^(N-1)
 *          2   --> (N-2) * 2^(N-2)
 *          ...
 *          N-1 --> 1 * 2^1
 *
 * Total Time = N * (2^N + 2^(N-1) + ... + 2^1) - (0*2^N + 1*2^(N-1) + ... + (N-1)*2^1)
 *            ≈ O(N * 2^N)
 *
 * Space Complexity: O(N*2^N + N + N^2) --> Memo + Recursion Stack + seenPalindromes
 *                   ≈ O(N * 2^N)
 * </pre>
 */
class Solution {
    public List<List<String>> partition(String s) {
        if (s == null) {
            return new ArrayList<>();
        }

        int len = s.length();
        if (len <= 1) {
            return List.of(List.of(s));
        }

        List<List<String>>[] memo = new List[len + 1];
        memo[len] = List.of(Collections.emptyList());

        return partitionHelper(s, 0, memo, new boolean[len][len]);
    }

    private List<List<String>> partitionHelper(String s, int start, List<List<String>>[] memo,
            boolean[][] seenPalindromes) {
        if (memo[start] != null) {
            return memo[start];
        }

        List<List<String>> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));

            if ((s.charAt(start) == s.charAt(i)) && (i - start <= 2 || seenPalindromes[start + 1][i - 1])) {
                seenPalindromes[start][i] = true;
                List<List<String>> tempResult = partitionHelper(s, i + 1, memo, seenPalindromes);
                String curWord = sb.toString();
                for (List<String> temp : tempResult) {
                    List<String> cur = new ArrayList<>();
                    cur.add(curWord);
                    cur.addAll(temp);
                    result.add(cur);
                }
            }
        }

        memo[start] = result;
        return result;
    }
}
