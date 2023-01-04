// LeetCode Question URL: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

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
 * PostOrder Traversal.
 *
 * Using height to determine the subtree with deepest nodes.
 *
 * Time Complexity: O(N) - All nodes are visited once
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes in tree. H = Height of the tree.
 */
class Solution1 {
    public class LcaResponse {
        TreeNode lca;
        int height;

        public LcaResponse() {
        }

        public LcaResponse(TreeNode n, int h) {
            lca = n;
            height = h;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        return lcaDeepestLeavesHelper(root).lca;
    }

    private LcaResponse lcaDeepestLeavesHelper(TreeNode node) {
        if (node == null) {
            return new LcaResponse(null, -1);
        }

        LcaResponse left = lcaDeepestLeavesHelper(node.left);
        LcaResponse right = lcaDeepestLeavesHelper(node.right);

        LcaResponse response = new LcaResponse();
        response.height = Math.max(left.height, right.height) + 1;

        if (left.height == right.height) {
            response.lca = node;
        } else if (left.height > right.height) {
            response.lca = left.lca;
        } else {
            response.lca = right.lca;
        }

        return response;
    }
}

/**
 * Iterative Solution - Using Parent Map
 *
 * Time Complexity: O(N) - All nodes are visited twice.
 *
 * Space Complexity: O(N + W) = O(N)
 *
 * N = Number of nodes in tree. W = Width of the tree.
 */
class Solution2 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Set<TreeNode> curLevel = Set.of(root);
        parentMap.put(root, null);

        while (true) {
            Set<TreeNode> nextLevel = new HashSet<>();
            for (TreeNode curNode : curLevel) {
                if (curNode.left != null) {
                    parentMap.put(curNode.left, curNode);
                    nextLevel.add(curNode.left);
                }
                if (curNode.right != null) {
                    parentMap.put(curNode.right, curNode);
                    nextLevel.add(curNode.right);
                }
            }
            if (nextLevel.isEmpty()) {
                break;
            }
            curLevel = nextLevel;
        }

        while (curLevel.size() > 1) {
            Set<TreeNode> parents = new HashSet<>();
            for (TreeNode curNode : curLevel) {
                parents.add(parentMap.get(curNode));
            }
            curLevel = parents;
        }

        return curLevel.iterator().next();
    }
}
