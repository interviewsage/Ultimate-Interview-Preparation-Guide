// LeetCode Question URL: https://leetcode.com/problems/count-square-sum-triples/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Constant Space Solution
 *
 * Time Complexity: O(3 + 4 + 5 + ... + N-2) = O((N-2)*(N-1)/2 - 3) = O(N^2)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int countTriples(int n) {
        int result = 0;
        for (int i = 5; i <= n; i++) {
            int left = 1;
            int right = i - 1;
            int iSquare = i * i;

            while (left < right) {
                int sum = left * left + right * right;

                if (sum == iSquare) {
                    result += 2;
                    left++;
                    right--;
                } else if (sum < iSquare) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}

/**
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N)
 */
class Solution2 {
    public int countTriples(int n) {
        if (n <= 4) {
            return 0;
        }

        Set<Integer> squares = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            squares.add(i * i);
        }

        int result = 0;
        int nSquare = n * n;

        for (int i = 1; i <= n - 2; i++) {
            int iSquare = i * i;
            for (int j = i + 1; iSquare + j * j <= nSquare; j++) {
                if (squares.contains(iSquare + j * j)) {
                    result += 2;
                }
            }
        }

        return result;
    }
}
