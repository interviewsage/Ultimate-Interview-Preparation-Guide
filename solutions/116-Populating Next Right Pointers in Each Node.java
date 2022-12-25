// LeetCode Question URL: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
// LeetCode Discuss URL:

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

/**
 * Iterative Solution
 *
 * This solution will work for perfect binary tree only. As it assumes every
 * parent has 2 children and all leaves are at the same level.
 *
 * Time Complexity: O((N-1)/2). While loop runs over all nodes except last
 * level.
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // levelStart -> Left most node at each level.
        Node levelHead = root;
        while (levelHead.left != null) {
            Node cur = levelHead;
            while (cur != null) {
                // Since the given tree is perfect binary tree, we can assume left and right
                // node exists.
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            levelHead = levelHead.left;
        }

        return root;
    }
}

/**
 * Recursive Solution
 *
 * This solution will work for perfect binary tree only. As it assumes every
 * parent has 2 children and all leaves are at the same level.
 *
 * Time Complexity: O(N). All nodes are visited once
 *
 * Space Complexity: O(H). Recursion stack space. = O(log N) since the given
 * tree is a perfect binary tree.
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public Node connect(Node root) {
        if (root == null || root.left == null) {
            return root;
        }

        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);

        return root;
    }
}
