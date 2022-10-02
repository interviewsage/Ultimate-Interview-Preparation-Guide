// LeetCode Question URL: https://leetcode.com/problems/copy-list-with-random-pointer/

import java.util.*;

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/**
 * Iterative with constant extra space
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in input list.
 */
class Solution1 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        Node dummy = new Node(-1);
        Node copyList = dummy;
        cur = head;
        while (cur != null) {
            copyList.next = cur.next;
            copyList = copyList.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }

        return dummy.next;
    }
}

class Solution1WithOutDummyPointer {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        while (cur != null) {
            Node copyNode = new Node(cur.val);
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = copyNode.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = head;
        Node copyHead = cur.next;
        while (cur != null) {
            Node next = cur.next.next;
            if (next != null) {
                cur.next.next = next.next;
            }
            cur.next = next;
            cur = next;
        }

        return copyHead;
    }
}

/**
 * Iterative with memoization
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Number of nodes in input list.
 */
class Solution2 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        map.put(head, new Node(head.val));
        Node cur = head;

        while (cur != null) {
            Node next = cur.next;
            if (next != null) {
                if (!map.containsKey(next)) {
                    map.put(next, new Node(next.val));
                }
                map.get(cur).next = map.get(next);
            }

            Node random = cur.random;
            if (random != null) {
                if (!map.containsKey(random)) {
                    map.put(random, new Node(random.val));
                }
                map.get(cur).random = map.get(random);
            }

            cur = cur.next;
        }

        return map.get(head);
    }
}

/**
 * Recursive with memoization
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N + N) = Memoization Map + Stack
 *
 * N = Number of nodes in input list.
 */
class Solution3 {
    public Node copyRandomList(Node head) {
        return copyRandomListHelper(head, new HashMap<>());
    }

    private Node copyRandomListHelper(Node cur, Map<Node, Node> map) {
        if (cur == null) {
            return null;
        }

        if (map.containsKey(cur)) {
            return map.get(cur);
        }

        Node newNode = new Node(cur.val);
        map.put(cur, newNode);

        newNode.next = copyRandomListHelper(cur.next, map);
        newNode.random = copyRandomListHelper(cur.random, map);

        return newNode;
    }
}
