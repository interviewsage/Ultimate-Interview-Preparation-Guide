// LeetCode Question URL: https://leetcode.com/problems/decode-ways/
// LeetCode Discuss URL: https://leetcode.com/problems/decode-ways/discuss/1496546/Java-or-TC:-O(N)-or-SC:-O(1)-or-Constant-Space-Dynamic-Programming-solution

/**
 * Dynamic Programming
 *
 * DP[i] = Number of ways to decode the string from 0 to i.
 *
 * DP[i] = DP[i-1] (when i is from 1 to 9) + DP[i-2] (when (i-1,i) is from 10 to
 * 26)
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

        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int pre = 1; // dp[i-2]
        int cur = 1; // dp[i-1]
        char preChar = s.charAt(0);
        for (int i = 1; i < len; i++) {
            int ways = 0; // dp[i]
            char curChar = s.charAt(i);
            if (curChar != '0') {
                ways = cur;
            }
            if (preChar == '1' || (preChar == '2' && curChar <= '6')) {
                ways += pre;
            }

            pre = cur;
            cur = ways;
            preChar = curChar;
        }

        return cur;
    }
}

class Solution2 {
    public int numDecodings(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }
        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int pre = 1; // dp[i-2]
        int cur = 1; // dp[i-1]

        for (int i = 1; i < s.length(); i++) {
            int sum = 0; // dp[i]
            if (s.charAt(i) != '0') {
                sum = cur;
            }
            int num = Integer.parseInt(s.substring(i - 1, i + 1));
            if (num >= 10 && num <= 26) {
                sum += pre;
            }

            pre = cur;
            cur = sum;
        }

        return cur;
    }
}
