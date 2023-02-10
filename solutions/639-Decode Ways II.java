// LeetCode Question URL: https://leetcode.com/problems/decode-ways-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/decode-ways-ii/discuss/1496550/Java-or-TC:-O(N)-or-SC:-O(1)-or-Constant-Space-Dynamic-Programming-solution

/**
 * Dynamic Programming
 *
 * DP[i] = Number of ways to decode the string from 0 to i.
 *
 * DP[i] = DP[i-1] (when i is from 1 to 9) + DP[i-2] (when (i-1,i) is from 10 to
 * 26)
 *
 * Based on star calculate the number of decodings possible.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string.
 */
class Solution1 {
    private static final int MOD = 1000000007;

    public int numDecodings(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }

        char preChar = s.charAt(0);
        long pre = 1; // dp[i-2]
        long cur = preChar == '*' ? 9 : 1; // dp[i-1]

        for (int i = 1; i < len; i++) {
            char curChar = s.charAt(i);
            if (preChar == '0' && curChar == '0') {
                return 0;
            }

            long ways = 0; // dp[i]

            if (curChar != '0') {
                ways = cur * (curChar == '*' ? 9 : 1);
            }

            if (preChar == '*') {
                if (curChar == '*') {
                    ways += 15 * pre;
                } else if (curChar <= '6') {
                    ways += 2 * pre;
                } else {
                    ways += pre;
                }
            } else if (preChar == '2') {
                if (curChar == '*') {
                    ways += 6 * pre;
                } else if (curChar <= '6') {
                    ways += pre;
                }
            } else if (preChar == '1') {
                ways += pre * (curChar == '*' ? 9 : 1);
            }

            pre = cur;
            cur = ways % MOD;
            preChar = curChar;
        }

        return (int) cur;
    }
}

class Solution2 {
    private static final int MOD = 1000000007;

    public int numDecodings(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }

        char preChar = s.charAt(0);
        long pre = 1; // dp[i-2]
        long cur = preChar == '*' ? 9 : 1; // dp[i-1]

        for (int i = 1; i < len; i++) {
            char curChar = s.charAt(i);
            if (preChar == '0' && curChar == '0') {
                return 0;
            }

            long ways = 0; // dp[i]

            if (curChar == '*') {
                ways = 9 * cur;

                if (preChar == '*') {
                    ways += 15 * pre;
                } else if (preChar == '2') {
                    ways += 6 * pre;
                } else if (preChar == '1') {
                    ways += 9 * pre;
                }
            } else {
                if (curChar != '0') {
                    ways = cur;
                }

                if (preChar == '*') {
                    ways += curChar <= '6' ? 2 * pre : pre;
                } else if (preChar == '1' || (preChar == '2' && curChar <= '6')) {
                    ways += pre;
                }
            }

            pre = cur;
            cur = ways % MOD;
            preChar = curChar;
        }

        return (int) cur;
    }
}
