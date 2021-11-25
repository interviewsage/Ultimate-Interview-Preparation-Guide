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
 * Time Complexity: O(N^3 + W) Assuming substring takes O(1) time.
 * - There are two nested loops, and substring computation at each iteration.
 * </pre>
 *
 * Space Complexity: O(N + WD)
 *
 * N = Length of the input string. WD = Number of chars in wordDict. W = Number
 * of words in wordDict
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        HashSet<String> wordSet = new HashSet<>(wordDict);
        if (wordSet.contains(s)) {
            return true;
        }

        int len = s.length();
        if (len == 0) {
            return false;
        }

        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}
