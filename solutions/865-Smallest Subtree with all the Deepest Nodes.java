// LeetCode Question URL: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
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

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        return subtreeWithAllDeepestHelper(root).lca;
    }

    private LcaResponse subtreeWithAllDeepestHelper(TreeNode node) {
        if (node == null) {
            return new LcaResponse(null, -1);
        }

        LcaResponse left = subtreeWithAllDeepestHelper(node.left);
        LcaResponse right = subtreeWithAllDeepestHelper(node.right);

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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
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

/**
 * PostOrder Traversal.
 *
 * Using height to determine the subtree with deepest nodes.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes in tree. H = Height of the tree.
 */
class Solution3 {
    class Result {
        TreeNode node;
        int height;

        Result(TreeNode n, int h) {
            node = n;
            height = h;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }

        Result res = dfsHelper(root);

        return res.node;
    }

    private Result dfsHelper(TreeNode root) {
        if (root == null) {
            return new Result(null, -1);
        }

        Result left = dfsHelper(root.left);
        Result right = dfsHelper(root.right);

        if (left.height == right.height) {
            return new Result(root, left.height + 1);
        } else if (left.height > right.height) {
            return new Result(left.node, left.height + 1);
        } else {
            return new Result(right.node, right.height + 1);
        }
    }
}
