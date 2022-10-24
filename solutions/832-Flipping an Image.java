// LeetCode Question URL: https://leetcode.com/problems/flipping-an-image/
// LeetCode Discuss URL: https://leetcode.com/problems/flipping-an-image/discuss/1520742/Java-or-TC:-O(N2)-or-SC:-O(1)-or-Optimal-one-pass-in-place-solution

/**
 * Constant Space One-Pass Solution
 *
 * <pre>
 * If the values are not same, swap and flip will not change anything.
 * If the values are same, we will flip both.
 * </pre>
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Matrix Size
 */
class Solution1 {
    public int[][] flipAndInvertImage(int[][] image) {
        if (image == null) {
            throw new IllegalArgumentException("Input image is null");
        }

        if (image.length == 0) {
            return image;
        }
        int cols = image[0].length;
        if (cols == 0) {
            return image;
        }

        for (int[] row : image) {
            int i = 0;
            int j = cols - 1;
            while (i < j) {
                if (row[i] == row[j]) {
                    row[i] ^= 1;
                    row[j] = row[i];
                }
                i++;
                j--;
            }
            if (i == j) {
                row[i] ^= 1;
            }
        }

        return image;
    }
}

class Solution2 {
    public int[][] flipAndInvertImage(int[][] image) {
        if (image == null) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int rows = image.length;
        if (rows == 0) {
            return image;
        }

        int start = 0;
        int end = image[0].length - 1;
        while (start <= end) {
            for (int i = 0; i < rows; i++) {
                if (start == end) {
                    image[i][start] ^= 1;
                } else if (image[i][start] == image[i][end]) {
                    image[i][start] ^= 1;
                    image[i][end] = image[i][start];
                }
            }
            start++;
            end--;
        }

        return image;
    }
}
