// LeetCode Question URL: https://leetcode.com/problems/find-leaves-of-binary-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Recursive - Post Order Traversal
 *
 * Time Complexity: O(N) - Each node is visited once
 *
 * Space Complexity: O(H) - In worst case this can be O(N)
 *
 * N = Number of nodes in the tree. H = height of the tree.
 */
class Solution1 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        findLeavesHelper(result, root);
        return result;
    }

    private int findLeavesHelper(List<List<Integer>> result, TreeNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = findLeavesHelper(result, node.left);
        int rightHeight = findLeavesHelper(result, node.right);

        int height = Math.max(leftHeight, rightHeight) + 1;
        if (result.size() == height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(node.val);
        node.left = null;
        node.right = null;

        return height;
    }
}

/**
 * Iterative - Topological Sort
 *
 * Refer:
 * https://leetcode.com/problems/find-leaves-of-binary-tree/discuss/83871/Java-Solution-similar-to-BFS-Topological-sort
 *
 * Time Complexity: O(N) - Each node is added tto stack once and then removed
 * from stack once. Each node is added to queue once and then polled from queue
 * once.
 *
 * Space Complexity: O(N + H) - In worst case this can be O(N).
 *
 * N = Number of nodes in the tree. H = height of the tree.
 */
class Solution2 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);

        Map<TreeNode, Integer> outDegreeMap = new HashMap<>();
        Queue<TreeNode> zeroOutDegreeQueue = new ArrayDeque<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            int outDegree = 0;

            if (cur.right != null) {
                outDegree++;
                parentMap.put(cur.right, cur);
                stack.push(cur.right);
            }
            if (cur.left != null) {
                outDegree++;
                parentMap.put(cur.left, cur);
                stack.push(cur.left);
            }

            if (outDegree == 0) {
                zeroOutDegreeQueue.offer(cur);
            } else {
                outDegreeMap.put(cur, outDegree);
            }
        }

        while (!zeroOutDegreeQueue.isEmpty()) {
            int size = zeroOutDegreeQueue.size();
            List<Integer> list = new ArrayList<>();
            result.add(list);

            for (int i = 0; i < size; i++) {
                TreeNode cur = zeroOutDegreeQueue.poll();
                list.add(cur.val);

                TreeNode parent = parentMap.get(cur);
                if (parent != null) {
                    int parentNewOutDegree = outDegreeMap.get(parent) - 1;
                    if (parentNewOutDegree == 0) {
                        zeroOutDegreeQueue.offer(parent);
                    }
                    outDegreeMap.put(parent, parentNewOutDegree);
                }

                cur.left = null;
                cur.right = null;
            }
        }

        return result;
    }
}
