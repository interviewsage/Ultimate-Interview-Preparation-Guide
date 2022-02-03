// LeetCode Question URL: https://leetcode.com/problems/largest-plus-sign/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming
 *
 * DP[i][j] = Largest Plus Sign that can be created at i,j.
 *
 * Time Complexity: O(M + 3*N^2)
 *
 * Space Complexity: O(N^2)
 *
 * N = Input grid size. M = Number of mines.
 */
class Solution1 {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid grid size");
        }
        if (n == 0 || mines.length == n * n) {
            return 0;
        }

        int[][] dp = new int[n][n];

        if (mines != null) {
            for (int[] m : mines) {
                dp[m[0]][m[1]] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            int countL = 0;
            int countR = 0;
            for (int j = 0; j < n; j++) {
                // if (dp[i][j] == -1) {
                // countL = 0;
                // } else {
                // countL++;
                // dp[i][j] = dp[i][j] == 0 ? countL : Math.min(countL, dp[i][j]);
                // }
                countL = dp[i][j] == -1 ? 0 : countL + 1;
                dp[i][j] = dp[i][j] == 0 ? countL : Math.min(countL, dp[i][j]);

                int k = n - 1 - j;
                // if (dp[i][k] == -1) {
                // countR = 0;
                // } else {
                // countR++;
                // dp[i][k] = dp[i][k] == 0 ? countR : Math.min(countR, dp[i][k]);
                // }
                countR = dp[i][k] == -1 ? 0 : countR + 1;
                dp[i][k] = dp[i][k] == 0 ? countR : Math.min(countR, dp[i][k]);
            }
        }

        int result = 0;

        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                // if (dp[i][j] == -1) {
                // count = 0;
                // } else {
                // count++;
                // dp[i][j] = Math.min(count, dp[i][j]);
                // }
                count = dp[i][j] == -1 ? 0 : count + 1;
                dp[i][j] = Math.min(count, dp[i][j]);
            }

            count = 0;
            for (int i = n - 1; i >= 0; i--) {
                // if (dp[i][j] == -1) {
                // count = 0;
                // } else {
                // count++;
                // dp[i][j] = Math.min(count, dp[i][j]);
                // result = Math.max(result, dp[i][j]);
                // }
                count = dp[i][j] == -1 ? 0 : count + 1;
                dp[i][j] = Math.min(count, dp[i][j]);
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }
}

/**
 * Dynamic Programming
 *
 * DP[i][j] = Largest Plus Sign that can be created at i,j.
 *
 * Time Complexity: O(N*N + M + 4*N*N) = O(5*N*N + M)
 *
 * Space Complexity: O(N*N)
 *
 * N = Input grid size. M = Number of mines.
 */
class Solution2 {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N < 1) {
            return 0;
        }
        if (mines == null || mines.length == 0) {
            return (N + 1) / 2;
        }

        int[][] dp = new int[N][N];
        int result = 0;

        for (int[] r : dp) {
            Arrays.fill(r, N);
        }
        for (int[] m : mines) {
            dp[m[0]][m[1]] = 0;
        }

        for (int r = 0; r < N; r++) {
            int count = 0;
            for (int c = 0; c < N; c++) {
                count = dp[r][c] == 0 ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int c = N - 1; c >= 0; c--) {
                count = dp[r][c] == 0 ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }

        for (int c = 0; c < N; c++) {
            int count = 0;
            for (int r = 0; r < N; r++) {
                count = dp[r][c] == 0 ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int r = N - 1; r >= 0; r--) {
                count = dp[r][c] == 0 ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);

                result = Math.max(result, dp[r][c]);
            }
        }

        return result;
    }
}

/**
 * Dynamic Programming
 *
 * DP[i][j] = Largest Plus Sign that can be created at i,j.
 *
 * Time Complexity: O(M + 4*N*N) = O(4*N*N + M)
 *
 * Space Complexity: O(N*N + M)
 *
 * N = Input grid size. M = Number of mines.
 */
class Solution3 {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N < 1) {
            return 0;
        }
        if (mines == null || mines.length == 0) {
            return (N + 1) / 2;
        }

        int[][] dp = new int[N][N];
        int result = 0;

        HashSet<Integer> set = new HashSet<>();
        for (int[] m : mines) {
            set.add(m[0] * N + m[1]);
        }

        for (int r = 0; r < N; r++) {
            int count = 0;
            for (int c = 0; c < N; c++) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = count;
            }

            count = 0;
            for (int c = N - 1; c >= 0; c--) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }

        for (int c = 0; c < N; c++) {
            int count = 0;
            for (int r = 0; r < N; r++) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int r = N - 1; r >= 0; r--) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);

                result = Math.max(result, dp[r][c]);
            }
        }

        return result;
    }
}
