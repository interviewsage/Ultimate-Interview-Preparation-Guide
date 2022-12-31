// LeetCode Question URL: https://leetcode.com/problems/unique-paths/
// LeetCode Discuss URL: https://leetcode.com/problems/unique-paths/discuss/1513886/Java-or-TC:-O(M*N)-or-SC:-O(min(MN))-or-Space-optimized-Dynamic-Programming-solution

import java.util.*;

/**
 * Space optimized Dynamic Programming solution
 *
 * Refer: https://leetcode.com/problems/unique-paths/discuss/22954/C++-DP
 *
 * <pre>
 * dp[i][j] = Number of ways to reach this cell [i][j].
 * dp[i][j] = dp[i][j-1] + dp[i-1][j].
 * </pre>
 *
 * This can be solved by using one array representing the row.
 *
 * Let M = number of rows and N = number of columns. Thus the row size will be
 * N. Try to minimize N by swapping M and N if M < N;
 *
 * Time Complexity: O(M * N).
 *
 * Space Complexity = O(min(M, N)). Only one row is used for dp.
 */
class Solution1 {
    public int uniquePaths(int m, int n) {
        if (m < 0 || n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }

        if (m < n) {
            return uniquePaths(n, m);
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}

/**
 * Find the total permutations of the directions to be traveled. For example
 * D,D,D,D,R,R,R,R -> Find permutations of this list
 *
 * This is a combinatorial problem and can be solved without DP. For mxn grid,
 * robot has to move exactly m-1 steps down and n-1 steps right and these can be
 * done in any order.
 *
 * For the eg., given in question, 3x7 matrix, robot needs to take 2+6 = 8 steps
 * with 2 down and 6 right in any order. That is nothing but a permutation
 * problem. Denote down as 'D' and right as 'R', following is one of the path :-
 *
 * D R R R D R R R
 *
 * We have to tell the total number of permutations of the above given word. So,
 * decrease both m & n by 1 and apply following formula:-
 *
 * Total permutations = (m+n)! / (m! * n!)
 *
 * <pre>
 * Proof of the formula:
 * 1) https://math.stackexchange.com/questions/119044/what-is-the-proof-of-permutations-of-similar-objects
 * 2) https://testbook.com/learn/maths-permutation-with-repetition/#:~:text=The%20Permutation%20with%20Repetition%20is,(r%20times
 *
 * Refer:
 * 1) https://leetcode.com/problems/unique-paths/discuss/22958/Math-solution-O(1)-space
 * 2) https://leetcode.com/problems/unique-paths/discuss/22958/Math-solution-O(1)-space/22394
 * 3) Approach 2 (Math) - https://leetcode.com/problems/unique-paths/solution/
 * </pre>
 *
 * Time Complexity: O(min(M,N)). Assuming Multiplication and Division takes
 * O(1).
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int uniquePaths(int m, int n) {
        if (m < 0 || n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }

        if (m < n) {
            return uniquePaths(n, m);
        }

        m--;
        n--;

        long paths = 1;
        // Direction of for loop and order of statements have to be this to get the
        // correct answer. We cannot change the order and direction.
        // As we are diving from 1 to n.. we are making sure that the numerator has the
        // numbers that can be divided by the denominator.
        for (int i = 1; i <= n; i++) {
            paths *= (m + i);
            paths /= i;
        }

        return (int) paths;
    }
}
