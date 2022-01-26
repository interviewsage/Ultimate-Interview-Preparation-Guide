// LeetCode Question URL: https://leetcode.com/problems/validate-binary-tree-nodes/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Find root and the do DFS to count nodes.
 *
 * Time Complexity: O(N + N) = O(N)
 *
 * Space Complexity: O(N + H)
 */
class Solution1 {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        if (n < 0 || leftChild == null || rightChild == null || leftChild.length != n || rightChild.length != n) {
            return false;
        }
        if (n == 0) {
            return true;
        }

        Set<Integer> hasIncomingEdge = new HashSet<>();
        Set<Integer> zeroIndegreeNodes = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int left = leftChild[i];
            if (left != -1) {
                if (left == i || !hasIncomingEdge.add(left)) {
                    return false;
                }
                zeroIndegreeNodes.remove(left);
            }

            int right = rightChild[i];
            if (right != -1) {
                if (right == i || !hasIncomingEdge.add(right)) {
                    return false;
                }
                zeroIndegreeNodes.remove(right);
            }

            if (!hasIncomingEdge.contains(i)) {
                zeroIndegreeNodes.add(i);
            }
            if (zeroIndegreeNodes.isEmpty()) {
                return false;
            }
        }

        if (zeroIndegreeNodes.size() != 1) {
            return false;
        }
        return countNodesDFS(zeroIndegreeNodes.iterator().next(), leftChild, rightChild) == n;
    }

    private int countNodesDFS(int root, int[] leftChild, int[] rightChild) {
        if (root == -1) {
            return 0;
        }
        return 1 + countNodesDFS(leftChild[root], leftChild, rightChild)
                + countNodesDFS(rightChild[root], leftChild, rightChild);
    }
}

/**
 * Using Union Find.
 *
 * Time Complexity: O(N + N) = O(N)
 *
 * Space Complexity: O(N + N) = O(N)
 */
class Solution2 {

    class UnionFind {
        int[] parents;
        int[] ranks;
        int n;

        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
            }
            this.ranks = new int[n];
            this.n = n;
        }

        public int findParent(int n) {
            if (parents[n] != n) {
                parents[n] = findParent(parents[n]);
            }
            return parents[n];
        }

        public boolean union(int x, int y) {
            int parentX = findParent(x);
            int parentY = findParent(y);

            if (parentX == parentY) {
                return false;
            }

            if (ranks[parentX] >= ranks[parentY]) {
                parents[parentY] = parentX;
                if (ranks[parentX] == ranks[parentY]) {
                    ranks[parentX]++;
                }
            } else {
                parents[parentX] = parentY;
            }

            n--;
            return true;
        }
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        if (n < 0 || leftChild == null || rightChild == null || leftChild.length != n || rightChild.length != n) {
            return false;
        }
        if (n == 0) {
            return true;
        }

        UnionFind uf = new UnionFind(n);
        Set<Integer> hasIncomingEdge = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int left = leftChild[i];
            if (left != -1) {
                if (!hasIncomingEdge.add(left) || !uf.union(i, left)) {
                    return false;
                }
            }
            int right = rightChild[i];
            if (right != -1) {
                if (!hasIncomingEdge.add(right) || !uf.union(i, right)) {
                    return false;
                }
            }
        }

        return uf.n == 1;
    }
}
