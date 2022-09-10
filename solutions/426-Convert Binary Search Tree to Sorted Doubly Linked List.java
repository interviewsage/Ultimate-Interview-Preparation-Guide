// LeetCode Question URL: https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

import java.util.ArrayDeque;
import java.util.Deque;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

/**
 * Inorder (Recursion)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of Tree) = O(N) in worst case. (Recursion Stack)
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node dummy = new Node();
        Node[] prev = { dummy };

        inorderHelper(root, prev);

        prev[0].right = dummy.right;
        dummy.right.left = prev[0];

        return dummy.right;
    }

    private void inorderHelper(Node node, Node[] prev) {
        if (node == null) {
            return;
        }

        inorderHelper(node.left, prev);

        prev[0].right = node;
        node.left = prev[0];
        prev[0] = node;

        inorderHelper(node.right, prev);
    }
}

/**
 * Inorder (Iterative using stack)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of Tree) = O(N) in worst case. (Stack size)
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Deque<Node> stack = new ArrayDeque<>();
        Node dummy = new Node();
        Node prev = dummy;
        Node cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();

            prev.right = cur;
            cur.left = prev;
            prev = cur;

            cur = cur.right;
        }

        prev.right = dummy.right;
        dummy.right.left = prev;

        return dummy.right;
    }
}

/**
 * Inorder (Recursion without using Dummy Pointer)
 *
 * Answer copied from:
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/154659/Divide-and-Conquer-without-Dummy-Node-Java-Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of Tree) = O(N) in worst case. (Recursion Stack)
 *
 * N = Number of nodes in the tree.
 */
class Solution3 {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node leftHead = treeToDoublyList(root.left);
        Node rightHead = treeToDoublyList(root.right);
        root.left = root;
        root.right = root;
        return connect(connect(leftHead, root), rightHead);
    }

    // Used to connect two circular doubly linked lists. n1 is the head of circular
    // DLL as well as n2.
    private Node connect(Node n1, Node n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }

        Node tail1 = n1.left;
        Node tail2 = n2.left;

        tail1.right = n2;
        n2.left = tail1;
        tail2.right = n1;
        n1.left = tail2;

        return n1;
    }
}
