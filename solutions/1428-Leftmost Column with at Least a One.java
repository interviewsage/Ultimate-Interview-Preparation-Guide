// LeetCode Question URL: https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Move left if found 1. Note the index here.
 * Move down if found 0.
 *
 * Time Complexity: O(R + C)
 * Space Complexity: O(1)
 *
 * R = Number of rows
 * C = Number of columns
 */
class Solution1 {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        if (binaryMatrix == null) {
            throw new IllegalArgumentException("Input is null");
        }

        List<Integer> matrixSize = binaryMatrix.dimensions();
        int rows = matrixSize.get(0);
        int cols = matrixSize.get(1);

        int result = -1;
        int i = 0;
        int j = cols - 1;

        while (i < rows && j >= 0) {
            if (binaryMatrix.get(i, j) == 1) {
                result = j;
                j--;
            } else {
                i++;
            }
        }

        return result;
    }
}

/**
 * Using Binary Search. This solution is better is number of columns are much
 * larger than number of columns.
 *
 * Another variation of this solution:
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/discuss/590828/Java-Binary-Search-and-Linear-Solutions-with-Picture-Explain-Clean-Code
 *
 * Move left if found 1. Note the index here.
 * Move down if found 0.
 *
 * Time Complexity: O(R * log(C)) in worst case
 *
 * Space Complexity: O(1)
 *
 * R = Number of rows
 * C = Number of columns
 */
class Solution2 {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        if (binaryMatrix == null) {
            throw new IllegalArgumentException("Input is null");
        }

        List<Integer> matrixSize = binaryMatrix.dimensions();
        int rows = matrixSize.get(0);
        int cols = matrixSize.get(1);

        int result = -1;
        int i = 0;
        int j = cols - 1;

        while (i < rows && j >= 0) {
            if (binaryMatrix.get(i, j) == 1) {
                int idx = binarySearch(binaryMatrix, i, j);
                result = idx;
                j = idx - 1;
            }
            i++;
        }

        return result;
    }

    private int binarySearch(BinaryMatrix binaryMatrix, int row, int end) {
        int start = 0;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (binaryMatrix.get(row, mid) == 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}

// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
interface BinaryMatrix {
    public int get(int row, int col);

    public List<Integer> dimensions();
}
