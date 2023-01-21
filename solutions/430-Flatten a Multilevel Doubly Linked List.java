// LeetCode Question URL: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};

/**
 * Iterative Solution.
 *
 * Time Complexity: O(N). Each node is visited at most twice.
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the Multilevel Doubly Linked List.
 */
class Solution1 {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                Node child = cur.child;
                while (child.next != null) {
                    child = child.next;
                }

                // Right End
                if (cur.next != null) {
                    child.next = cur.next;
                    cur.next.prev = child;
                }
                // Left End
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }
            cur = cur.next;
        }

        return head;
    }
}

/**
 * Recursive Solution.
 *
 * Time Complexity: O(N). Each node is visited once.
 *
 * Space Complexity: O(Depth of List). In worst case O(N).
 *
 * N = Number of nodes in the Multilevel Doubly Linked List.
 */
class Solution2 {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node dummy = new Node();
        flattenHelper(head, new Node[] { dummy });
        head.prev = null;
        dummy.next = null;
        return head;
    }

    private void flattenHelper(Node node, Node[] prevSolved) {
        if (node == null) {
            return;
        }

        prevSolved[0].next = node;
        node.prev = prevSolved[0];
        prevSolved[0] = node;

        Node next = node.next;
        if (node.child != null) {
            flattenHelper(node.child, prevSolved);
            node.child = null;
        }

        flattenHelper(next, prevSolved);
    }
}

/**
 * Iterative Solution using Stack
 *
 * Time Complexity: O(N). Each node is visited once.
 *
 * Space Complexity: O(Depth of child nodes). In worst case O(N).
 *
 * N = Number of nodes in the Multilevel Doubly Linked List.
 */
class Solution3 {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Deque<Node> stack = new ArrayDeque<>();
        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            } else if (cur.next == null && !stack.isEmpty()) {
                cur.next = stack.pop();
                cur.next.prev = cur;
            }

            cur = cur.next;
        }

        return head;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N). Each node is visited once.
 *
 * Space Complexity: O(Depth of child nodes). In worst case O(N).
 *
 * N = Number of nodes in the Multilevel Doubly Linked List.
 */
class Solution4 {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        flattenHelper(head);
        return head;
    }

    private Node flattenHelper(Node cur) {
        if (cur == null) {
            return null;
        }

        Node listTail = cur;

        while (cur != null) {
            listTail = cur;

            // No child present. Continue.
            if (cur.child == null) {
                cur = cur.next;
                continue;
            }

            // Child present. Recursively find the tail of the child list
            listTail = flattenHelper(cur.child);

            // Append the current tail in the child list
            if (cur.next != null) {
                listTail.next = cur.next;
                cur.next.prev = listTail;
            }
            // Merge the child list into main flat list
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;

            cur = listTail.next;
        }

        return listTail;
    }
}
