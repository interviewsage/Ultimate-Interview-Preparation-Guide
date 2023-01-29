// LeetCode Question URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
// LeetCode Discuss URL:

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};

/**
 * Using the same concept as the intersection of the linked list.
 *
 * This solution handles the case when the nodes are not part of same
 * tree.
 *
 * Time Complexity: O(D1 + D2) = O(Depth of Node P + Depth of Node Q)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null || q == null) {
            return null;
        }

        Node p1 = p;
        Node p2 = q;

        while (p1 != p2) {
            p1 = (p1 == null) ? q : p1.parent;
            p2 = (p2 == null) ? p : p2.parent;
        }

        return p1;
    }
}

/**
 * THIS SOLUTION IS NOT NEEDED
 *
 * In this solution we are handling the case when the nodes are not part of same
 * tree. This solution will ensure that we do not go into endless loop and exit
 * when the root is reached second time.
 *
 * Using the same concept as the intersection of the linked list.
 *
 * Time Complexity: O(D1 + D2) = O(Depth of Node P + Depth of Node Q)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        Node p1 = p;
        Node p2 = q;

        boolean p1ReachedRoot = false;
        boolean p2ReachedRoot = false;

        while (p1 != p2) {
            p1 = p1.parent;
            p2 = p2.parent;

            if (p1 == null) {
                if (p1ReachedRoot) {
                    throw new IllegalArgumentException("Nodes in different tree");
                }
                p1ReachedRoot = true;
                p1 = q;
            }
            if (p2 == null) {
                if (p2ReachedRoot) {
                    throw new IllegalArgumentException("Nodes in different tree");
                }
                p2ReachedRoot = true;
                p2 = p;
            }
        }

        return p1;
    }
}
