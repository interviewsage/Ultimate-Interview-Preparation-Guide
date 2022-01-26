// LeetCode Question URL: https://leetcode.com/problems/pascals-triangle/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming
 *
 * Time Complexity: O(N*(N+1)/2) = O(N^2)
 *
 * Space Complexity: O(1) -> Excluding the result space.
 *
 * N = Input index.
 */
class Solution1 {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            throw new IllegalArgumentException("rowIndex is invalid");
        }

        List<Integer> result = new ArrayList<>();

        while (rowIndex-- >= 0) {
            for (int i = result.size() - 1; i > 0; i--) {
                result.set(i, result.get(i - 1) + result.get(i));
            }
            result.add(1);
        }

        return result;
    }
}

/**
 * Recursion Top Down
 *
 * Time Complexity: O(N*(N+1)/2) = O(N^2)
 *
 * Space Complexity: O(N) -> Recursion Stack.
 *
 * N = Input index.
 */
class Solution2 {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            throw new IllegalArgumentException("rowIndex is invalid");
        }
        if (rowIndex == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            return list;
        }

        List<Integer> row = getRow(rowIndex - 1);

        for (int i = row.size() - 1; i > 0; i--) {
            row.set(i, row.get(i - 1) + row.get(i));
        }
        row.add(1);

        return row;
    }
}
