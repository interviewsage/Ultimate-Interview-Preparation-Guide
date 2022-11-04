// LeetCode Question URL: https://leetcode.com/problems/climbing-stairs/
// LeetCode Discuss URL: https://leetcode.com/problems/climbing-stairs/discuss/1515325/Java-or-TC:-O(logN)-or-SC:-O(1)-or-Matrix-Multiplication-and-Space-Optimized-DP-solutions

/**
 * Dynamic Programming
 *
 * This is space optimized. Same solution can be done using one 1D array.
 *
 * DP[i] = DP[i-1] + DP[i-2]
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Input number n.
 */
class Solution1 {
    public int climbStairs(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input n is invalid");
        }
        if (n <= 2) {
            return n;
        }

        int pre = 1; // n == 1
        int cur = 2; // n == 2
        for (int i = 3; i <= n; i++) {
            int temp = pre + cur;
            pre = cur;
            cur = temp;
        }

        return cur;
    }
}

/**
 * Binets Method (Using Matrix Multiplication to find the Fibonacci Number)
 *
 * Refer: Approach 5: Binets Method
 * (https://leetcode.com/problems/climbing-stairs/solution/)
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1) -> Uses constant complexity
 *
 * N = Input number n
 */
class Solution2 {
    public int climbStairs(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n in invalid");
        }
        if (n <= 1) {
            return n;
        }

        int[][] q = { { 1, 1 }, { 1, 0 } };
        int[][] result = q;
        n--; // As we have already solved for n = 1. q[0][0] points to 2nd Fibonacci Number.
        while (n > 0) {
            if (n % 2 == 1) {
                result = multiplyMatrix(result, q);
                if (n == 1) {
                    break;
                }
                n--;
            }

            q = multiplyMatrix(q, q);
            n /= 2;
        }
        return result[0][0];
    }

    private int[][] multiplyMatrix(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}
