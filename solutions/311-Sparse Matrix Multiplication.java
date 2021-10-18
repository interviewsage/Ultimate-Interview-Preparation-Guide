// LeetCode Question URL: https://leetcode.com/problems/sparse-matrix-multiplication/
// LeetCode Discuss URL: https://leetcode.com/problems/sparse-matrix-multiplication/discuss/1527243/Java-or-Using-Optimal-Sparse-Matrix-Representation

import java.util.*;

/**
 * First convert the input matrix to sparse format (into HashMap of HashMaps).
 * Then calculate the product and the convert back the result to 2D matrix.
 *
 * <pre>
 * Time Complexity:
 * Convert A[][] to sparseA -> O(M*K).
 * Convert B[][] to sparseB -> O(K*N).
 * Product -> O(NZ_R_A * NZ_C_A * NZ_C_B) ==> bounded by O(M*K*N)
 * Convert sparseC to C[][] -> O(NZ_R_C * NZ_C_C) ==> bounded by O(M*N)
 *
 * Space Complexity:
 * If input/output is in 2D array -> O(MK + KN + MN).
 * If input/output is in sparse format -> O(1).
 *
 * NZ_R_A -> Non-Zero Rows in A
 * NZ_C_A -> Non-Zero Columns in A
 * NZ_C_B -> Non-Zero Columns in B
 * NZ_R_C -> Non-Zero Rows in C
 * NZ_C_C -> Non-Zero Columns in C
 * </pre>
 *
 * In Facebook Dot Product of Sparse Vector is asked.
 * https://www.cnblogs.com/EdwardLiu/p/6399867.html
 */
class Solution1 {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        if (mat1 == null || mat2 == null || mat1[0].length != mat2.length) {
            throw new IllegalArgumentException("Invalid Input");
        }

        HashMap<Integer, HashMap<Integer, Integer>> sparseA = buildSparseMatrix(mat1);
        HashMap<Integer, HashMap<Integer, Integer>> sparseB = buildSparseMatrix(mat2);
        HashMap<Integer, HashMap<Integer, Integer>> sparseC = multiplyTwoSparseMatrices(sparseA, sparseB);

        int[][] result = new int[mat1.length][mat2[0].length];
        for (int row : sparseC.keySet()) {
            for (int col : sparseC.get(row).keySet()) {
                result[row][col] = sparseC.get(row).get(col);
            }
        }

        return result;
    }

    private HashMap<Integer, HashMap<Integer, Integer>> buildSparseMatrix(int[][] matrix) {
        HashMap<Integer, HashMap<Integer, Integer>> sparseMatrix = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    sparseMatrix.putIfAbsent(i, new HashMap<>());
                    sparseMatrix.get(i).put(j, matrix[i][j]);
                }
            }
        }
        return sparseMatrix;
    }

    private HashMap<Integer, HashMap<Integer, Integer>> multiplyTwoSparseMatrices(
            HashMap<Integer, HashMap<Integer, Integer>> sparseA, HashMap<Integer, HashMap<Integer, Integer>> sparseB) {
        HashMap<Integer, HashMap<Integer, Integer>> sparseC = new HashMap<>();

        for (int rowA : sparseA.keySet()) {
            for (int colA : sparseA.get(rowA).keySet()) {
                if (!sparseB.containsKey(colA)) {
                    continue;
                }
                int valA = sparseA.get(rowA).get(colA);
                sparseC.putIfAbsent(rowA, new HashMap<>());
                for (int colB : sparseB.get(colA).keySet()) {
                    int valB = sparseB.get(colA).get(colB);
                    int valC = sparseC.get(rowA).getOrDefault(colB, 0);
                    sparseC.get(rowA).put(colB, valC + valA * valB);
                }
            }
        }

        return sparseC;
    }
}

/**
 * Using normal Matrix Multiplication
 *
 * Time Complexity: O(M*K*N)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        if (mat1 == null || mat2 == null || mat1[0].length != mat2.length) {
            throw new IllegalArgumentException("Input matrix is invalid");
        }

        int rowsA = mat1.length;
        int colsA = mat1[0].length;
        int colsB = mat2[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int k = 0; k < colsA; k++) {
                if (mat1[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < colsB; j++) {
                    result[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }

        return result;
    }
}
