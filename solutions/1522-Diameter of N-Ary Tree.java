// LeetCode Question URL: https://leetcode.com/problems/diameter-of-n-ary-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};

/**
 * <pre>
 * For complexity calculation Purposes, lets assume:
 * N = Maximum number of children of a node. Each node can have 0 to N nodes.
 * M = Total number of nodes.
 *
 * Time Complexity:
 * Since in a Tree, if there are M nodes, then there are M-1 edges.
 * In this solution each node and each edge will be visited once.
 * Thus, Total Time Complexity = O(V + E) + O(M + M-1) = O(M)
 *
 * Space Complexity: O(Height of the input tree)
 * 1. In worst case, for a skewed tree, Height can be equal to the total number of nodes. Thus, worst case space complexity = O(M)
 * 2. In best / average case, for a balanced N-ary tree, height = O(log M / log N) = O(logN M).
 * </pre>
 *
 * <pre>
 * Some Useful links on Trees:
 * 1. Tree: https://en.wikipedia.org/wiki/Tree_(graph_theory)
 * 2. m-ary tree: https://en.wikipedia.org/wiki/M-ary_tree
 * 3. What is the definition for the height of a tree?: https://stackoverflow.com/a/2209802
 * </pre>
 */
class Solution {
    public int diameter(Node root) {
        if (root == null || root.children.size() == 0) {
            return 0;
        }

        int[] diameter = { 0 };
        diameterHelper(root, diameter);
        return diameter[0];
    }

    private int diameterHelper(Node node, int[] diameter) {
        if (node.children.size() == 0) {
            return 0;
        }

        // Setting below maximums to -1 helps in the case if there is only one child
        // node of this node.
        int max1 = -1;
        int max2 = -1;

        for (Node child : node.children) {
            int height = diameterHelper(child, diameter);
            if (height > max1) {
                max2 = max1;
                max1 = height;
            } else if (height > max2) {
                max2 = height;
            }
        }

        diameter[0] = Math.max(diameter[0], max1 + max2 + 2);
        return max1 + 1;
    }
}
