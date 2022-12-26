// LeetCode Question URL: https://leetcode.com/problems/word-break/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming
 *
 * <pre>
 * Refer:
 * 1. Solution: https://leetcode.com/problems/word-break/discuss/43814/C++-Dynamic-Programming-simple-and-fast-solution-(4ms)-with-optimization
 * 2. Time Complexity Discussion: https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways/173773
 * 3. BFS Solution: https://leetcode.com/problems/word-break/solution/
 * 4. Most Optimized Solution: https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways/1034994
 * </pre>
 *
 * DP[i] -> Can a string of length i be broken into a valid words.
 *
 * DP[i] = DP[j] && wordDict.contains(substring(j,i)) where 0 <= j < i
 *
 * DP[0] = true;
 *
 * We use a boolean vector dp[]. dp[i] is set to true if a valid word (word
 * sequence) ends there. The optimization is to look back from current position
 * i and if preceding position j with dp[j] == true is found, then check
 * s.substring(j, i) in wordDict.
 *
 * <pre>
 * Time Complexity:
 * 1. Compare & Add to HashSet --> O(W * N)
 * 2. Nested For Loops + substring + contains --> (N * maxWordLen * (2 * maxWordLen)) = (N * maxWordLen^2)
 * Total time complexity: O(W * N + N * maxWordLen^2)
 *
 * Space Complexity: O(N + W + maxWordLen) --> (DP Array + HashSet will contains references + substring)
 * </pre>
 *
 * N = Length of the input string. W = Number of words in wordDict
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        Set<String> wordSet = new HashSet<>();
        int maxWordLen = 0;
        int minWordLen = Integer.MAX_VALUE;
        for (String w : wordDict) {
            if (s.equals(w)) {
                return true;
            }
            wordSet.add(w);
            maxWordLen = Math.max(maxWordLen, w.length());
            minWordLen = Math.min(minWordLen, w.length());
        }

        int len = s.length();
        // This is done later as wordDict can contain an empty string which would have
        // matched in the previous step.
        if (len == 0 || len < minWordLen) {
            return false;
        }

        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            int end = Math.max(0, i - maxWordLen);
            for (int j = i - 1; j >= end; j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}
