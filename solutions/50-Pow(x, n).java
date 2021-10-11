// LeetCode Question URL: https://leetcode.com/problems/powx-n/
// LeetCode Discuss URL: https://leetcode.com/problems/powx-n/discuss/1515322/Java-or-TC:-O(logN)-or-Optimized-Recursive-and-Iterative-(handles-overflow)-solutions

/**
 * Reduce N by half in every recursion
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(log N) - Space required by recursion stack.
 *
 * N = Input number n.
 */
class Solution1 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }

        if (n < 0) {
            /**
             * -(n + 1) is done to avoid int overflow.
             */
            return (1 / x) * myPow(1 / x, -(n + 1));
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}

/**
 * Reduce N by half in every recursion
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(log N) - Space required by recursion stack.
 *
 * N = Input number n.
 */
class Solution2 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }

        double num = myPow(x, n / 2);

        if (n % 2 == 0) {
            return num * num;
        } else {
            if (n < 0) {
                return num * num / x;
            } else {
                return num * num * x;
            }
        }
    }
}

/**
 * Reduce N by half in every iteration.
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Input number n.
 */
class Solution3 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }

        double result = 1;
        if (n < 0) {
            x = 1 / x;
            /**
             * -(n + 1) is done to avoid int overflow.
             */
            n = -(n + 1);
            result *= x;
        }
        while (n > 0) {
            if (n % 2 == 1) {
                result *= x;
                n--;
            }
            x *= x;
            n /= 2;
        }
        return result;
    }
}
