// LeetCode Question URL: https://leetcode.com/problems/inorder-successor-in-bst-ii/
// LeetCode Discuss URL:

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};

/**
 * Iterative BST Search - Here you have access to value of node
 *
 * Time Complexity: O(H) in Best case. O(N) in worst case.
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        while (node.parent != null) {
            if (node.parent.val > node.val) {
                return node.parent;
            }
            node = node.parent;
        }

        return null;
    }
}

/**
 * Iterative BST Search - Here you do not have access to the value of node
 *
 * Time Complexity: O(H) in Best case. O(N) in worst case.
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        while (node.parent != null) {
            if (node == node.parent.left) {
                return node.parent;
            }
            node = node.parent;
        }

        return null;
    }
}

/**
 * Iterative BST Search
 *
 * Time Complexity: O(H) in Best case. O(N) in worst case.
 *
 * Space Complexity: O(H)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return findSmallestBSTNode(node.right);
        }

        return findNextLargeParentBST(node);
    }

    private Node findSmallestBSTNode(Node node) {
        return (node == null || node.left == null) ? node : findSmallestBSTNode(node.left);
    }

    private Node findNextLargeParentBST(Node node) {
        if (node == null || node.parent == null) {
            return null;
        }
        return node.equals(node.parent.right) ? findNextLargeParentBST(node.parent) : node.parent;
    }
}
