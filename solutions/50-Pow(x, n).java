// LeetCode Question URL: https://leetcode.com/problems/powx-n/
// LeetCode Discuss URL: https://leetcode.com/problems/powx-n/discuss/1515322/Java-or-TC:-O(logN)-or-Optimized-Recursive-and-Iterative-(handles-overflow)-solutions

/**
 * Reduce N by half in every recursion
 *
 * Time Complexity: O(log N) ==> T(N) = T(N/2) + O(1)
 *
 * <pre>
 * T(N) = T(N / 2^0) = T(N/2) + O(1)
 * T(N/2) = T(N / 2^1) = T(N/4) + O(1)
 * T(N/4) = T(N / 2^2) = T(N/8) + O(1)
 * ...
 * ...
 * T(2) = T(N / 2^(logN - 1)) = T(1) + O(1)
 * T(1) = T(N / 2^logN) = O(1)
 *
 * T(N) = O(1) * (logN + 1) = O(log N)
 * </pre>
 *
 * Space Complexity: O(log N) - Space required by recursion stack.
 *
 * N = Input number n.
 */
class Solution1 {
    public double myPow(double x, int n) {
        if (x == 0 && n < 0) {
            throw new IllegalArgumentException("Undefined Solution");
        }
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        }
        if (n == -1) {
            return 1 / x;
        }

        if (n < 0) {
            /**
             * -(n + 1) is done to avoid int overflow.
             */
            return (1 / x) * myPow(1 / x, -(n + 1));
        }

        return (n % 2 == 0 ? 1 : x) * myPow(x * x, n / 2);
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
class Solution2 {
    public double myPow(double x, int n) {
        if (x == 0 && n < 0) {
            throw new IllegalArgumentException("Undefined Solution");
        }
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        }

        double result = 1;
        if (n < 0) {
            x = 1 / x;
            /**
             * -(n + 1) is done to avoid int overflow.
             */
            n = -(n + 1);
            result = x;
        }

        while (n > 0) {
            if (n % 2 != 0) {
                result *= x;
            }
            x *= x;
            n = n / 2;
        }

        return result;
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
        if (x == 0 && n < 0) {
            throw new IllegalArgumentException("Undefined Solution");
        }
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        }
        if (n == -1) {
            return 1 / x;
        }

        double result = 1;
        if (n < 0) {
            x = 1 / x;
        }

        while (n != 0) {
            if (n % 2 != 0) {
                result *= x;
                if (n < 0) {
                    n++;
                }
            }
            x *= x;
            n = n / 2;
        }

        return result;
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
class Solution4 {
    public double myPow(double x, int n) {
        if (x == 0 && n < 0) {
            throw new IllegalArgumentException("Undefined Solution");
        }
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        }
        if (n == -1) {
            return 1 / x;
        }

        double tempVal = myPow(x, n / 2);
        if (n % 2 == 0) {
            return tempVal * tempVal;
        } else {
            if (n > 0) {
                return tempVal * tempVal * x;
            } else {
                return tempVal * tempVal / x;
            }
        }
    }
}
