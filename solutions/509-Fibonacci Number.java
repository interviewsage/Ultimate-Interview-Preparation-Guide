// LeetCode Question URL: https://leetcode.com/problems/fibonacci-number/
// LeetCode Discuss URL: https://leetcode.com/problems/fibonacci-number/discuss/1515434/Java-or-TC:-O(logN)-or-SC:-O(1)-or-Matrix-Multiplication-and-Space-Optimized-DP-solutions

import java.util.*;

/**
 * Space Optimized Dynamic Programming Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n <= 1) {
            return n;
        }
        int pre = 1; // n == 1
        int cur = 1; // n == 2
        for (int i = 3; i <= n; i++) {
            int next = pre + cur;
            pre = cur;
            cur = next;
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
    public int fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n <= 1) {
            return n;
        }

        int[][] q = { { 1, 1 }, { 1, 0 } };
        int[][] result = q;
        n -= 2; // As we have already solved for n = 2. q[0][0] points to 2nd Fibonacci Number.
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

/**
 * Recursive Solution with Memoization
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 */
class Solution3 {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }

        return fibHelper(N, new HashMap<>());
    }

    private int fibHelper(int N, HashMap<Integer, Integer> map) {
        if (N <= 1) {
            return N;
        }
        if (map.containsKey(N)) {
            return map.get(N);
        }
        int sum = fibHelper(N - 1, map) + fibHelper(N - 2, map);
        map.put(N, sum);

        return sum;
    }
}

/**
 * Recursive Solution.
 *
 * Time Complexity: T(N) = T(N-1) + T(N-2) + O(1) => O(2 ^ N). This can be
 * viszualized in a tree. Every node has 2 childs. The tree will have maximum N
 * levels. Thus maximum number of nodes = 2 ^ N
 *
 * Space Complexity: O(N)
 */
class Solution4 {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }

        return fib(N - 1) + fib(N - 2);
    }
}
