// LeetCode Question URL: https://leetcode.com/problems/number-of-provinces/
// LeetCode Discuss URL:

/**
 * This question was also know as 547-Friend Circle. URL was https://leetcode.com/problems/friend-circles/
 */

/**
 * Using Union Find
 *
 * Time Complexity: O(N^2/2 * ⍺). Where ⍺ is a very slow growing function, which
 * is always ≤ 4. Thus the time complexity becomes O(N^2).
 *
 * Space Complexity: O(N)
 *
 * N = Number of cities in the class.
 */
class Solution1 {
    class UnionFind {
        int[] parents;
        int[] ranks;
        int count;

        public UnionFind(int n) {
            this.parents = new int[n + 1];
            this.ranks = new int[n + 1];
            this.count = n;
        }

        public int findParent(int a) {
            if (parents[a] == 0) {
                parents[a] = a;
            } else if (parents[a] != a) {
                parents[a] = findParent(parents[a]);
            }
            return parents[a];
        }

        public void union(int x, int y) {
            int parentX = findParent(x);
            int parentY = findParent(y);

            if (parentX == parentY) {
                return;
            }

            if (ranks[parentX] >= ranks[parentY]) {
                parents[parentY] = parentX;
                if (ranks[parentX] == ranks[parentY]) {
                    ranks[parentX]++;
                }
            } else {
                parents[parentX] = parentY;
            }
            count--;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null) {
            return 0;
        }

        int n = isConnected.length;
        if (n <= 1) {
            return n;
        }

        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i + 1, j + 1);
                }
            }
        }

        return uf.count;
    }
}

/**
 * Using DFS
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N). Visited array Size. Also recursion stack cannot grow
 * more than size N
 *
 * N = Number of students in the class.
 */
class Solution2 {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }

        boolean[] visited = new boolean[M.length];
        int count = 0;

        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfsHelper(M, visited, i);
                count++;
            }
        }

        return count;
    }

    public void dfsHelper(int[][] M, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < M.length; j++) {
            if (visited[j] || M[i][j] == 0) {
                continue;
            }
            dfsHelper(M, visited, j);
        }
    }
}
