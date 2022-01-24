// LeetCode Question URL: https://leetcode.com/problems/maximum-length-of-pair-chain/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/maximum-length-of-pair-chain/solution/
 * </pre>
 *
 * Time Complexity: O(N*logN + N^2)
 *
 * Space Complexity: O(Space required by sorting algo + N for DP array)
 *
 * N = Number of pairs
 */
class Solution1 {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null) {
            return 0;
        }

        int len = pairs.length;
        if (len <= 1) {
            return len;
        }

        // We can sort either on left or right. Both will give correct answer.
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[len];
        dp[0] = 1;
        int maxLen = 1;

        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}

/**
 * Greedy Solution
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/maximum-length-of-pair-chain/solution/
 * 2. https://leetcode.com/problems/maximum-length-of-pair-chain/discuss/225801/Proof-of-the-greedy-solution
 * </pre>
 *
 * Time Complexity: O(N*logN + N)
 *
 * Space Complexity: O(Space required by sorting algo)
 *
 * N = Number of pairs
 */
class Solution2 {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null) {
            return 0;
        }

        int len = pairs.length;
        if (len <= 1) {
            return len;
        }

        // Can only sort on right (end)
        Arrays.sort(pairs, (a, b) -> (a[1] - b[1]));
        int curEnd = pairs[0][1];
        int maxLen = 1;

        for (int i = 1; i < len; i++) {
            if (curEnd < pairs[i][0]) {
                curEnd = pairs[i][1];
                maxLen++;
            }
        }

        return maxLen;
    }
}
