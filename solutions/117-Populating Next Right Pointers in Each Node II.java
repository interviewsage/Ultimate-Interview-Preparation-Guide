// LeetCode Question URL: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
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
 * Time Complexity: O(N). All nodes are visited once.
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

        Node dummy = new Node();
        Node cur = root;

        while (cur != null) {
            Node pre = dummy;
            /**
             * Visiting all nodes in the same level and connecting their children's next
             * pointers.
             */
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;
            dummy.next = null;
        }

        return root;
    }
}

class Solution2 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root;

        while (cur != null) {
            Node pre = null;
            Node head = null;
            while (cur != null) {
                if (cur.left != null) {
                    if (pre == null) {
                        head = cur.left;
                    } else {
                        pre.next = cur.left;
                    }
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre == null) {
                        head = cur.right;
                    } else {
                        pre.next = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
        }

        return root;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N). All nodes are visited once.
 *
 * Space Complexity: O(2 * H). Recursion stack space + levelList
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public Node connect(Node root) {
        connectDfsHelper(root, new ArrayList<>(), 0);
        return root;
    }

    private void connectDfsHelper(Node node, List<Node> levelList, int level) {
        if (node == null) {
            return;
        }

        if (levelList.size() == level) {
            levelList.add(node);
        } else {
            levelList.set(level, node).next = node;
            // levelList.get(level).next = node;
            // levelList.set(level, node);
        }

        connectDfsHelper(node.left, levelList, level + 1);
        connectDfsHelper(node.right, levelList, level + 1);
    }
}

/**
 * DO NOT SOLVE BELOW SOLUTIONS
 */

/**
 * Recursive Solution
 *
 * Time Complexity: O(N^2). Last level in Perfect tree will be visited multiple
 * times.
 *
 * Space Complexity: O(H). Recursion stack space.
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution4 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connectHelper(root);

        return root;
    }

    private void connectHelper(Node node) {
        if (node == null) {
            return;
        }

        Node dummy = new Node();
        Node tmp = dummy;

        if (node.left != null) {
            tmp.next = node.left;
            tmp = tmp.next;
        }
        if (node.right != null) {
            tmp.next = node.right;
            tmp = tmp.next;
        }
        if (node.next != null) {
            Node nextNode = node.next;
            while (nextNode.next != null && nextNode.left == null && nextNode.right == null) {
                nextNode = nextNode.next;
            }
            if (nextNode.left != null) {
                tmp.next = nextNode.left;
            } else if (nextNode.right != null) {
                tmp.next = nextNode.right;
            }
        }

        dummy.next = null;

        connectHelper(node.right);
        connectHelper(node.left);
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N). All nodes are visited once.
 *
 * Space Complexity: O(2N+H) = O(N).. These are for saving the references of
 * nodes in HashMap. Clones of nodes is not created.
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution5 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();

        connectHelper(root, map);

        return root;
    }

    private void connectHelper(Node node, Map<Node, Node> map) {
        if (node == null) {
            return;
        }

        Node dummy = new Node();
        Node tmp = dummy;
        if (node.left != null) {
            tmp.next = node.left;
            tmp = tmp.next;
        }
        if (node.right != null) {
            tmp.next = node.right;
            tmp = tmp.next;
        }

        if (node.next != null) {
            tmp.next = map.get(node.next);
        }

        map.put(node, dummy.next);

        connectHelper(node.right, map);
        connectHelper(node.left, map);
    }
}
