// LeetCode Question URL: https://leetcode.com/problems/lexicographical-numbers/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Iterative
 *
 * We are trying to find N numbers. Each number can take upto log10 N time to
 * create.
 *
 * Time Complexity: O(N * log10 N) = O(N) as log10 N is a very small number can
 * be maximum 10 in case of Integers.
 *
 * Space Complexity: O(1)
 *
 * N = input number n.
 */
class Solution1 {
    public List<Integer> lexicalOrder(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Input n is invalid");
        }

        List<Integer> result = new ArrayList<>();
        int cur = 1;

        for (int i = 1; i <= n; i++) {
            result.add(cur);

            if (cur * 10 <= n) {
                // Multiply current by ten to get next number.
                cur *= 10;
            } else if (cur % 10 != 9 && cur + 1 <= n) {
                // Now go to next number provided the last digit is not 9.
                // Here we have to check cur + 1 <= n so that we do not go beyond n.
                cur++;
            } else {
                // Backtrack all digit 9s to get the next number.
                // Here no need to cur + 1 <= n as the cur will get smaller.
                cur /= 10;
                while (cur % 10 == 9) {
                    cur /= 10;
                }
                cur++;
            }
        }

        return result;
    }
}

/**
 * Recursive DFS
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(log10 N)
 *
 * N = input number n.
 */
class Solution2 {
    public List<Integer> lexicalOrder(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Input n is invalid");
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (i > n) {
                break;
            }
            dfsHelper(result, i, n);
        }

        return result;
    }

    private void dfsHelper(List<Integer> result, int cur, int n) {
        result.add(cur);

        cur *= 10;
        for (int i = 0; i <= 9; i++) {
            if (cur + i > n) {
                return;
            }
            dfsHelper(result, cur + i, n);
        }
    }
}
