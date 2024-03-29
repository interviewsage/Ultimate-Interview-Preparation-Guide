// LeetCode Question URL: https://leetcode.com/problems/pascals-triangle/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming
 *
 * Refer: (Check recursive solution here)
 * https://leetcode.com/problems/pascals-triangle/discuss/1287175/Short-and-Simple-Solution-w-Explanation-or-Beats-100
 *
 * DP[i][j] = DP[i-1][j-1] + DP[i-1][j]
 *
 * Time Complexity: O(N * (N+1) / 2) = O(N^2)
 *
 * Space Complexity: O(1) -> Excluding the result space.
 *
 * N = Input number of rows.
 */
class Solution1 {
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 0) {
            throw new IllegalArgumentException("numRows is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }
        result.add(Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {
            result.add(getNextRow(result.get(i - 1)));
        }
        return result;
    }

    private List<Integer> getNextRow(List<Integer> row) {
        List<Integer> nextRow = new ArrayList<>();
        nextRow.add(1);
        for (int i = 1; i < row.size(); i++) {
            nextRow.add(row.get(i - 1) + row.get(i));
        }
        nextRow.add(1);
        return nextRow;
    }
}

/**
 * Dynamic Programming
 *
 * DP[i][j] = DP[i-1][j-1] + DP[i-1][j]
 *
 * Time Complexity: O(N ^ 2)
 *
 * Space Complexity: O(N) -> Excluding the result space. O(N^2) -> Including the
 * result space.
 *
 * N = Input number of rows.
 */
class Solution2 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }

        List<Integer> row = new ArrayList<>();

        while (result.size() < numRows) {
            /**
             * Iterating from end to start helps to minizie the use of temp variable to
             * store DP[i-1][j] as this gets over written and is required in calculation of
             * next value.
             */
            for (int i = row.size() - 1; i >= 1; i--) {
                row.set(i, row.get(i - 1) + row.get(i));
            }

            // Increasing the size of row by adding last '1'
            row.add(1);
            result.add(new ArrayList<>(row));
        }

        return result;
    }
}