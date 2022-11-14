// LeetCode Question URL: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// LeetCode Discuss URL:

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Iterative - One Pass using Two pointer.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input list.
 */
class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        while (n-- >= 0) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}

/**
 * Recursive - One Pass
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input list.
 */
class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        return n == removeNthFromEndHelper(head, n) ? head.next : head;
    }

    private int removeNthFromEndHelper(ListNode node, int n) {
        if (node == null) {
            return 0;
        }

        int nextNodeIdx = removeNthFromEndHelper(node.next, n);
        if (nextNodeIdx == n) {
            node.next = node.next.next;
        }
        return nextNodeIdx + 1;
    }
}
