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
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }

        for (int[] row : image) {
            int start = 0;
            int end = row.length - 1;
            while (start <= end) {
                if (row[start] == row[end]) {
                    row[start] ^= 1;
                    row[end] = row[start];
                }
                start++;
                end--;
            }
        }

        return image;
    }
}
