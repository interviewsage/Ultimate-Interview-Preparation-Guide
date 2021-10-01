// LeetCode URL: https://leetcode.com/problems/decode-ways-ii/
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
class Solution {
    public int numDecodings(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }
        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        long pre = 1; // dp[i-2]
        long cur = s.charAt(0) == '*' ? 9 : 1; // dp[i-1]

        for (int i = 1; i < s.length(); i++) {
            long sum = 0; // dp[i]
            char curChar = s.charAt(i);
            char preChar = s.charAt(i - 1);

            if (curChar != '0') {
                sum = cur * (curChar == '*' ? 9 : 1);
            }
            if (preChar == '*') {
                if (curChar == '*') {
                    sum += pre * 15;
                } else if (curChar <= '6') {
                    sum += pre * 2;
                } else {
                    sum += pre;
                }
            } else {
                if (curChar == '*') {
                    if (preChar == '1') {
                        sum += pre * 9;
                    } else if (preChar == '2') {
                        sum += pre * 6;
                    }
                } else {
                    int num = Integer.parseInt(s.substring(i - 1, i + 1));
                    if (num >= 10 && num <= 26) {
                        sum += pre;
                    }
                }
            }

            pre = cur;
            cur = sum % 1000000007;
        }

        return (int) cur;
    }
}
