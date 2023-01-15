// LeetCode URL: https://leetcode.com/problems/longest-palindromic-subsequence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming (Using 2D array)
 *
 * Refer: 1)
 * https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
 * 2)
 * https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution/103147
 *
 * DP[i][j] = Length of the longest palindromic subsequence in substring(i, j),
 * here i, j represent left, right indexes in the string.
 *
 * If s.charAt(i) == s.charAt(j) ==> DP[i][j] = DP[i+1][j-1] + 2. If left and
 * right characters are equal, 2 plus the answer from removing both left and
 * right.
 *
 * otherwise, DP[i][j] = Math.max(DP[i+1][j], DP[i][j-1]). Maximum of i) the
 * length after removing the left edge char and ii) the length after removing
 * the right edge char
 *
 * DP[i][i] = 1. Single char in the substring is a palindrome.
 *
 * N = Length of the input string.
 */

/**
 * Dynamic Programming (Using 1D array)
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N). Since values in DP array are depended on 1 rows. Thus
 * we need one 1D array to store the DP information.
 *
 * N = Length of the input string.
 */
class Solution1 {
    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i] = 1;
            int pre = 0;
            char c = s.charAt(i);
            for (int j = i + 1; j < len; j++) {
                int cur = dp[j];
                if (c == s.charAt(j)) {
                    dp[j] = pre + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                pre = cur;
            }
        }

        return dp[len - 1];
    }
}

/**
 * Dynamic Programming (Using 1D array)
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N). Since values in DP array are depended on 2 rows. Thus
 * we need two 1D arrays to store the DP information.
 *
 * N = Length of the input string.
 */
class Solution2 {
    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }

        int strLen = s.length();
        int[] dp1 = new int[strLen];
        int[] dp2 = new int[strLen];
        for (int i = strLen - 1; i >= 0; i--) {
            dp1[i] = 1;
            for (int j = i + 1; j < strLen; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp1[j] = dp2[j - 1] + 2;
                } else {
                    dp1[j] = Math.max(dp2[j], dp1[j - 1]);
                }
            }
            dp2 = Arrays.copyOf(dp1, strLen);
        }

        return dp1[strLen - 1];
    }
}

/**
 * Dynamic Programming (Using 1D array)
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N^2).
 *
 * N = Length of the input string.
 */
class Solution3 {
    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }

        int strLen = s.length();
        int[][] dp = new int[strLen][strLen];
        for (int i = strLen - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < strLen; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][strLen - 1];
    }
}
