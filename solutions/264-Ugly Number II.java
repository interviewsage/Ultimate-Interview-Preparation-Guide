// LeetCode Question URL: https://leetcode.com/problems/ugly-number-ii/
// LeetCode Discuss URL:

/**
 * Alternate Solution: (Not sure if its better)
 * https://leetcode.com/problems/ugly-number-ii/discuss/483848/sub-linear-solution-in-constant-space
 */

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/ugly-number-ii/discuss/69364/My-16ms-C++-DP-solution-with-short-explanation
 * 2. https://leetcode.com/problems/ugly-number-ii/discuss/69364/My-16ms-C++-DP-solution-with-short-explanation/224033
 * 3. https://leetcode.com/problems/ugly-number-ii/discuss/69364/My-16ms-C++-DP-solution-with-short-explanation/470711
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 * </pre>
 */
class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n <= 6) {
            return n;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;

        for (int i = 1; i < n; i++) {
            int val2 = 2 * dp[idx2];
            int val3 = 3 * dp[idx3];
            int val5 = 5 * dp[idx5];

            dp[i] = Math.min(val2, Math.min(val3, val5));

            if (dp[i] == val2) {
                idx2++;
            }
            if (dp[i] == val3) {
                idx3++;
            }
            if (dp[i] == val5) {
                idx5++;
            }
        }

        return dp[n - 1];
    }
}
