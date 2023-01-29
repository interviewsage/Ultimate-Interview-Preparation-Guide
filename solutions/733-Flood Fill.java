// LeetCode Question URL: https://leetcode.com/problems/flood-fill/
// LeetCode Discuss URL:

import java.util.*;

/**
 * BFS Iterative
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity: O(2*M + 2*N) = O(M + N)
 *
 * M = Number of rows
 * N = Number of columns
 */
class Solution1 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (image[sr][sc] == color) {
            return image;
        }

        int originalColor = image[sr][sc];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { sr, sc });
        image[sr][sc] = color;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : DIRS) {
                int i = cur[0] + d[0];
                int j = cur[1] + d[1];
                if (i >= 0 && i < image.length && j >= 0 && j < image[0].length && image[i][j] == originalColor) {
                    image[i][j] = color;
                    queue.offer(new int[] { i, j });
                }
            }
        }

        return image;
    }
}

/**
 * DFS Recursive
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity: O(M * N)
 *
 * M = Number of rows
 * N = Number of columns
 */
class Solution2 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (image[sr][sc] == color) {
            return image;
        }

        dfsHelper(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    private void dfsHelper(int[][] image, int x, int y, int targetColor, int originalColor) {
        image[x][y] = targetColor;
        for (int[] d : DIRS) {
            int i = x + d[0];
            int j = y + d[1];
            if (i >= 0 && i < image.length && j >= 0 && j < image[0].length && image[i][j] == originalColor) {
                dfsHelper(image, i, j, targetColor, originalColor);
            }
        }
    }
}
