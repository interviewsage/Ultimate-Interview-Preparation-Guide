// LeetCode Question URL: https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/

// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};

/**
 * Single Pass
 *
 * Refer:
 * https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/discuss/149374/Java-5ms-One-Pass-and-Two-Pass-Traverse-With-Detailed-Comments-and-Edge-cases!
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution {
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        Node cur = head;

        while (cur.next != head) { // While the whole list is traversed. This means whole list same values.
            if (cur.val <= cur.next.val && cur.val <= insertVal && insertVal <= cur.next.val) {
                // case 1: Found the point but numbers still increasing.
                break;
            }
            if (cur.val > cur.next.val && (insertVal <= cur.next.val || cur.val <= insertVal)) {
                // case 2: Found the point but about to cross the from maximum value to minimum
                // value.
                break;
            }
            cur = cur.next;
        }

        newNode.next = cur.next;
        cur.next = newNode;
        return head;
    }
}
