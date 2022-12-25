// LeetCode Question URL: https://leetcode.com/problems/number-of-islands-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Union Find with ranks and path compression.
 *
 * Time Complexity: O(P).
 *
 * Each UnionFind operation takes O(1) on average due to path compression. (Its
 * a very slow increasing function).
 *
 * Space Complexity: O(M*N)
 *
 * M = Number of rows, N = Number of columns, P = number of positions.
 */
class Solution1 {
    private static final int[][] DIRS = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public class UnionFindForNumIslands {
        int[] parent;
        int[] rank;
        int numIslands;

        public UnionFindForNumIslands(int size) {
            parent = new int[size];
            rank = new int[size];
            numIslands = 0;
        }

        public boolean setIsland(int x) {
            if (isIsland(x)) {
                return false;
            }
            parent[x] = x;
            numIslands++;
            return true;
        }

        public boolean isIsland(int x) {
            return parent[x] != 0;
        }

        public int findParent(int x) {
            if (parent[x] != x) {
                parent[x] = findParent(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int pX = findParent(x);
            int pY = findParent(y);

            if (pX == pY) {
                return;
            }

            if (rank[pX] >= rank[pY]) {
                parent[pY] = pX;
                if (rank[pX] == rank[pY]) {
                    rank[pX]++;
                }
            } else {
                parent[pX] = pY;
            }
            numIslands--;
        }

        public int countOfIslands() {
            return numIslands;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if (m < 0 || n < 0 || positions == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<Integer> result = new ArrayList<>();
        if (m == 0 || n == 0) {
            if (positions.length > 0) {
                throw new IllegalArgumentException("Input is invalid");
            }
            return result;
        }

        UnionFindForNumIslands uf = new UnionFindForNumIslands(m * n + 1);

        for (int[] pos : positions) {
            int posIdx = pos[0] * n + pos[1] + 1;
            if (uf.setIsland(posIdx)) {
                for (int[] d : DIRS) {
                    int i = pos[0] + d[0];
                    int j = pos[1] + d[1];
                    int neighIdx = i * n + j + 1;

                    if (i >= 0 && j >= 0 && i < m && j < n && uf.isIsland(neighIdx)) {
                        uf.union(posIdx, neighIdx);
                    }
                }
            }

            result.add(uf.countOfIslands());
        }

        return result;
    }
}

/**
 * Using Union Find with ranks and path compression.
 *
 * Time Complexity: O(M*N + P). M*N to fill the arrays with -1. This can be
 * minimized by increasing the parents and ranks array size by 1. (Check
 * solution above)
 *
 * Each UnionFind operation takes O(1) on average due to path compression. (Its
 * a very slow increasing function).
 *
 * Space Complexity: O(M*N)
 *
 * M = Number of rows, N = Number of columns, P = number of positions.
 */
class Solution2 {
    class UnionFind {
        int count;
        int[] parents;
        int[] ranks;

        UnionFind(int m, int n) {
            count = 0;
            parents = new int[m * n];
            ranks = new int[m * n];
            Arrays.fill(parents, -1);
        }

        void createIsland(int pos) {
            if (parents[pos] == -1) {
                parents[pos] = pos;
                count++;
            }
        }

        boolean isValidIsland(int pos) {
            return parents[pos] >= 0;
        }

        int findRoot(int pos) {
            if (pos != parents[pos]) {
                parents[pos] = findRoot(parents[pos]);
            }
            return parents[pos];
        }

        void union(int x, int y) {
            int pX = findRoot(x);
            int pY = findRoot(y);

            if (pX == pY) {
                return;
            }

            if (ranks[pX] >= ranks[pY]) {
                parents[pY] = pX;
                ranks[pX] = ranks[pX] == ranks[pY] ? ranks[pX] + 1 : ranks[pX];
            } else {
                parents[pX] = pY;
            }
            count--;
        }
    }

    private static final int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (positions == null || positions.length == 0 || m <= 0 || n <= 0) {
            return result;
        }

        UnionFind uf = new UnionFind(m, n);

        for (int[] p : positions) {
            int curPos = p[0] * n + p[1];
            uf.createIsland(curPos);

            for (int[] d : dirs) {
                int x = p[0] + d[0];
                int y = p[1] + d[1];
                int nextPos = x * n + y;
                if (x < 0 || y < 0 || x >= m || y >= n || !uf.isValidIsland(nextPos)) {
                    continue;
                }
                uf.union(curPos, nextPos);
            }

            result.add(uf.count);
        }

        return result;
    }
}