// LeetCode Question URL: https://leetcode.com/problems/range-sum-query-2d-immutable/
// LeetCode Discuss URL: https://leetcode.com/problems/range-sum-query-2d-immutable/discuss/1495052/Java-or-TC:-init-greater-O(R*C)-sumRange-greater-O(1)-or-Using-Prefix-Sum

/**
 * Using a prefix sum array
 *
 * <pre>
 * prefixSum(ABCD) = prefixSum(OD) − prefixSum(OB) − prefixSum(OC) + prefixSum(OA)
 * </pre>
 *
 * Time Complexity: NumArray() -> O(R*C). sumRange() -> O(1)
 *
 * Space Complexity: O(R*C)
 *
 * R = Number of rows. C = Number of columns.
 */
class NumMatrix {

    int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }

        int m = matrix.length;
        int n = matrix[0].length;
        prefixSum = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1]
                        + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2 + 1][col2 + 1] - prefixSum[row2 + 1][col1] - prefixSum[row1][col2 + 1]
                + prefixSum[row1][col1];
    }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix obj = new NumMatrix(matrix);
// int param_1 = obj.sumRegion(row1,col1,row2,col2);