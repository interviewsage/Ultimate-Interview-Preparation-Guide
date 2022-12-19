// LeetCode Question URL: https://leetcode.com/problems/regular-expression-matching/
// LeetCode Discuss URL:

/**
 * DP Using 1 1D array.
 *
 * Refer:
 * https://www.dropbox.com/s/x5unylmr9zyp0oo/LC%20-%2010%20-%20Regular%20Expression%20Matching.pdf?dl=0
 *
 * Time Complexity: O((S+1) * P) = O(S * P)
 *
 * Space Complexity: O(P+1) = O(P)
 *
 * S = Length of input string s. P = Length of pattern p.
 */
class Solution1 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int sLen = s.length();
        int pLen = p.length();
        if (pLen == 0) {
            return sLen == 0;
        }
        if (sLen == 0) {
            if (pLen % 2 != 0) {
                return false;
            }
            for (int i = 1; i < pLen; i += 2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        boolean[] dp = new boolean[pLen + 1];

        // Setting up dp for empty s string.
        dp[0] = true;
        for (int j = 2; j <= pLen; j++) {
            if (p.charAt(j - 1) == '*') {
                if (!dp[j - 2]) {
                    break;
                }
                dp[j] = true;
            }
        }

        for (int i = 1; i <= sLen; i++) {
            boolean pre = dp[0];
            dp[0] = false; // Empty pattern does not match a non empty string.
            char sChar = s.charAt(i - 1);

            for (int j = 1; j <= pLen; j++) {
                char pChar = p.charAt(j - 1);
                boolean cur = dp[j];

                if (pChar == '.' || pChar == sChar) {
                    dp[j] = pre;
                } else if (pChar == '*') {
                    char prevPChar = p.charAt(j - 2);
                    dp[j] = dp[j - 2]; // Excluding prevPChar & '*' in pattern
                    if (prevPChar == '.' || prevPChar == sChar) {
                        dp[j] |= cur; // prevPChar & '*' might be included. So use both dp[i-1][j] & dp[i][j-2]
                    }
                } else {
                    dp[j] = false;
                }

                pre = cur;
            }
        }

        return dp[pLen];
    }
}

/**
 * DP Using 2 1D arrays.
 *
 * Refer:
 * https://www.dropbox.com/s/x5unylmr9zyp0oo/LC%20-%2010%20-%20Regular%20Expression%20Matching.pdf?dl=0
 *
 * Time Complexity: O(S * P)
 *
 * Space Complexity: O(P)
 *
 * S = Length of input string s. P = Length of pattern p.
 */
class Solution2 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();

        if (sLen == 0 && pLen == 0) {
            return true;
        }

        if (pLen == 0 || p.charAt(0) == '*') {
            return false;
        }

        boolean[] dp1 = new boolean[pLen + 1];

        dp1[0] = true;
        // Here setting dp1 for empty s string.
        for (int j = 2; j <= pLen; j++) {
            if (p.charAt(j - 1) == '*') {
                dp1[j] = dp1[j - 2];
            }
        }

        for (int i = 1; i <= sLen; i++) {
            boolean[] dp2 = new boolean[pLen + 1];
            char sChar = s.charAt(i - 1);

            for (int j = 1; j <= pLen; j++) {
                char pChar = p.charAt(j - 1);

                if (pChar != '*') {
                    if (sChar == pChar || pChar == '.') {
                        dp2[j] = dp1[j - 1];
                    }
                } else {
                    dp2[j] = dp2[j - 2];
                    char pChar2 = p.charAt(j - 2);
                    if (pChar2 == sChar || pChar2 == '.') {
                        dp2[j] |= dp1[j];
                    }
                }
            }

            dp1 = dp2;
        }

        return dp1[pLen];
    }
}

/**
 * DP Using 1 2D array.
 *
 * Refer:
 * https://www.dropbox.com/s/x5unylmr9zyp0oo/LC%20-%2010%20-%20Regular%20Expression%20Matching.pdf?dl=0
 *
 * Time Complexity: O(S * P)
 *
 * Space Complexity: O(S * P)
 *
 * S = Length of input string s. P = Length of pattern p.
 */
class Solution3 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();

        if (sLen == 0 && pLen == 0) {
            return true;
        }

        if (pLen == 0 || p.charAt(0) == '*') {
            return false;
        }

        boolean[][] dp = new boolean[sLen + 1][pLen + 1];

        dp[0][0] = true;
        for (int j = 2; j <= pLen; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= sLen; i++) {
            char sChar = s.charAt(i - 1);

            for (int j = 1; j <= pLen; j++) {
                char pChar = p.charAt(j - 1);

                if (pChar != '*') {
                    if (sChar == pChar || pChar == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 2];
                    char pChar2 = p.charAt(j - 2);
                    if (pChar2 == sChar || pChar2 == '.') {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[sLen][pLen];
    }
}
